package com.zenika.addressbook.gwt;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.zenika.addressbook.gwt.server.model.Address;
import com.zenika.addressbook.gwt.server.model.Person;
import com.zenika.addressbook.gwt.server.util.SessionFactoryHolder;

public class AdressBookServiceTest {

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testSavePerson() {
		SessionFactoryHolder.getCurrentSession().beginTransaction();
		Person p;
		Address address;
		Set<Address> addresses;

		// person 1
		
		p = new Person();
		p.setFirstname("Guillaume");
		p.setLastname("Giamarchi");
		
		addresses = new HashSet<Address>();
		p.setAddresses(addresses);
		
		address = new Address();
		address.setZipcode("75018");
		address.setCity("Paris");
		addresses.add(address);
		
		address = new Address();
		address.setZipcode("20600");
		address.setCity("Furiani");
		addresses.add(address);

		Person.saveOrUpdate(p);

		// person 2

		p = new Person();
		p.setFirstname("Alexandre");
		p.setLastname("Giamarchi");

		addresses = new HashSet<Address>();
		p.setAddresses(addresses);
				
		address = new Address();
		address.setZipcode("20215");
		address.setCity("Vescovato");
		addresses.add(address);
		
		Person.saveOrUpdate(p);

		SessionFactoryHolder.getCurrentSession().getTransaction().commit();
	}

	@Test
	public void testReadListPerson() {
		SessionFactoryHolder.getCurrentSession().beginTransaction();
		Person person = Person.read("Guillaume", "Giamarchi");
		Assert.assertEquals("Guillaume", person.getFirstname());
		Assert.assertEquals("Giamarchi", person.getLastname());
		SessionFactoryHolder.getCurrentSession().getTransaction().commit();
	}

}
