/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import com.github.bhlangonijr.chesslib.game.*;
import com.github.bhlangonijr.chesslib.move.*;
import com.github.bhlangonijr.chesslib.Board;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adria_6
 */
public class GameAnalysis {
    public Game game;
    public String user;
    public MoveList moves;
    public List<String> fenList;
    LinkedHashMap<Move, Evaluation> moveEvaluations = new LinkedHashMap<>();
    HashMap<Move, Evaluation> deltaEvaluations = new HashMap<>();
    HashMap<String, String> tactOps = new HashMap<>();
    HashMap<String, String> bestMoves = new HashMap<>();
    
    
    public GameAnalysis(Game game) {
        this.fenList = new ArrayList<>();
        this.game = game;
        this.moves = game.getHalfMoves();
        try {
            this.game.loadMoveText();
        } catch (Exception ex) {
            Logger.getLogger(GameAnalysis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public final void makeEvaluations() throws IOException, InterruptedException{
        Board board = new Board();
        String stockfishPath = "src/resources/stockfish/stockfish-windows-x86-64.exe"; // Ruta relativa, ajustar si es necesario
        StockfishEngine engine = new StockfishEngine(stockfishPath);
        // Loop through the moves in the game
        for (Move move: moves) {
            // Do the current move
            board.doMove(move);

            // Get the FEN of the position after the move is played
            String fen = board.getFen();
            fenList.add(fen);
            
            //Set position in the engine
            engine.setPosition(fen);

            // Get the evaluation for this FEN position
            Evaluation evaluation = engine.getEvaluation(200);

            // Store the move and its evaluation in the HashMap
            moveEvaluations.put(move, evaluation);
            
            // Store the best move 
            bestMoves.put(fen, engine.getBestMove(200));
        }
    }
    
    public void makeDeltaEvaluations(){
        Evaluation currentEvaluation = new Evaluation("cp", 0);
        // Loop through the moves in the game
        for(Move move: moves){
            Evaluation moveEvaluation = moveEvaluations.get(move);
            if(currentEvaluation.getScoreType().equals("mate")){
                if(currentEvaluation.getMateScore()>0){
                    deltaEvaluations.put(move, new Evaluation("cp", (double) 0.0));
                } else {
                    if (moveEvaluation.getScoreType().equals("mate")){
                        if(moveEvaluation.getMateScore()>0){
                            deltaEvaluations.put(move, new Evaluation("cp", (double) 0.0));
                        } else {
                            deltaEvaluations.put(move, new Evaluation("cp", (double) -10.0));
                        }
                    } else {
                        deltaEvaluations.put(move, new Evaluation("cp", (double) -10.0));
                    }
                }
            } else if (moveEvaluation.getScoreType().equals("mate")){
                if(moveEvaluation.getMateScore()<0){
                    deltaEvaluations.put(move, new Evaluation("cp", (double) -10.0));
                } else {
                    deltaEvaluations.put(move, new Evaluation("cp", (double) 0.0));
                }
            } else {
                deltaEvaluations.put(move, new Evaluation("cp",
                        moveEvaluation.getCpScore() + currentEvaluation.getCpScore()));
            }
            
            currentEvaluation = moveEvaluation;
        }
    }
    
    public void makeTactOps(String user){
        makeDeltaEvaluations();
        for(Evaluation eval : deltaEvaluations.values()){
            System.out.println(eval.getCpScore());
        }
        
        //Este metodo localiza los errores
        //del contrincante del usuario
        
        //movimientos pares son de las blancas. 
        int side = 0;
        if (game.getWhitePlayer().getName().equals(user)){
            side++;
        }
        int n;
        for(n = side; n<moves.size(); n+=2){
            Move move = moves.get(n);
            //Vemos que el cambio en la evaluación sea mayor que 1.5
            if (Math.abs(deltaEvaluations.get(move).getCpScore())>1.5){
                String header;
                String fen = fenList.get(n);                
                if(moveEvaluations.get(move).getScoreType().equals("mate")){
                    header = "mate " + Integer.toString(moveEvaluations.get(move).getMateScore());
                } else {
                    header = "100*cp" + moveEvaluations.get(move).getCpScore();
                }
                System.out.println(header);
                tactOps.put(header, fen);
            }
            
        }
                
        
    }
    public List<String> getFenList() {
        return fenList;
    }

    public HashMap<String, String> getBestMoves() {
        return bestMoves;
    }

    public HashMap<String, String> getTactOps() {
        return tactOps;
    }
    
    public void printEvaluations(){
        for (Move move: moves){
            Evaluation moveEval = moveEvaluations.get(move);
            if (moveEval.getScoreType().equals("cp")){
                System.out.println(move.toString()+" eval: "+moveEval.getCpScore());
            } else if(moveEval.getScoreType().equals("mate")) {
                System.out.println(move.toString()+" eval: mate in "+moveEval.getMateScore());
            }
            
        }
    }
}
