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

    public Player getPlayer(Long id) {
        return playerRepository.getPlayerById(id);
    }

    public Player savePlayer(String playerName) {
        Optional<Player> existingPlayer = playerRepository.getPlayerByName(playerName);
        if (existingPlayer.isPresent()) {
            return existingPlayer.get(); // Возвращаем существующего игрока
        }
        //TODO проверить на существование
        Player player = new Player();
        player.setName(playerName);
        return playerRepository.savePlayer(player);
    }

//    public Long getPlayerIdByName(String playerName) {
//        return repository.getPlayerIdByName(playerName);
//    }
}
