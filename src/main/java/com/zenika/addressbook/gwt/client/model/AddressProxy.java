package com.zenika.addressbook.gwt.client.model;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.zenika.addressbook.gwt.server.model.Address;

@ProxyFor(Address.class)
public interface AddressProxy extends EntityProxy {

	public int getId();

	public void setId(int id);

	public String getLine1();
	
	public void setLine1(String line1);
	
	public String getLine2();
	
	public void setLine2(String line2);
	
	public String getZipcode();
	
	public void setZipcode(String zipcode);
	
	public String getCity();
	
	public void setCity(String city);
	
	public String getState();
	
	public void setState(String state);

	public String getCountry();

	public void setCountry(String country);

}
