/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Model;
import com.github.bhlangonijr.chesslib.*;
import com.github.bhlangonijr.chesslib.pgn.PgnHolder;
import com.github.bhlangonijr.chesslib.game.Game;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author adria_6
 */
public class ChessAnalysis {
    public static void main(String[] args) throws IOException, InterruptedException{
        //Test1 test = new Test1();
        //Test2 test2 = new Test2();
        
        //OpeningHistogram histo = new OpeningHistogram();
        
        //String row = "12P121";
        //char[] charRow = {'1', '2', 'P', '1', '2', '1'};
        //System.out.println(PawnStructure.simpleRow(charRow));
        
        //String row = "k1Pp2QR";
        //System.out.println(PawnStructure.removePieces(row));
        
        //String fenTest = "r1bqkb1r/pp1p1ppp/2n1pn2/8/3NP3/3BB3/PPP2PPP/RN1QK2R b KQkq - 4 6";
        //PawnStructure structure = new PawnStructure(fenTest);
        //System.out.println(structure.pawnRows);
        
        //PawnStructuresFromGames test = new PawnStructuresFromGames();
        
        //String structureA = "8/pp3ppp/3p4/2p1p3/3PP3/2P5/PP3PPP/8";
        //String structureB = "8/pp3p2/3p2pp/2p1p3/3PP3/2P5/PP3PPP/8";
        //System.out.println(PawnStructure.isSimilarStructure(structureA, structureB, true));
        

//        // Set the path to your Stockfish binary (adjust the path as needed)
//        String stockfishPath = "src/resources/stockfish/stockfish-windows-x86-64.exe"; // Ruta relativa, ajustar si es necesario
//        StockfishEngine engine = new StockfishEngine(stockfishPath);
//
//        // Set the initial position (FEN format)
//        String initialFen = "rn2k2r/ppp2ppp/8/3p4/6nq/4P2R/PPPPN1K1/RNBQ4 b kq - 1 12"; // "startpos" means standard chess starting position
//        engine.setPosition(initialFen);
//
//        // Get the best move for the current position (e.g., give it 2 seconds to think)
//        String bestMove = engine.getBestMove(2000); // Time in milliseconds (e.g., 2000ms = 2 seconds)
//        System.out.println("Best move: " + bestMove);
//        
//        //Get evaluation
//        double evaluation = engine.getEvaluation(2000); // Time in milliseconds (e.g., 2000ms = 2 seconds)
//        System.out.println("Evaluation: " + evaluation);
//
//        // Close the engine when done
//        engine.close();

//        PgnHolder pgn = new PgnHolder("src\\main\\resources\\chess_eco_2021-12-03.pgn");
//        try {
//            pgn.loadPgn();
//        } catch (Exception ex) {
//            Logger.getLogger(Test1.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        Game gameTest = pgn.getGames().get(0);
//        GameAnalysis analysis = new GameAnalysis(gameTest);
//        analysis.makeEvaluations();
//        analysis.printEvaluations();

    }
}
