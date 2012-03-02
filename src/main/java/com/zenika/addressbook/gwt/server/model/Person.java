package com.zenika.addressbook.gwt.server.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.criterion.Restrictions;

import com.zenika.addressbook.gwt.server.util.SessionFactoryHolder;


@Entity
@Table(
	name="person", 
	uniqueConstraints=
		@UniqueConstraint(
	        name="unique_firstname_lastname", 
	        columnNames={"firstname", "lastname"} 
		)
)
public class Person implements Serializable, Comparable<Person> {

	private static final long serialVersionUID = -5034893225405242562L;

	@Id
	@GeneratedValue
	private int id;
	
	@Version
	private int Version;

	private String firstname;	
	private String lastname;
	private String phone;
	private String email;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "person_id")
	private Set<Address> addresses;

	public Person() {

	}
	
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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	@Override
	public boolean equals(Object o) {
		if (! (o instanceof Person)) {
			return false;
		}
		return id == ((Person)o).id;
	}
	
	@Override
	public int hashCode() {
		return
			new StringBuilder()
				.append(id)
				.append(lastname)
				.append(firstname)
				.append(phone)
				.append(email)
				.toString()
				.hashCode();
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstname=" + firstname + ", lastname="
				+ lastname + ", phone=" + phone + ", email=" + email
				+ ", address=" + addresses + "]";
	}
	
	
	// Services implementation

	public static Person findPerson(int id) {
		System.out.println("Person.findPerson()");
		return (Person) SessionFactoryHolder.getSessionFactory().getCurrentSession().load(Person.class, id);

	}

	public static void saveOrUpdate(Person person) {
		System.out.println("Person.saveOrUpdate()");
		SessionFactoryHolder.getSessionFactory().getCurrentSession().saveOrUpdate(person);
	}

	public static Person read(String firstname, String lastname) {
		System.out.println("Person.readListPerson()");
		return (Person) SessionFactoryHolder.getSessionFactory().getCurrentSession()
			.createCriteria(Person.class)
		    	.add(Restrictions.like("firstname", firstname))
		    	.add(Restrictions.like("lastname", lastname))
		    	.uniqueResult();
	}

	@Override
	public int compareTo(Person o) {
		return
			((o.lastname != null ? o.lastname : "") + (o.firstname != null ? o.firstname : ""))
			.compareTo
			((lastname != null ? lastname : "") + (firstname != null ? firstname : ""));
	}
	
}
