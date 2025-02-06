package com.tennis.repository;

import com.tennis.model.Match;
import com.tennis.model.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Repository {

    private static Repository instance;
    private SessionFactory sessionFactory;

    private Repository() {
        Configuration configuration = new Configuration();
        configuration.configure();
        this.sessionFactory = configuration.buildSessionFactory();
    }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    public Long getPlayerIdByName(String playerName) {

        Long playerId = null;

        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            playerId = session.createQuery("SELECT p.id FROM Player p WHERE p.name = :name",Long.class)
                            .setParameter("name", playerName)
                            .uniqueResult();
            session.getTransaction().commit();
        }
        return playerId;
    }

    public Long savePlayer(Player player) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(player);
            Long id = player.getId();
            session.getTransaction().commit();
            return id;
        }
    }
}
