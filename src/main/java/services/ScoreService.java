package services;

import model.Position;

import java.util.List;

import constants.GameConfig;

public class ScoreService {
    private int score = 0;
    
    public void addScore(List<Position> matches) {
        int points = matches.size() * GameConfig.POINTS_PER_BLOCK;
        score += points;
        System.out.println("Scored: +" + points);
    }

    public int getScore() {
        return score;
    }
}