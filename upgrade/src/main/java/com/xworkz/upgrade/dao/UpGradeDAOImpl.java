package com.xworkz.upgrade.dao;

import com.xworkz.upgrade.entity.UpGradeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class UpGradeDAOImpl implements UpGradeDAO {
    @Autowired
    EntityManagerFactory entityManagerFactory;



    @Override
    public boolean saveUpGradeData(UpGradeEntity upGradeEntity) {
        System.out.println("UpGrade Details are saved : " + upGradeEntity);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(upGradeEntity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            entityManager.close();
        }
    }




    //Checks  whether an email is already present using a JPQL query.
    @Override
    public UpGradeEntity isEmailExist(String email) {
        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            EntityTransaction transaction = em.getTransaction();
            Query query = em.createNamedQuery("countByEmail");
            query .setParameter("email", email);
            UpGradeEntity singleResult =(UpGradeEntity) query.getSingleResult();
            return singleResult;
        } catch (Exception e) {
            System.out.println("Email is NOt found" + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }





    
    //Once Got registered,If user want to login , it check email and password ,
    // when it matches it will logIn ,otherwise shows No User Found
    @Override
    public UpGradeEntity validateLogin(String email) {
        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            Query query = em.createNamedQuery("findByEmail");
            query.setParameter("email", email);
          UpGradeEntity upGradeEntity = (UpGradeEntity) query.getSingleResult();
          transaction.commit();
          return upGradeEntity;
        } catch (Exception e) {
            System.out.println("User Not Found" + e.getMessage());
            return null;
        } finally {
            em.close();
        }

    }
}