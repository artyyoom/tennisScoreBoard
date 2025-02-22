package com.tennis.repository;

import com.tennis.exception.DataBaseException;
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
            session.beginTransaction();
            List<Match> matches = session.createQuery("FROM Match m", Match.class).getResultList();
            session.getTransaction().commit();
            return matches;
        }
    }

    public void saveMatch(Match match) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(match);
            session.getTransaction().commit();
        }
        catch (Exception e) {
            throw new DataBaseException("Error with saving match");
        }
    }
}
