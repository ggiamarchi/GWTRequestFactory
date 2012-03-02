package com.zenika.addressbook.gwt.client.model;

import java.util.Set;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.zenika.addressbook.gwt.server.model.Person;

@ProxyFor(Person.class)
public interface PersonProxy extends EntityProxy {

	public int getId();

	public void setId(int id);

	public String getFirstname();

	public void setFirstname(String firstname);

	public String getLastname();

	public void setLastname(String lastname);

	public String getPhone();

	public void setPhone(String phone);

	public String getEmail();

	public void setEmail(String email);
	
	public Set<AddressProxy> getAddresses();

	public void setAddresses(Set<AddressProxy> addresses);
	
}
