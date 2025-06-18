/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author adria_6
 */
import java.io.*;
import java.util.concurrent.*;

public class StockfishEngine {
    
    private Process stockfishProcess;
    private BufferedReader reader;
    private BufferedWriter writer;
    
    public StockfishEngine(String stockfishPath) throws IOException {
        // Start the Stockfish process
        ProcessBuilder processBuilder = new ProcessBuilder(stockfishPath);
        stockfishProcess = processBuilder.start();

        // Get the input/output streams for communication
        reader = new BufferedReader(new InputStreamReader(stockfishProcess.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(stockfishProcess.getOutputStream()));

        // Initialize Stockfish by sending UCI command
        sendCommand("uci");
        readResponse();
        System.out.println("Engine Initialized");
    }
    
    // Sends a command to Stockfish and waits for a response
    private void sendCommand(String command) throws IOException {
        writer.write(command + "\n");
        writer.flush();
    }
    
    // Reads a response from Stockfish
    private String readResponse() throws IOException {
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line).append("\n");
            // UCI protocol response ends with "uciok"
            System.out.println(line.equals(""));
            if (line.equals("uciok")) {
                break;
            }
        }
        return response.toString();
    }
    
    // Set up position (can be custom starting position or FEN)
    public void setPosition(String fen) throws IOException {
        System.out.println("setPosition called");
        sendCommand("position fen " + fen);
        System.out.println("fen parsed");
        //readResponse();
    }
    // Calculate the best move for the current position
    public String getBestMove(int timeLimitMillis) throws IOException, InterruptedException {
        // Set the thinking time (timeLimitMillis is the maximum time in milliseconds)
        sendCommand("go movetime " + timeLimitMillis);
        String bestMove = null;

        // Read the response for the best move
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            if (line.startsWith("bestmove")) {
                bestMove = line.split(" ")[1];
                break;
            }
        }
        return bestMove;
    }
    // Calculate the evaluation for the current position
    public Evaluation getEvaluation(int timeLimitMillis) throws IOException, InterruptedException {
        // Set the thinking time (timeLimitMillis is the maximum time in milliseconds)
        sendCommand("go movetime " + timeLimitMillis);
        Evaluation evaluation = new Evaluation("cp", 0);
        // Read the response for the best move
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            if (line.startsWith("info depth")){
                String[] lineSplit = line.split("score ")[1].split(" ");
                if (lineSplit[0].equals("cp")){
                    evaluation.setCpScore(Double.parseDouble(lineSplit[1])/100);
                } else if (lineSplit[0].equals("mate")){
                    evaluation.setScoreType("mate");
                    evaluation.setMateScore(Integer.parseInt(lineSplit[1]));
                }
            }
            if (line.startsWith("bestmove")) {
                break;
            }
        }
        return evaluation;
    }
    
    

    // Close the Stockfish process
    public void close() throws IOException {
        sendCommand("quit");
        reader.close();
        writer.close();
        stockfishProcess.destroy();
    }
}
