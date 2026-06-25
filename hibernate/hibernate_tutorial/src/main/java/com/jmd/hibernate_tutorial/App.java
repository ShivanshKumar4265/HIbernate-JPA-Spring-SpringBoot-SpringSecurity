package com.jmd.hibernate_tutorial;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
//        SessionFactory factory =new Configuration().configure().buildSessionFactory();
//
//        Session session = factory.openSession();
//
//        Transaction tx = session.beginTransaction();
//
//        tx.commit();
//
//        session.close();
//        factory.close();

        System.out.println("Hibernate Connected Successfully!");
    }
}
