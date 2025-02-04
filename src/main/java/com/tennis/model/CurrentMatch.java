package com.tennis.model;

import com.tennis.dto.CurrentMatchDto;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CurrentMatch {

    private static final Map<UUID, CurrentMatchDto> currentMatchMap = new HashMap<>();

    public static void addCurrentMatch(UUID key, CurrentMatchDto value) {
        currentMatchMap.put(key, value);
    }

    public static CurrentMatchDto getCurrentMatch(UUID key) {
        return currentMatchMap.get(key);
    }

    public static void delCurrentMatch(UUID key) {
        currentMatchMap.remove(key);
    }
}
