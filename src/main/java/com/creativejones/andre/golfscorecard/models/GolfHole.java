package com.creativejones.andre.golfscorecard.models;

public class GolfHole {

    private String HoleName;
    private int Score;

    public GolfHole(String name, int strokes) {
        HoleName = name;
        Score = strokes;
    }

    public String getHoleName() {
        return HoleName;
    }

    public void setHoleName(String holeName) {
        HoleName = holeName;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public void Add(){
        Score += 1;
    }

    public void Minus(){
        if(Score > 0) {
            Score -= 1;
        }
    }
}
