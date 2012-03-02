package com.zenika.addressbook.gwt.server;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.zenika.addressbook.gwt.server.model.Person;
import com.zenika.addressbook.gwt.server.util.SessionFactoryHolder;

public class AddressBookServiceImpl extends RemoteServiceServlet {

	private static final long serialVersionUID = 1L;

//	public void savePerson(Person person) {
//		Session session = SessionManager.beginTransaction();
//		session.save(person);
//		SessionManager.commitTransaction();
//	}
//
//	public List<Person> readListPerson(String lastname) {
//		Session session = SessionManager.beginTransaction();
//        Query query = session.createQuery("from Person as person where person.lastname = :lastname");        
//        query.setParameter("lastname", lastname);        
//        @SuppressWarnings("unchecked")
//		List<Person> list = query.list();
//        SessionManager.commitTransaction();
//		return list;
//	}

}
