/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelView;
import Model.LichessAPI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import com.github.bhlangonijr.chesslib.pgn.GameLoader;
import com.github.bhlangonijr.chesslib.game.Game;

/**
 *
 * @author kamus
 */
public class GamesController {
    static List<Game> games = new ArrayList<>();
    
    
    public static List<Game> instanceGames(String user) {
        List<String> gameList = LichessAPI.downloadUserGames(user);
        
        for (String gameJson : gameList) {
            try {
                Game game = GameLoader.loadNextGame(getPgnIterator(gameJson));
                games.add(game);
                System.out.println(game.getBlackPlayer());
            } catch (Exception e) {
                System.out.println("Error procesando partida: " + gameJson);
                e.printStackTrace();
            }
        }

        return games;
    }
     // Method to get an iterator over the lines of the PGN component from a JSON string
    public static Iterator<String> getPgnIterator(String gameJson) throws JSONException {
        JSONObject json = new JSONObject(gameJson);
        String pgn = json.optString("pgn", ""); // Extract PGN, default to empty if not found
        return Arrays.asList(pgn.split("\\R")).iterator(); // Split PGN by lines and return iterator
    }
    

    
}
