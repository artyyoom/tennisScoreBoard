package com.tennis;

import com.tennis.model.Match;
import com.tennis.model.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateRunner {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Player player1 = Player.builder()
                    .name("arttt1")
                    .build();
//            Player player2 = Player.builder()
//                    .name("arttt2")
//                    .build();
//
//            session.persist(player1);
//            session.persist(player2);
//
//            Match match = Match.builder()
//                    .Player1(player1)
//                    .Player2(player2)
//                    .Winner(player1)
//                    .build();
//            session.persist(match);

            session.getTransaction().commit();
        }
    }
}
