package com.zenika.addressbook.gwt.shared;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.zenika.addressbook.gwt.client.model.PersonProxy;
import com.zenika.addressbook.gwt.server.model.Person;

@Service(Person.class)
public interface PersonRequest extends RequestContext {

	Request<PersonProxy> read(String firstname, String lastname);

	Request<Void> saveOrUpdate(PersonProxy person);

}
