package com.xworkz.employee.dao;

import com.xworkz.employee.entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{
   @Autowired
    EntityManagerFactory entityManagerFactory;
    @Override
    public void saveEmployeeLoginData(EmployeeEntity employeeEntity) {
        System.out.println("The DAO method is Called");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(employeeEntity);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error in the data : " + e.getMessage());
        }finally {
            entityManager.close();
        }
    }

    @Override
    public EmployeeEntity findByEmail(String emailId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createNamedQuery("findEmail");
            query.setParameter("email",emailId);
            EmployeeEntity employeeEntity = (EmployeeEntity) query.getSingleResult();
            return employeeEntity;

        } catch (Exception e) {
            System.out.println("Check Email : " + e.getMessage());
            return null;
        }finally {
            entityManager.close();
        }
    }
}
