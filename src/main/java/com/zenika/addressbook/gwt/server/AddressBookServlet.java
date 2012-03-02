package com.zenika.addressbook.gwt.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.zenika.addressbook.gwt.server.util.SessionFactoryHolder;

public class AddressBookServlet extends RequestFactoryServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Transaction tx = null;
		try {
			Session session = SessionFactoryHolder.getCurrentSession();
			tx = session.beginTransaction();
			super.service(request, response);
			tx.commit();
		}
        finally {
        	if (tx != null && tx.isActive()) {
        		tx.rollback();
        	}
        }		
	}

}
