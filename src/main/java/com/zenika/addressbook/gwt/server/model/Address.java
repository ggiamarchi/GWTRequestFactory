package com.zenika.addressbook.gwt.server.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import com.zenika.addressbook.gwt.server.util.SessionFactoryHolder;

@Entity
public class Address implements Serializable {

	private static final long serialVersionUID = -7125160361618018958L;

	@Id
	@GeneratedValue
	private int id;
	
	@Version
	private int Version;


	private String line1;
	private String line2;
	private String zipcode;
	private String city;
	private String state;
	private String country;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVersion() {
		return Version;
	}

	public void setVersion(int version) {
		Version = version;
	}

	public String getLine1() {
		return line1;
	}
	
	public void setLine1(String line1) {
		this.line1 = line1;
	}
	
	public String getLine2() {
		return line2;
	}
	
	public void setLine2(String line2) {
		this.line2 = line2;
	}
	
	public String getZipcode() {
		return zipcode;
	}
	
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	@Override
	public boolean equals(Object o) {
		if (! (o instanceof Address)) {
			return false;
		}
		return id == ((Address)o).id;
	}
	
	@Override
	public int hashCode() {
		return
			new StringBuilder()
				.append(id)
				.append(line1)
				.append(line2)
				.append(zipcode)
				.append(city)
				.append(state)
				.append(country)
				.toString()
				.hashCode();
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", line1=" + line1 + ", line2=" + line2
				+ ", zipcode=" + zipcode + ", city=" + city + ", state="
				+ state + ", country=" + country + "]";
	}
	
	public static Address findAddress(int id) {
		System.out.println("Address.findAddress()");
		return (Address) SessionFactoryHolder.getCurrentSession().load(Address.class, id);
	}

}
