package com.tennis.repository;

import com.tennis.model.Match;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

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

    public List<Match> getMatches() {
        try(Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Match m", Match.class).getResultList();
        }
    }

    public Match getMatchByNames(String firstName, String secondName) {

        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            //TODO скорее всего немного переделать HQL запрос
            Match match = session.createQuery("SELECT m FROM Match m WHERE m.Player1 = :firstName AND m.Player2 = :secondName", Match.class)
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
