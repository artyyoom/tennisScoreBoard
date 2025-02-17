package com.tennis.util;

import com.tennis.exception.*;
import com.tennis.service.CurrentMatchStorage;
import com.tennis.service.PlayerService;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
public class DataValidator {

    PlayerService playerService = PlayerService.getInstance();

    public void checkName(String name) {
        if (name.isEmpty()) {
            throw new InvalidDataException("Name is empty");
        }
        if (name.length() > 20) {
            throw new InvalidDataException("Name is too long");
        }
    }

    public void checkUuid(String initialUuid) {
        UUID uuid = UUID.fromString(initialUuid);
        if (CurrentMatchStorage.getCurrentMatch(uuid) == null) {
            throw new InvalidDataException("Current match not found");
        }
    }

    public void checkId(String initialId) {
        try {
            Long winnerId = Long.valueOf(initialId);
            if (playerService.getPlayer(winnerId) == null) {
                throw new NumberFormatException();
            }
        }
        catch (NumberFormatException e) {
            throw new InvalidDataException("This player not found");
        }
    }

    public void checkPage(String initialPage) {
        try {
            int page = Integer.parseInt(initialPage);
            if (page <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            throw new InvalidDataException("Incorrect page entry");
        }
    }
}
