package com.xworkz.employee.dao;

import com.xworkz.employee.entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.time.LocalDateTime;

@Repository
public class OtpDAOImpl implements OtpDAO {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    // SAVE OTP + EXPIRY TIME into  MYSQLDB
    @Override
    public boolean saveOtp(String emailId, String otp, LocalDateTime expiry) {
        System.out.println("Saving OTP for: " + emailId
                + " OTP: " + otp
                + "Expiry: " + expiry);
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            Query query = em.createNamedQuery("findEmail");
            query.setParameter("email", emailId);
            EmployeeEntity entity = (EmployeeEntity) query.getSingleResult();

            entity.setOtp(otp);
            entity.setOtpExpiry(expiry);
            em.merge(entity);
            tx.commit();

            System.out.println("OTP and expiry saved in DB successfully");
            return true;
        } catch (Exception e) {
            System.out.println("Error saving OTP: " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    //READ OTP from DB
    @Override
    public String getOtp(String emailId) {
        System.out.println("Fetching OTP for: " + emailId);
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            Query query = em.createNamedQuery("findEmail");
            query.setParameter("email", emailId);
            EmployeeEntity entity = (EmployeeEntity) query.getSingleResult();
            System.out.println("Fetched OTP: " + entity.getOtp());
            return entity.getOtp();
        } catch (Exception e) {
            System.out.println("Error fetching OTP: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    //  READ EXPIRY TIME from DB
    @Override
    public LocalDateTime getOtpExpiry(String emailId) {
        System.out.println("Fetching OTP expiry for: " + emailId);
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            Query query = em.createNamedQuery("findEmail");
            query.setParameter("email", emailId);
            EmployeeEntity entity = (EmployeeEntity) query.getSingleResult();
            System.out.println("Fetched expiry: " + entity.getOtpExpiry());
            return entity.getOtpExpiry();
        } catch (Exception e) {
            System.out.println("Error fetching OTP expiry: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    //CLEAR OTP
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
            entity.setOtpExpiry(null);
            em.merge(entity);
            tx.commit();

            System.out.println("OTP and expiry cleared from DB");
            return true;
        } catch (Exception e) {
            System.out.println("Error clearing OTP: " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }
}