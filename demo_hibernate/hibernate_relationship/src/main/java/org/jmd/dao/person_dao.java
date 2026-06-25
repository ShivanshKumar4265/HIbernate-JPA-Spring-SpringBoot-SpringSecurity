package org.jmd.dao;

import jakarta.persistence.*;
import org.jmd.model.Aadhar;
import org.jmd.model.Person;
import org.jmd.util.UniqueString;

import java.util.List;

public class person_dao implements AutoCloseable {
    private EntityManagerFactory emf;

    public person_dao(){
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    }

    private EntityManager getEm(){
        return  emf.createEntityManager();
    }


    public Person savePerson(Person person , Aadhar aadhar){
        EntityManager em = getEm();
        EntityTransaction entityTransaction = em.getTransaction();
        try{
            entityTransaction.begin();

            if (aadhar.getAadharNumber() == null) {
                aadhar.setAadharNumber(Long.valueOf(UniqueString.getUniqueAadhar()));
            }
            em.persist(aadhar);

            person.setAadhar(aadhar);
            em.persist(person);

            entityTransaction.commit();

        }catch (RuntimeException e){
            if(entityTransaction.isActive()){
                entityTransaction.rollback();
            }
            throw e;
        }finally {
            em.close();
        }
        return  person;
    }

    public Person getPersonById(Long id) {
        EntityManager em = getEm();
        try {
            return em.find(Person.class, id);
        } finally {
            em.close();
        }
    }

    public List<Person> getAllPersons() {

        /**
         * Why this happensSQL: Works with database tables and columns (SELECT * FROM table_name).
         * JPQL: Works with Java objects and fields.
         * select p from Person p tells Hibernate/JPA: "Select the full Java object instance p from the Entity class Person, where p represents each row."
         */
        EntityManager em = getEm();
        try{
            TypedQuery<Person>  personTypedQuery = em.createQuery("select p from Person p", Person.class);
            return  personTypedQuery.getResultList();
        }catch (RuntimeException e){
            throw  e;
        }
    }

    public Person updatePerson(Long id, Person updatedPerson, Aadhar updatedAadhar) {
        EntityManager em = getEm();
        EntityTransaction entityTransaction = em.getTransaction();
        try {
            entityTransaction.begin();
            Person existingPerson = em.find(Person.class, id);
            if (existingPerson == null) {
                entityTransaction.rollback();
                return null;
            }

            existingPerson.setName(updatedPerson.getName());
            existingPerson.setEmail(updatedPerson.getEmail());

            if (updatedAadhar != null) {
                if (existingPerson.getAadhar() == null) {
                    if (updatedAadhar.getAadharNumber() == null) {
                        updatedAadhar.setAadharNumber(Long.valueOf(UniqueString.getUniqueAadhar()));
                    }
                    existingPerson.setAadhar(updatedAadhar);
                } else {
                    existingPerson.getAadhar().setAddress(updatedAadhar.getAddress());
                    existingPerson.getAadhar().setDob(updatedAadhar.getDob());
                }
            }

            Person merged = em.merge(existingPerson);
            entityTransaction.commit();
            return merged;
        } catch (RuntimeException e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public boolean deletePerson(Long id) {
        EntityManager em = getEm();
        EntityTransaction entityTransaction = em.getTransaction();
        try {
            entityTransaction.begin();
            Person person = em.find(Person.class, id);
            if (person == null) {
                entityTransaction.rollback();
                return false;
            }
            em.remove(person);
            entityTransaction.commit();
            return true;
        } catch (RuntimeException e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }


    @Override
    public void close() throws Exception {
        emf.close();
    }
}
