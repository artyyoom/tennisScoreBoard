package com.tennis.util;

import com.tennis.exception.*;
import com.tennis.service.PlayerService;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DataValidator {

    PlayerService playerService = PlayerService.getInstance();

    public void checkName(String name) {
        //TODO написать во фронтенде критерии имени
        if (!name.matches("^[A-Za-zа-яА-Я0-9_]{1,20}$")) {
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
