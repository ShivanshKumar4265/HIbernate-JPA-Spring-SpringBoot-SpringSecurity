package org.jmd.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.jmd.model.Department;
import org.jmd.model.Employee;

import java.util.List;

public class DepartmentDao {
    EntityManagerFactory emf;

    public DepartmentDao() {
        this.emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public Department saveDepartment(Department department, List<Employee> employee) {
        EntityManager em = getEntityManager();
        EntityTransaction etx = em.getTransaction();

        try {
            etx.begin();
            for (int i = 0; i < employee.size(); i++) {
                em.persist(employee.get(i));
            }

            department.setEmployees(employee);

            em.persist(department);

            etx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (etx.isActive()) {
                etx.rollback();
            }
            throw e;
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
        return department;
    }

    public Department getDepartmenWihtEmmplyeeByDepartmentID(int departmentid) {
        EntityManager em = getEntityManager();
        Department department = null;
        try {
            department = em.find(Department.class, departmentid);
            if (department != null) {
                department.getEmployees().size();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }

        return department;
    }

    // add emplyee and then associate wiht the department
     public Employee addEmployeeToDepartment(int departmentId, Employee employee) {
        EntityManager em = getEntityManager();
        EntityTransaction etx = em.getTransaction();
        Employee savedEmployee = null;

        try {
            etx.begin();

            Department department = em.find(Department.class, departmentId);
            if (department == null) {
                throw new IllegalArgumentException("Department not found with ID: " + departmentId);
            }

            savedEmployee = em.merge(employee);
            department.getEmployees().add(savedEmployee);

            em.merge(department);

            etx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (etx.isActive()) {
                etx.rollback();
            }
            throw e;
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }

        return savedEmployee;
    }
}

// for loop
// date time, update, now, timestap datatime, dat, year, day , month, current_date ,current_time
