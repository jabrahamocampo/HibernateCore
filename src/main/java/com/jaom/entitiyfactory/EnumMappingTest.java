package com.jaom.entitiyfactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.jaom.model.Customer;
import com.jaom.model.Customer.Gender;

public class EnumMappingTest {

	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("SalesDB");
	private EntityManager entityManager = factory.createEntityManager();
	
	public static void main(String[] args) {
		EnumMappingTest enumTest = new EnumMappingTest();
		enumTest.save();
	}
	
	private void save() {
		entityManager.getTransaction().begin();
		
		Customer customer = new Customer(); 
		customer.setName("Josele");		
		customer.setEmail("josele@gmail.com");
		customer.setAddress("Ravinia");
		customer.setGender(Gender.FEMALE);
		
		entityManager.persist(customer);
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}

}
