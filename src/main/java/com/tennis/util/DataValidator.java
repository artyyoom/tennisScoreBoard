package com.tennis.util;

import com.tennis.exception.*;
import com.tennis.service.CurrentMatchStorage;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
public class DataValidator {

    public void checkName(String name) {
        if (name.isEmpty()) {
            throw new InvalidDataException("Name is empty");
        }
        if (name.length() > 20) {
            throw new InvalidDataException("Name is too long");
        }
    }

    public void checkUuid(UUID uuid) {
        if (CurrentMatchStorage.getCurrentMatch(uuid) == null) {
            throw new InvalidDataException("Current match not found");
        }

    }
}
