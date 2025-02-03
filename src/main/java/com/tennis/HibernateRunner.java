package com.tennis;

import com.tennis.entity.Player;
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

            Player player = Player.builder()
                    .name("arttt")
                    .build();
            session.persist(player);

            session.getTransaction().commit();
        }
    }
}
