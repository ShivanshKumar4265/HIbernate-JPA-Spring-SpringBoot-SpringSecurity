package org.example.controller;
import org.example.dao.PersonDao;

public class PersonController {
    public static void main(String[] args) throws Exception {
        try (PersonDao dao = new PersonDao()) {
            // Create
//            Person p = new Person(1L,"Alice", "alice@example.com");
//
//            System.out.println(p);
//
//            p.setName("shiv");

//            System.out.println( "After update " + p.toString());

//            dao.listAll();

            System.out.println(dao.findByEmail("alice@example.com"));


//            dao.create(p);
//            System.out.println("Created id = " + p.getId());

            // Read
//            Person found = dao.find(p.getId());
//            System.out.println("Found: " + found.getName() + " / " + found.getEmail());

            // Update
//            found.setEmail("alice.new@example.com");
//            dao.update(found);
//            System.out.println("Updated email.");
//
//            // List
//            dao.listAll().forEach(person ->
//                    System.out.println(person.getId() + " - " + person.getName() + " - " + person.getEmail())
//            );
//
//            // Delete
//            dao.delete(found.getId());
//            System.out.println("Deleted id = " + found.getId());
        }
    }
}

