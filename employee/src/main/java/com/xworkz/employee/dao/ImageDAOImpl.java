package com.xworkz.employee.dao;

import com.xworkz.employee.entity.EmployeeEntity;
import com.xworkz.employee.entity.ImageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Repository
public class ImageDAOImpl implements ImageDAO {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Override
    public void saveImageFile(EmployeeEntity employeeEntity) {

        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(employeeEntity);
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            System.out.println("Error: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}