package com.swiatek.mateusz;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DbSchemaBuilder {
    static void connectToDb(){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session ses = sessionFactory.openSession();
        DBFeeder.deleteAll();
        DBFeeder.feedUsers();
        DBFeeder.feedMovies();
//        DBFeeder.feedTags();
        DBFeeder.check();
        ses.close();
    }

    public static void main(String[] args) {
        connectToDb();
    }
}

/*
Q1 select * from users where id in (select id from ratings order by count(*) desc limit 10);
Q2 select id, title from movies natural join tags where tags = 'Drama' limit 100;
...
 */