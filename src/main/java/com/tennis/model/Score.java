package com.tennis.model;

import lombok.Getter;

@Getter
public class Score {
    private int set = 2;
    private int game = 5;
    private int point = 2;

    public void addSet() {
        set++;
    }

    public void addGame() {
        game++;
    }

    public void addPoint() {
        point++;
    }

    public void delPoints() {
        point = 0;
    }

    public void delGames() {game = 0;}
}
