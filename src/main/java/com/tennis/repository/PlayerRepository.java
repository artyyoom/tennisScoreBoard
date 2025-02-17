package com.tennis.repository;

import com.tennis.model.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PlayerRepository {

    private static PlayerRepository instance;
    private final SessionFactory sessionFactory;

    private PlayerRepository() {
        Configuration configuration = new Configuration();
        configuration.configure();
        this.sessionFactory = configuration.buildSessionFactory();
    }

    public static PlayerRepository getInstance() {
        if (instance == null) {
            instance = new PlayerRepository();
        }
        return instance;
    }

    public Player getPlayerById(Long id) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Player player = session.get(Player.class, id);
            session.getTransaction().commit();
            return player;
        }
    }

    public void savePlayer(Player player) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(player);
            session.getTransaction().commit();
        }
    }
}
