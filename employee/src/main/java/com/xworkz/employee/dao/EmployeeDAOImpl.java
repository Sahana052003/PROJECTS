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
            System.out.println("Error while saving a employee data : " + e.getMessage());
        }finally {
            entityManager.close();
        }
    }

    @Override
    public EmployeeEntity findByEmail(String emailId) {
        System.out.println("Finding Emial : " + emailId);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createNamedQuery("findEmail");
            query.setParameter("email",emailId);
            EmployeeEntity employeeEntity = (EmployeeEntity) query.getSingleResult();
            System.out.println("Email found : " + emailId);
            return employeeEntity;

        } catch (Exception e) {
            System.out.println("Email Not found in DB  " + e.getMessage());
            return null;
        }finally {
            entityManager.close();
        }
    }



    @Override
    public EmployeeEntity getDetailsBasedOnId(int id) {


        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            EmployeeEntity employeeEntity = entityManager.find(EmployeeEntity.class, id);
            return employeeEntity;
        } catch (Exception e) {
            System.out.println("Exception in the Data : " + e.getMessage());
            return null;
        } finally {
            entityManager.close();
        }
    }


    @Override
    public boolean updateEmployeeDetails(EmployeeEntity employeeEntity) {
        System.out.println("Updating Employee Data");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(employeeEntity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error While updating an employee data : " + e.getMessage());
            return false;
        } finally {
            entityManager.close();
        }
    }
}
