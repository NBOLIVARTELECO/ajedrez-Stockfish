/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author adria_6
 */
import org.apache.commons.lang3.ArrayUtils;
import java.util.Map;
import java.util.HashMap;
public class PawnStructure {
    public String pawnRows;
    public String structureName;
    public Map<String, String> pawnStructures = new HashMap<String, String>();

    public PawnStructure(String fen) {
        makeStructuresDict();
        String[] fenSplit = fen.split(" ");
        String[] rows = fenSplit[0].split("/");
        int x;
        for(x=0; x<8; x++){
            if (x == 0){
                pawnRows = removePieces(rows[x]);
            } else{
                pawnRows += "/" + removePieces(rows[x]);
            }
        }
        structureName = whichStructure(pawnRows);
    }
    public static String removePieces(String rowString){
        /*
        Takes a string representing a row in a fen and returns a string
        representing the same row but with all pieces removed except for the
        pawns.
        */
        String pawns;
        char[] fenChars = rowString.toCharArray();
        char[] noPiecesFenChars;
        int x;
        for (x = 0; x<fenChars.length; x++){
            if (!(fenChars[x] == 'P' || fenChars[x] == 'p' || Character.isDigit(fenChars[x]))){
                fenChars[x] = '1';
            }
        }
        noPiecesFenChars = simpleRow(fenChars);
        pawns = new String(noPiecesFenChars);
        return pawns;
    }
    
    public static char[] simpleRow(char[] rowList){
        /*
        Takes a list of characters and returns a list of characters in which 
        every consecutive digits have been replaced by their sum
        */
        char[] newRowList = rowList;
        boolean simpleForm = true;
        int x;
        for(x = 0; x<rowList.length -1; x++){
            char a = rowList[x];
            char b = rowList[x+1];
            if (Character.isDigit(a) && Character.isDigit(b)){
                simpleForm = false;
                int sum = Character.getNumericValue(a) + Character.getNumericValue(b);
                char num = Character.forDigit(sum, 10);
                rowList[x] = num;
                newRowList = ArrayUtils.remove(rowList, x+1);
                break;
            }
        }
        if (simpleForm){
            return rowList;
        } else{
            return simpleRow(newRowList);
        }
    }
    public final void makeStructuresDict(){
        /*
        Asigns a pawn structure fen with its corresponding name
        */
        pawnStructures.put("8/pp3ppp/2p1p3/8/3P4/8/PPP2PPP/8", "Caro-Kann");
        pawnStructures.put("8/pp3ppp/2p1p3/8/3P4/4P3/PP3PPP/8", "Slav");
        pawnStructures.put("8/pp3ppp/3pp3/8/4P3/8/PPP2PPP/8", "S. Scheveningen");
        pawnStructures.put("8/pp2pp1p/3p2p1/8/4P3/8/PPP2PPP/8", "S. Dragon");
        pawnStructures.put("8/pp2pppp/3p4/8/2P1P3/8/PP3PPP/8", "S. Maroczy");
        pawnStructures.put("8/5ppp/pp1pp3/8/2P1P3/8/PP3PPP/8", "Hedgehog");
        pawnStructures.put("8/pp3ppp/3p4/4p3/4P3/8/PPP2PPP/8", "S. Boleslavsky Hole");
        pawnStructures.put("8/ppp2ppp/3p4/3Pp3/4P3/8/PPP2PPP/8", "d5 Chain");
        pawnStructures.put("8/ppp2ppp/4p3/3pP3/3P4/8/PPP2PPP/8", "e5 Chain");
        pawnStructures.put("8/pp3ppp/2p5/4p3/2P1P3/8/PP3PPP/8", "KI Rauzer");
        pawnStructures.put("8/pp3ppp/2pp4/8/2P1P3/8/PP3PPP/8", "KI Boleslavsky Wall");
        pawnStructures.put("8/pp3ppp/4p3/8/3P4/8/PP3PPP/8", "QG Isolani");
        pawnStructures.put("8/pp3ppp/4p3/8/2PP4/8/P4PPP/8", "QG Hanging Pawns");
        pawnStructures.put("8/pp3ppp/2p5/3p4/3P4/4P3/PP3PPP/8", "QG Orthodox Exchange");
        pawnStructures.put("8/pp3ppp/4p3/2Pp4/3P4/8/PP3PPP/8", "Panov");
        pawnStructures.put("8/ppp3pp/4p3/3p1p2/3P1P2/4P3/PPP3PP/8", "Stonewall");
        pawnStructures.put("8/pp2pppp/3p4/2p5/4P3/3P4/PPP2PPP/8", "Closed Sicilian");
        pawnStructures.put("8/pp3ppp/2p1p3/8/3P4/2P5/PP3PPP/8", "Caro-Kann (Rios)");
        pawnStructures.put("8/ppp2ppp/8/3p4/3P4/4P3/PP3PPP/8", "Carlsbad (Rios)");
        pawnStructures.put("8/ppp3pp/4p3/3p1p2/3P4/8/PPP1PPPP/8", "Stonewall (Rios)");
        pawnStructures.put("8/pp2pppp/8/2p5/3PP3/2P5/P4PPP/8", "Grünfeld Centre (Rios)");
        pawnStructures.put("8/pp3ppp/3p4/3Pp3/8/8/PPP2PPP/8", "Najdorf I (Rios)");
        pawnStructures.put("8/pp3ppp/3p4/4p3/4P3/8/PPP2PPP/8", "Najdorf II (Rios)");
        pawnStructures.put("8/pp2pp1p/3p2p1/8/2P1P3/8/PP3PPP/8", "Maroczy (Rios)");
        pawnStructures.put("8/pp3ppp/3p4/2pP4/4P3/8/PP3PPP/8", "Asymmetric Benoni (Rios)");
        pawnStructures.put("8/pp3ppp/3p4/2pP4/2P5/8/PP3PPP/8", "Symmetric Benoni (Rios)");
        pawnStructures.put("8/pp3ppp/3p4/3Pp3/4P3/8/PP3PPP/8", "KID I (Rios)");
        pawnStructures.put("8/pp3ppp/3p4/2pPp3/2P1P3/8/PP3PPP/8", "KID II (Rios)");
        pawnStructures.put("8/ppp2ppp/3p4/3Pp3/2P1P3/8/PP3PPP/8", "KID III (Rios)");
        pawnStructures.put("8/ppp2ppp/3p4/8/2P1P3/8/PP3PPP/8", "Open KID (Rios)");
        pawnStructures.put("8/pp3ppp/2pp4/4p3/2PPP3/8/PP3PPP/8", "KID Complex (Rios)");
        pawnStructures.put("8/ppp3pp/4p3/3p4/3P4/8/PPP2PPP/8", "French I (Rios)");
        pawnStructures.put("8/pp3ppp/4p3/3pP3/8/2P5/PP3PPP/8", "French II (Rios)");
        pawnStructures.put("8/ppp2ppp/4p3/3pP3/3P4/8/PPP2PPP/8", "French III (Rios)");
        pawnStructures.put("8/pp3ppp/4p3/8/8/2P5/PP3PPP/8", "3-3 vs. 4-2 (Rios)");
        pawnStructures.put("8/pp2pppp/8/2Pp4/3P4/8/PP3PPP/8", "Panov (Rios)");
        pawnStructures.put("8/4pp1p/3p2p1/2pP4/4P3/8/PP3PPP/8", "Benko (Rios)");
        pawnStructures.put("8/p4ppp/3p4/1p1Pp3/2p1P3/2P5/PP3PPP/8", "Closed Ruy Lopez (Rios)");
        pawnStructures.put("8/pp3ppp/3p4/2p1p3/3PP3/2P5/PP3PPP/8", "Lopez (Rios)");
    }
    public final String whichStructure(String pawnStructure){
        /*
        Returns the name of the structure in the dict pawnStructures.
        If there is no matching Structure searches for matching structures 
        when comparing without taking into account lateral pawns.
        */
        String name = "Other";
        //Searches matching structures. If no structure is found compares without
        //taking into account a and h pawns.
        if(pawnStructures.keySet().contains(pawnStructure)){
            name = pawnStructures.get(pawnStructure);
        } else{
            for(String posibleStructure : pawnStructures.keySet()){
                if(isSimilarStructure(posibleStructure, pawnStructure, false)){
                    name = pawnStructures.get(posibleStructure);
                    break;
                } 
            }
        }
        //If no structure was found searches without taking into account a,b and h
        //pawns.
        if(name.equals("Other")){
            for(String posibleStructure : pawnStructures.keySet()){
                if(isSimilarStructure(posibleStructure, pawnStructure, true)){
                    name = pawnStructures.get(posibleStructure);
                    break;
                } 
            }
        }
        return name;
    }
    public static boolean isSimilarStructure(String structureA, String structureB, boolean removeBGpawn){
        String[] rowsA = structureA.split(" ")[0].split("/");
        String[] rowsB = structureB.split(" ")[0].split("/");
        boolean similar = true;
        int x;
        for(x=0; x<8; x++){
            String aRowNoLat = rowRemoveLateralPawns(rowsA[x], removeBGpawn);
            String bRowNoLat = rowRemoveLateralPawns(rowsB[x], removeBGpawn);
            if (!(aRowNoLat.equals(bRowNoLat))){
                similar = false;
                break;
            }
        }
        return similar;
    }
    
    public static String rowRemoveLateralPawns(String rowString, boolean removeBGpawn){
        /*
        Takes a string representing a row in a fen and returns a string representing the
        same row with the lateral pawns removed
        */
        char[] noLatPawnsRowChars = rowString.toCharArray();
        
        //remove a and b pawns
        if (Character.isDigit(noLatPawnsRowChars[0])){
            if (Character.getNumericValue(noLatPawnsRowChars[0]) == 1 && removeBGpawn){
                noLatPawnsRowChars[1] = '1';
            }
        } else {
            noLatPawnsRowChars[0] = '1';
            if (Character.isLetter(noLatPawnsRowChars[1]) && removeBGpawn){
                noLatPawnsRowChars[1] = '1';
            }
        }
        
        //remove h and g pawns
        char last = noLatPawnsRowChars[noLatPawnsRowChars.length - 1];
        if (Character.isDigit(last)){
            if (Character.getNumericValue(last) == 1 && removeBGpawn){
                noLatPawnsRowChars[noLatPawnsRowChars.length - 2] = '1';
            }
        } else {
            noLatPawnsRowChars[noLatPawnsRowChars.length - 1] = '1';
            if (Character.isLetter(noLatPawnsRowChars[noLatPawnsRowChars.length - 2]) && removeBGpawn){
                noLatPawnsRowChars[noLatPawnsRowChars.length - 2] = '1';
            }
        }
        
        String resultRow = new String(simpleRow(noLatPawnsRowChars));
        return resultRow;
    }
}
