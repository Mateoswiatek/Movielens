package com.swiatek.mateusz;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.swiatek.mateusz.model.Movie;
import com.swiatek.mateusz.model.Tag;
import com.swiatek.mateusz.model.User;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;

public class DBFeeder {
    static void deleteAll() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session ses = sessionFactory.openSession();
        Transaction t = ses.beginTransaction();

        Query query = ses.createQuery("delete User");
        int result = query.executeUpdate();
        if (result > 0) {
            System.out.println("Users were removed");
        }
        t.commit();
        ses.close();
    }

    static void feedUsers() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session ses = sessionFactory.openSession();
        Transaction t = ses.beginTransaction();

        CSVReader reader;
        try {
            reader = new CSVReader(new FileReader("users.csv"));
            String[] nextLine;
            reader.readNext(); // header
            while((nextLine = reader.readNext()) != null) {
                User user = User.builder()
                        .id(Long.valueOf(nextLine[0]))
                        .forename(nextLine[1])
                        .surname(nextLine[2])
                        .email(nextLine[3])
                        .build();
                ses.persist(user); // bo save jest przestazale?
//                ses.save(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        t.commit();
        ses.close();
    }
    static void feedMovies() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session ses = sessionFactory.openSession();
        Transaction t = ses.beginTransaction();

        CSVReader reader;
        try {
            reader = new CSVReader(new FileReader("movies.csv"));
            String[] nextLine;
            reader.readNext(); // header
            while((nextLine = reader.readNext()) != null) {
                Movie movie = Movie.builder()
                        .id(Long.valueOf(nextLine[0]))
                        .title(nextLine[1])
                        .build();

                ses.persist(movie); // bo save jest przestazale?
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        t.commit();
        ses.close();
    }
    static void feedTags() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session ses = sessionFactory.openSession();
        Transaction t = ses.beginTransaction();

        CSVReader reader;
        try {
            reader = new CSVReader(new FileReader("tags.csv"));
            String[] nextLine;
            reader.readNext(); // header
            while((nextLine = reader.readNext()) != null) {
                User user = ses.get(User.class, Long.parseLong(nextLine[0]));
                Movie movie = ses.get(Movie.class, Long.parseLong(nextLine[1]));
                System.out.println(user.getId());

                Tag tag = Tag.builder()
                        .user(user)
                        .movie(movie)
                        .tag(nextLine[2])
                        .date(new Date(Long.parseLong(nextLine[3])*1000))
                        .build();
                tag.tagId = new Tag.TagId(movie, user);

                System.out.println(tag);
//                ses.persist(tag); // bo save jest przestazale?
//                ses.save(movie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        t.commit();
        ses.close();
    }
//    static void feedRatings()

}
