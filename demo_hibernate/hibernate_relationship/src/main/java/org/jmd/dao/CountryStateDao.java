package org.jmd.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.jmd.model.Country;
import org.jmd.model.States;

import java.util.List;

public class CountryStateDao {
    EntityManagerFactory entityManagerFactory = null;

    public  CountryStateDao(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("shiv");
    }

    private EntityManager getEntityManage(){
        return entityManagerFactory.createEntityManager();
    }

    public  Country findCountryId(Long countryId){
        EntityManager entityManager = getEntityManage();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        Country country = null;
        try {
            country = entityManager.find(Country.class, countryId);

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return  country;
    }

    public Country addStateToCountry(Long countryId, List<States> statesList){
        EntityManager entityManager = getEntityManage();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        Country country = null;
        try{
            country = findCountryId(countryId);

            if(country == null){
                throw new RuntimeException("country not found, please chesk");
            }

            entityTransaction.begin();

            for(States states : statesList){
                states.setCountry(country);
                entityManager.persist(states);
            }

            entityTransaction.commit();


        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return country;
    }

    public States addState(States states, Country country){
        EntityManager entityManager = getEntityManage();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try{
            entityTransaction.begin();

            entityManager.persist(country);

            states.setCountry(country);
            entityManager.persist(states);

            entityTransaction.commit();
        }catch (Exception e){
            if(entityManager.isOpen()){
                entityTransaction.rollback();
            }
            throw new IllegalStateException("Something went wrong "+ e.getMessage());
        }
        return states;
    }
}
