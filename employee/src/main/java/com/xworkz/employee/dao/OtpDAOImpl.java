package com.xworkz.employee.dao;

import com.xworkz.employee.entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class OtpDAOImpl implements OtpDAO {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public boolean saveOtp(String emailId, String otp) {
        System.out.println("Saving OTP for: " + emailId);
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            Query query = em.createNamedQuery("findEmail");
            query.setParameter("email", emailId);
            EmployeeEntity entity = (EmployeeEntity) query.getSingleResult();

            entity.setOtp(otp);
            em.merge(entity);
            tx.commit();

            System.out.println("OTP saved in DB: " + otp);
            return true;
        } catch (Exception e) {
            System.out.println("Error saving OTP: " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public String getOtp(String emailId) {
        System.out.println("Fetching OTP for: " + emailId);
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            Query query = em.createNamedQuery("findEmail");
            query.setParameter("email", emailId);
            EmployeeEntity entity = (EmployeeEntity) query.getSingleResult();
            return entity.getOtp();
        } catch (Exception e) {
            System.out.println("Error fetching OTP: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean clearOtp(String emailId) {
        System.out.println("Clearing OTP for: " + emailId);
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            Query query = em.createNamedQuery("findEmail");
            query.setParameter("email", emailId);
            EmployeeEntity entity = (EmployeeEntity) query.getSingleResult();

            entity.setOtp(null);
            em.merge(entity);
            tx.commit();

            System.out.println("OTP cleared from DB");
            return true;
        } catch (Exception e) {
            System.out.println("Error clearing OTP: " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }
}