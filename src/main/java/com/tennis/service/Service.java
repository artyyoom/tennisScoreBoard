package com.tennis.service;

import com.tennis.model.Match;
import com.tennis.model.Player;
import com.tennis.repository.Repository;

public class Service {

    private static Service instance;

    private Service() {}

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    Repository repository = Repository.getInstance();

    public void savePlayer(String reqPlayer) {
        //TODO проверить на существование
        Player player1 = Player.builder()
                .name(reqPlayer)
                .build();
        repository.savePlayer(player1);
    }

    public Long getPlayerIdByName(String playerName) {
        return repository.getPlayerIdByName(playerName);
    }
}
