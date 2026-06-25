package org.jmd.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.jmd.model.Student;
import org.jmd.model.Trainer;

import java.util.List;

public class TrainerStudentDao {
    EntityManagerFactory  emf = null;

    public TrainerStudentDao(){
        this.emf = Persistence.createEntityManagerFactory("shiv");
    }

    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }




    public List<Integer> addTrainersAndStudent(List<Integer> trainer, List<Student> students){
        EntityManager em = getEntityManager();
        EntityTransaction etx = em.getTransaction();
        try{
            etx.begin();

            for(int i = 0;i<students.size();i++){
                em.persist(students.get(0));
            }

            for(int i = 0;i<trainer.size();i++){
                Trainer t = em.find(Trainer.class, trainer.get(i));
                t.setStudents(students);
                em.persist(t);
            }
            etx.commit();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        return trainer;
    }
}
