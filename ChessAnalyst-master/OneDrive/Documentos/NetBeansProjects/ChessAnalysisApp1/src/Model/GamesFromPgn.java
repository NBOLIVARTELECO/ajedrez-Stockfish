/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import com.github.bhlangonijr.chesslib.game.Game;
import com.github.bhlangonijr.chesslib.pgn.PgnHolder;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adria_6
 */
public class GamesFromPgn {
    PgnHolder pgn;
    List<Game> games;
    HashMap<String, Game> headerGameMap = new HashMap<>();

    public GamesFromPgn(String path) {
        this.pgn = new PgnHolder(path);
        try {
            this.pgn.loadPgn();
        } catch (Exception ex) {
            System.out.println("Error cargando PGN");
            Logger.getLogger(GamesFromPgn.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.games = this.pgn.getGames();
        for(Game game: this.games){
            String header = game.getWhitePlayer()+" vs. " +game.getBlackPlayer()
                    + " "+ game.getResult().getDescription();
            headerGameMap.put(header, game);
        }
    }
    
    public GamesFromPgn(List<Game> gamesList){
        games = gamesList;
        for(Game game: gamesList){
            String header = game.getWhitePlayer()+" vs. " +game.getBlackPlayer()
                    + " "+ game.getResult().getDescription();
            headerGameMap.put(header, game);
        }
    }
    
    public PgnHolder getPgn() {
        return pgn;
    }

    public List<Game> getGames() {
        return games;
    }

    public HashMap<String, Game> getHeaderGameMap() {
        return headerGameMap;
    }
    
    
}
