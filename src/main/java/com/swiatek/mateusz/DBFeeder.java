package com.swiatek.mateusz;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.swiatek.mateusz.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.FileReader;
import java.io.IOException;

public class DBFeeder {

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
//                ses.persist(user); // bo save jest przestazale?
                ses.save(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        t.commit();
        ses.close();
    }
//    static void feedMovies()
//    static void feedTags()
//    static void feedRatings()

}
