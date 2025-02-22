package com.tennis.util;

import com.tennis.exception.*;
import com.tennis.service.PlayerService;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DataValidator {

    PlayerService playerService = PlayerService.getInstance();

    public void checkNames(String firstName, String secondName) {
        if (firstName.equals(secondName)) {
            throw new InvalidDataException("These names are the same");
        }
        checkName(firstName);
        checkName(secondName);
    }

    public void checkName(String name) {
        if (!name.matches("^[A-Za-zа-яА-Я0-9_]{1,15}$")) {
            throw new InvalidDataException("Invalid name");
        }
    }

    public void checkUuid(String initialUuid) {
        if (!initialUuid.matches("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$")) {
            throw new InvalidDataException("Invalid uuid");
        }
    }

    public void checkId(String initialId) {
        if (isPositiveNumber(initialId)) {
            throw new InvalidDataException("Invalid id");
        }
    }

    public void checkPage(String initialPage) {
        if (isPositiveNumber(initialPage)) {
            throw new InvalidDataException("Invalid page");
        }
    }

    private boolean isPositiveNumber(String value) {
        return !value.matches("\\d+");
    }
}
