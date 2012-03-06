package com.zenika.addressbook.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.zenika.addressbook.gwt.client.model.AddressProxy;
import com.zenika.addressbook.gwt.client.model.PersonProxy;
import com.zenika.addressbook.gwt.client.ui.MainPanel;
import com.zenika.addressbook.gwt.client.ui.PersonPanel;
import com.zenika.addressbook.gwt.client.ui.SearchPanel;
import com.zenika.addressbook.gwt.shared.AddressBookResquestFactory;
import com.zenika.addressbook.gwt.shared.PersonRequest;


public class AddressBook implements EntryPoint {
	
	private PersonProxy currentPerson;
	private PersonRequest editRequest; 

	public void onModuleLoad() {

		// Create RequestFactory
		final AddressBookResquestFactory requestFactory = GWT.create(AddressBookResquestFactory.class);
		requestFactory.initialize(new SimpleEventBus());

		// Create GUI
		final SearchPanel searchPanel = new SearchPanel();
		final PersonPanel personPanel = new PersonPanel();
		final MainPanel mainScreen = new MainPanel(searchPanel, personPanel);
		RootPanel.get().add(mainScreen);
		
		// Search button click handler
		searchPanel.addSearchButonClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				// Get input parameters
				String firstname = searchPanel.getFirstnameText();
				String lastname = searchPanel.getLastnameText();

				// Build request
				Request<PersonProxy> req = requestFactory.personResquest().read(firstname, lastname);
				if (searchPanel.isFetchAdresses()) {
					req.with("addresses");
				}

				// Fire request
				req.fire(new Receiver<PersonProxy>() {
					@Override
					public void onSuccess(PersonProxy person) {
						if (person == null) {
							Window.alert("Not found !");
							return;
						}
						// Handle response
						editRequest = requestFactory.personResquest();
						person = editRequest.edit(person);
						currentPerson = person;
						personPanel.setPerson(person);
					}
					@Override
					public void onFailure(ServerFailure error) {
						System.err.println(error.getMessage());
					}
				});
				
			}
		});
		
		personPanel.addSaveButtonClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (currentPerson == null) {
					Window.alert("Nothing to save !");
					return;
				}
				personPanel.save();
				editRequest.saveOrUpdate(currentPerson).fire(new Receiver<Void>() {
					@Override
					public void onSuccess(Void response) {
						currentPerson = null;
						Window.alert("Saved");
					}
					@Override
					public void onFailure(ServerFailure error) {
						Window.alert("Error : " + error.getMessage());						
						super.onFailure(error);
					}
				});
			}
		});
		
		personPanel.addAddressChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				ListBox box = (ListBox) event.getSource();
				int id = Integer.valueOf(box.getValue(box.getSelectedIndex()));
				if (currentPerson.getAddresses() != null) {
					for (AddressProxy address : currentPerson.getAddresses()) {
						if (address.getId() == id) {
							personPanel.setAddress(address);
							break;
						}
					}
				}
			}
		});

	}

}

