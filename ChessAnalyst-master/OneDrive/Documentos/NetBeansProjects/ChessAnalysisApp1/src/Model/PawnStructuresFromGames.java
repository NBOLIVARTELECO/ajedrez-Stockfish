/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import com.github.bhlangonijr.chesslib.pgn.*;
import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.game.*;
import com.github.bhlangonijr.chesslib.move.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author adria_6
 */
public class PawnStructuresFromGames {

    public PawnStructuresFromGames() {
        PgnHolder pgn = new PgnHolder("src\\main\\resources\\chess_com_games_2025-02-02_as_black.pgn");
        try {
            pgn.loadPgn();
        } catch (Exception ex) {
            Logger.getLogger(PawnStructuresFromGames.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Game game: pgn.getGames()) {
            try {
                game.loadMoveText();
            } catch (Exception ex) {
                Logger.getLogger(PawnStructuresFromGames.class.getName()).log(Level.SEVERE, null, ex);
            }
            MoveList moves = game.getHalfMoves();
            Board board = new Board();
            //For each move on the game check if it corresponds to a known pawn structure
            String currentPawnStructure = "Other";
            for (Move move: moves) {
                board.doMove(move);
                String fen = board.getFen();
                PawnStructure current = new PawnStructure(fen);
                if (!(current.structureName.equals("Other"))){
                    currentPawnStructure = current.structureName;
                    break;
                }
            }
            System.out.println("PS: " + currentPawnStructure);
        }
    }
    
}
