package com.tennis.service;

import com.tennis.model.Player;
import com.tennis.repository.PlayerRepository;

import java.util.Optional;

public class PlayerService {

    private static PlayerService instance;

    private PlayerService() {}

    public static PlayerService getInstance() {
        if (instance == null) {
            instance = new PlayerService();
        }
        return instance;
    }

    PlayerRepository playerRepository = PlayerRepository.getInstance();

    public Player savePlayer(String playerName) {
        Optional<Player> existingPlayer = playerRepository.getPlayerByName(playerName);
        if (existingPlayer.isPresent()) {
            return existingPlayer.get();
        }

        Player player = Player.builder()
                .name(playerName)
                .build();
        return playerRepository.savePlayer(player);
    }
}
