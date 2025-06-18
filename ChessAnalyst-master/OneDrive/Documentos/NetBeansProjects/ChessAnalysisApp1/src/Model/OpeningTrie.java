/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author adria_6
 */
import java.util.HashMap;
import java.util.Map;

class TrieNode {
    Map<String, TrieNode> children = new HashMap<>();
    String openingName = null;  // Stores the opening if this node represents a full move sequence
}

public class OpeningTrie {
    private final TrieNode root = new TrieNode();

    // Insert an opening move sequence into the Trie
    public void insert(String moveSequence, String openingName) {
        TrieNode node = root;
        String[] moves = moveSequence.split(" ");

        for (String move : moves) {
            node.children.putIfAbsent(move, new TrieNode());
            node = node.children.get(move);
        }
        node.openingName = openingName;
    }

    // Find the closest matching opening
    public String searchClosest(String moveSequence) {
        TrieNode node = root;
        String[] moves = moveSequence.split(" ");
        String lastFoundOpening = "Unknown Opening";

        for (String move : moves) {
            if (!node.children.containsKey(move)) {
                return lastFoundOpening; // Return the last known opening
            }
            node = node.children.get(move);
            if (node.openingName != null) {
                lastFoundOpening = node.openingName;
            }
        }

        return lastFoundOpening;
    }
}
