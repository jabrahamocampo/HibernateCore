package com.jaom.entitiyfactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.jaom.model.Customer;

public class EntityFactoryManager {
	
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("SalesDB");
	private EntityManager entityManager = factory.createEntityManager(); 
	
	public static void main(String[] args) {
		EntityFactoryManager entityFactoryManager = new EntityFactoryManager();
		//entityFactoryManager.save();
		//entityFactoryManager.update();
		//entityFactoryManager.read();
		//entityFactoryManager.execueQuery();
		entityFactoryManager.delete();
	}
	
	private void save() {
		this.entityManager.getTransaction().begin();
		
		Customer customer = new Customer();
		customer.setName("Billy Boy");
		customer.setEmail("Billy@boy.com");
		customer.setAddress("city of Derryl");
		
		this.entityManager.persist(customer);
		
		this.entityManager.getTransaction().commit();
		this.entityManager.close();
		this.factory.close();
	}
	
	public void update() {
		System.out.println("UPDATE METHOD");
		
		Customer customer = new Customer();
		customer.setId(2);
		customer.setName("Jose Abraham");
		customer.setEmail("billy.joy@gmail.com");
		customer.setAddress("Amacuzac");
		
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(customer);
		this.entityManager.getTransaction().commit();
		this.entityManager.close();
		this.factory.close();
	}
	
	protected void read() {
		long id = 10;
		Customer customer = entityManager.find(Customer.class, id);
		System.out.println(customer.getName());
	}
	
	protected void delete() {
		long id = 10;
		Customer reference = entityManager.getReference(Customer.class, id);
		
		this.entityManager.getTransaction().begin();
		this.entityManager.remove(reference);
		this.entityManager.getTransaction().commit();
		this.entityManager.close();
		this.factory.close();
	}
	
	protected void execueQuery() {
		String sql = "SELECT c FROM Customer c WHERE c.name = 'Jose Abraham'";
		Query query = entityManager.createQuery(sql);
		Customer customer = (Customer) query.getSingleResult();
		System.out.println(customer.getEmail());
	}

}
