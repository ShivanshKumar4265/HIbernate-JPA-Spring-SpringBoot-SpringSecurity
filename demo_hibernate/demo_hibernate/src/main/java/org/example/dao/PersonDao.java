package org.example.dao;


import jakarta.persistence.*;
import org.example.model.Person;
import java.util.List;

public class PersonDao implements AutoCloseable {

    private final EntityManagerFactory emf;

    public PersonDao() {
        this.emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    }

    private EntityManager em() {
        return emf.createEntityManager();
    }

    public Person create(Person p) {
        EntityManager em = em();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(p);
            tx.commit();
            return p;
        } catch (RuntimeException ex) {
            if (tx.isActive()) tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    public Person find(Long id) {
        EntityManager em = em();
        try {
            return em.find(Person.class, id);
        } finally {
            em.close();
        }
    }

    public Person update(Person p) {
        EntityManager em = em();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Person merged = em.merge(p);
            tx.commit();
            return merged;
        } catch (RuntimeException ex) {
            if (tx.isActive()) tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = em();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Person p = em.find(Person.class, id);
            if (p != null) em.remove(p);
            tx.commit();
        } catch (RuntimeException ex) {
            if (tx.isActive()) tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    public List<Person> listAll() {
        EntityManager em = em();
        try {
            em.createQuery("select p from Person p");
            // createQuery(qlString) return QueryIterface
            // createQuery(qlString, resultClass) return TypedQury interface

            Query query =  em.createQuery("SELECT p FROM Person p", Person.class);
            return em.createQuery("SELECT p FROM Person p", Person.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Person findByEmail(String email){
        String findByEmailQuery = "select e from Person e where e.email = :email";
        /**
         * belwo line is used to read the jpql
         */
        TypedQuery<Person> typePerson = em().createQuery(findByEmailQuery, Person.class);
        /**
         *
         *  belwo line is used to set the actual parameter to the @findByEmailQuery
         */
        typePerson.setParameter("email" , email);

        //belwo line is giving the data form db, since it will alsows giveon item so getSingleResult(), if list then getSingle
        Person person = null;

        try{
            person = typePerson.getSingleResult();
        }catch (NoResultException e){
            System.out.println(e.getMessage());
        }
        return  person;
     }

    public Person findBySalary(Double email){
        String findByEmailQuery = "select e from Person e where e.email = :email";
        /**
         * belwo line is used to read the jpql
         */
        TypedQuery<Person> typePerson = em().createQuery(findByEmailQuery, Person.class);
        /**
         *
         *  belwo line is used to set the actual parameter to the @findByEmailQuery
         */
        typePerson.setParameter("email" , email);

        //belwo line is giving the data form db, since it will alsows giveon item so getSingleResult(), if list then getSingle
        Person person = null;

        try{
            person = typePerson.getSingleResult();
        }catch (NoResultException e){
            System.out.println(e.getMessage());
        }
        return  person;
    }


    @Override
    public void close() throws Exception {
        emf.close();
    }
}
