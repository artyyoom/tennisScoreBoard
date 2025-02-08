package com.tennis.service;

import com.tennis.model.Player;
import com.tennis.repository.PlayerRepository;

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
        //TODO проверить на существование
        Player player = new Player();
        player.setName(playerName);
        playerRepository.savePlayer(player);
//        player1.setId(playerId);
        return player;
    }

//    public Long getPlayerIdByName(String playerName) {
//        return repository.getPlayerIdByName(playerName);
//    }
}
