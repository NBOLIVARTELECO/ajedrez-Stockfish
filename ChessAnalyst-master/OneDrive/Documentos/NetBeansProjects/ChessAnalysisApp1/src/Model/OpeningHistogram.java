package Model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.github.bhlangonijr.chesslib.pgn.*;
import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.game.*;
import com.github.bhlangonijr.chesslib.move.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Objects;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartUtils;
import java.io.File;
import java.io.IOException;
import java.util.List;
/**
 *
 * @author adria_6
 */
public class OpeningHistogram {
    private JFreeChart chart;
    public OpeningHistogram(List<Game> games) throws IOException {
        
        // Load ECO PGN into Trie
        EcoOpeningTrie ecoTrie = new EcoOpeningTrie();
        ecoTrie.loadECOFile("src/resources/eco.pgn"); // Ruta relativa, ajustar si es necesario
        
        DefaultCategoryDataset openingDS = new DefaultCategoryDataset();

        for (Game game: games) {
            Board board = new Board();
            StringBuilder firstMoves = new StringBuilder();

            // Extract the first few moves
            List<Move> moves = game.getHalfMoves();
            int y = 1;
            for (int i = 0; i < Math.min(moves.size(), 6); i++) {  // Use first 6 moves
                Move move = moves.get(i);
                board.doMove(move);
                if(i%2==0){
                    firstMoves.append(String.valueOf(y)).append(". ").append(move.getSan()).append(" ");
                    y++;
                } else {
                    firstMoves.append(move.getSan()).append(" ");
                }
                
            }

            // Find the closest matching opening
            String openingName = ecoTrie.getClosestOpening(firstMoves.toString().trim());
            game.setOpening(openingName);  // Assign detected opening

            // Now the opening is correct before calling game.getOpening()
            String opVariation = game.getOpening();
            
            if (openingDS.getColumnKeys().contains(opVariation) && openingDS.getRowKeys().contains(game.getResult())){
                openingDS.incrementValue(1, game.getResult(), opVariation);
            } else if(game.getResult() != null && opVariation != null){
                openingDS.addValue(1, game.getResult(), opVariation);
            } else {
                System.out.print(game.getBlackPlayer());
            }
        }
        JFreeChart barChart = ChartFactory.createStackedBarChart("Openings you used", null, null, openingDS);
        chart = barChart;
    }

    public JFreeChart getChart() {
        return chart;
    }
    
}
