/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author adria_6
 */
public class Evaluation {
    public String scoreType;
    public int mateScore;
    public double cpScore;

    public Evaluation(String scoreType, int mateScore) {
        this.scoreType = scoreType;
        this.mateScore = mateScore;
    }

    public Evaluation(String scoreType, double cpScore) {
        this.scoreType = scoreType;
        this.cpScore = cpScore;
    }

    public String getScoreType() {
        return scoreType;
    }

    public int getMateScore() {
        return mateScore;
    }

    public double getCpScore() {
        return cpScore;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }

    public void setMateScore(int mateScore) {
        this.mateScore = mateScore;
    }

    public void setCpScore(double cpScore) {
        this.cpScore = cpScore;
    }
}
