package com.tennis.dto;

import com.tennis.model.Match;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MatchesPageDto {
    private int page;
    private int totalPages;
    private List<Match> matchesForPage;
}
