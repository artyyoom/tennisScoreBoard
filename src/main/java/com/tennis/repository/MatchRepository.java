package com.tennis.repository;

import com.tennis.model.Match;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MatchRepository {

    private static MatchRepository instance;
    private SessionFactory sessionFactory;

    private MatchRepository() {
        Configuration configuration = new Configuration();
        configuration.configure();
        this.sessionFactory = configuration.buildSessionFactory();
    }

    public static MatchRepository getInstance() {
        if (instance == null) {
            instance = new MatchRepository();
        }
        return instance;
    }

    public void saveMatch(Match match) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(match);
            session.getTransaction().commit();
        }
    }
}
