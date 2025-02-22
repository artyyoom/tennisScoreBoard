package com.tennis.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Score {
    private int set = 0;
    private int game = 0;
    private int point = 0;

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
