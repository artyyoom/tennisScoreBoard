package com.tennis.model;

import lombok.Data;

@Data
public class Score {
    private int set = 0;
    private int game = 0;
    private int point = 0;
}
