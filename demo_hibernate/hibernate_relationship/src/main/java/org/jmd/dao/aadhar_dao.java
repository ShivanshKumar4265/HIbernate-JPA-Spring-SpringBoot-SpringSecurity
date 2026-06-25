package org.jmd.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.jmd.model.Aadhar;

import java.util.List;

public class aadhar_dao implements AutoCloseable {
	private final EntityManagerFactory emf;

	public aadhar_dao() {
		this.emf = Persistence.createEntityManagerFactory("my-persistence-unit");
	}

	private EntityManager getEm() {
		return emf.createEntityManager();
	}

	public Aadhar saveAadhar(Aadhar aadhar) {
		EntityManager em = getEm();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.persist(aadhar);
			transaction.commit();
			return aadhar;
		} catch (RuntimeException e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		} finally {
			em.close();
		}
	}

	public Aadhar getAadharById(Long id) {
		EntityManager em = getEm();
		try {
			return em.find(Aadhar.class, id);
		} finally {
			em.close();
		}
	}

	public List<Aadhar> getAllAadhars() {
		EntityManager em = getEm();
		try {
			TypedQuery<Aadhar> query = em.createQuery("select a from Aadhar a order by a.aadharNumber", Aadhar.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	public Aadhar updateAadhar(Long id, Aadhar updatedAadhar) {
		EntityManager em = getEm();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			Aadhar existing = em.find(Aadhar.class, id);
			if (existing == null) {
				transaction.rollback();
				return null;
			}
			existing.setAddress(updatedAadhar.getAddress());
			existing.setDob(updatedAadhar.getDob());
			Aadhar merged = em.merge(existing);
			transaction.commit();
			return merged;
		} catch (RuntimeException e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		} finally {
			em.close();
		}
	}

	public boolean deleteAadhar(Long id) {
		EntityManager em = getEm();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			Aadhar aadhar = em.find(Aadhar.class, id);
			if (aadhar == null) {
				transaction.rollback();
				return false;
			}
			em.remove(aadhar);
			transaction.commit();
			return true;
		} catch (RuntimeException e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		} finally {
			em.close();
		}
	}

	@Override
	public void close() {
		emf.close();
	}
}
