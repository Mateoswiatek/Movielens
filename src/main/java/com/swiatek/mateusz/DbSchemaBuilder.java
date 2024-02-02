package com.swiatek.mateusz;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DbSchemaBuilder {
    static void connectToDb(){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session ses = sessionFactory.openSession();
        DBFeeder.deleteAll();
//        DBFeeder.feedUsers();
//        DBFeeder.feedMovies();
        // DBFeeder.feedTags();
        ses.close();
    }

    public static void main(String[] args) {
        connectToDb();
    }
}