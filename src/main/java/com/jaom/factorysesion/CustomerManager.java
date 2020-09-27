package com.jaom.factorysesion;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.jaom.model.Customer;

public class CustomerManager {
	
    protected SessionFactory sessionFactory;
    
    protected void setup() {
        // code to load Hibernate Session factory
    	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
    			.configure() //configure settings from hibernate.cfg.xml
    			.build();
    	
    	try {
    		sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    		
    	}catch(Exception ex) {
    		StandardServiceRegistryBuilder.destroy(registry);
    	}
    	
    	
    }
 
    protected void exit() {
        // code to close Hibernate Session factory
    	sessionFactory.close();
    }
 
    protected void create() {
        // code to save a customer
    	Customer newCustomer = new Customer();
    	newCustomer.setName("Daniel");
    	newCustomer.setEmail("dany@ocampo.com");
    	newCustomer.setAddress("Queretaro");
    	
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	
    	session.save(newCustomer);
    	
    	session.getTransaction().commit();
    	session.close();
    }
 
    protected void read() {
        // code to get a customer
    	Session session = sessionFactory.openSession();
    	
    	long id = 1;
    	Customer customer = session.get(Customer.class, id);
    	System.out.println("Name: "+ customer.getName());
    	System.out.println("Email: "+ customer.getEmail());
    	System.out.println("Address: "+ customer.getAddress());
    	
    	session.close();
    	
    }
 
    protected void update() {
        // code to modify a customer
    	Customer customer = new Customer();
    	customer.setId(1);
    	customer.setName("Jose Abraham");
    	customer.setEmail("jose.ocampo@gmail.com");
    	customer.setAddress("av. canal interceptorsito");
    	
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	session.update(customer);
    	session.getTransaction().commit();
    	session.close();
    }
 
    protected void delete() {
        // code to remove a customer
    	Customer customer = new Customer();
    	customer.setId(1);
    	
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	session.delete(customer);
    	session.getTransaction().commit();
    	session.close();
    }
 
    public static void main(String[] args) {
        // code to run the program
    	CustomerManager manager = new CustomerManager();
    	manager.setup();
    	
    	//manager.create();
    	//manager.read();
    	//manager.update();
    	manager.delete();
    	
    	manager.exit();
    }

}
