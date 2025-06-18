/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author adria_6
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EcoOpeningTrie {
    private final OpeningTrie trie = new OpeningTrie();

    public void loadECOFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        String openingName = "";
        StringBuilder moves = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            line = line.trim();

            if (line.startsWith("[ECO")) {
                moves.setLength(0); // Reset moves
            } else if (line.startsWith("[Opening")) {
                openingName = line.split("\"")[1]; // Extract Opening name
            } else if (line.startsWith("[Variation")) {
                openingName += ", " + line.split("\"")[1]; // Add variation name
            } else if (!line.startsWith("[") && !line.isEmpty()) {
                // Collect moves until "*"
                if (line.contains("*")) {
                    line = line.replace("*", "").trim();
                    moves.append(" ").append(line);
                    trie.insert(moves.toString().trim(), openingName);
                } else {
                    moves.append(" ").append(line);
                }
            }
        }
        reader.close();
    }

    public String getClosestOpening(String moves) {
        return trie.searchClosest(moves);
    }
}