package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.model.User;

public class UserDao {
    private  final EntityManagerFactory  emf;

    UserDao() {
        this.emf = Persistence.createEntityManagerFactory("Shivansh");
    }

    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    public User createUser(User user){
        EntityManager em = getEntityManager();

        return  user;
    }

}
