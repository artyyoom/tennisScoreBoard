package com.tennis.repository;

import com.tennis.exception.DataBaseException;
import com.tennis.model.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Optional;

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
        } catch (Exception e) {
            throw new DataBaseException("Problem with getting player");
        }
    }

    public Player savePlayer(Player player) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(player);
            session.getTransaction().commit();
            return player;
        }
        catch (Exception e) {
            throw new DataBaseException("Problem with saving player");
        }
    }

    public Optional<Player> getPlayerByName(String playerName) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Optional<Player> player = session.createQuery("from Player where name = :name", Player.class)
                    .setParameter("name", playerName)
                    .uniqueResultOptional();
            session.getTransaction().commit();
            return player;
        }
    }
}
