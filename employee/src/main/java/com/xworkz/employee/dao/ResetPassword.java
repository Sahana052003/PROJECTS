package com.xworkz.employee.dao;


import com.xworkz.employee.entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

@Repository
public class ResetPassword implements LoginPasswordDAO{

    @Autowired
    EntityManagerFactory entityManagerFactory;


    @Override
    public boolean changePassword(String emailId,String password) {
        System.out.println("Changing Password  for th e email : " + emailId);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createNamedQuery("findEmail");
            query.setParameter("email",emailId);
            EmployeeEntity singleResult =(EmployeeEntity) query.getSingleResult();
            singleResult.setPassword(password);
            singleResult.setLoginAttempts(0);
            entityManager.merge(singleResult);
            transaction.commit();
        return true;
    } catch (Exception e) {
            System.out.println("Passwor derror is : " + e.getMessage());
        return false;
        }finally {
            entityManager.close();
        }
        }
}
