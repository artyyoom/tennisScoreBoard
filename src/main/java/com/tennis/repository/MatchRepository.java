package com.tennis.repository;

import com.tennis.model.Match;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MatchRepository {

    private static MatchRepository instance;
    private final SessionFactory sessionFactory;

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

    public Match getMatchByNames(String firstName, String secondName) {

        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Match match = session.createQuery("SELECT * FROM Match m WHERE m.Player1 = :firstName AND m.Player2 = :secondName", Match.class)
                    .setParameter("firstName", firstName)
                    .setParameter("secondName", secondName)
                    .uniqueResult();
            session.getTransaction().commit();
            return match;
        }
    }

    public void saveMatch(Match match) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(match);
            session.getTransaction().commit();
        }
    }
}
