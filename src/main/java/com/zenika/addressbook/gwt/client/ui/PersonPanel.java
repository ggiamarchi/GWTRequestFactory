package com.zenika.addressbook.gwt.client.ui;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.zenika.addressbook.gwt.client.model.AddressProxy;
import com.zenika.addressbook.gwt.client.model.PersonProxy;

public class PersonPanel  extends Composite {

	private TextBox firstnameTextBox = new TextBox();
	private TextBox lastnameTextBox = new TextBox();
	private TextBox phoneTextBox = new TextBox();
	private TextBox emailTextBox = new TextBox();
	private final ListBox addressesListBox = new ListBox(false);
	
	private Button saveButton = new Button("Save");
	
	private TextBox address1TextBox = new TextBox();
	private TextBox address2TextBox = new TextBox();
	private TextBox zipcodeTextBox = new TextBox();
	private TextBox cityTextBox = new TextBox();
	private TextBox stateTextBox = new TextBox();
	private TextBox countryTextBox = new TextBox();
	
	private PersonProxy currentPerson;
	private AddressProxy currentAddress;

	public PersonPanel() {
		
		saveButton.setEnabled(false);
		
		// Person form
		
		FlexTable personTable = new FlexTable();
		personTable.setCellSpacing(15);
		personTable.setWidget(0, 0, new Label("First name :"));
		personTable.setWidget(0, 1, firstnameTextBox);
		personTable.setWidget(1, 0, new Label("Last name :"));
		personTable.setWidget(1, 1, lastnameTextBox);
		personTable.setWidget(2, 0, new Label("Phone :"));
		personTable.setWidget(2, 1, phoneTextBox);
		personTable.setWidget(3, 0, new Label("Email :"));
		personTable.setWidget(3, 1, emailTextBox);
		personTable.setWidget(4, 0, new Label("Addresses :"));
		personTable.setWidget(4, 1, addressesListBox);
		personTable.setWidget(5, 0, saveButton);
		
		
		// Address form
		
		FlexTable addressTable = new FlexTable();
		addressTable.setCellSpacing(15);
		
		FlexCellFormatter cellFormatter = addressTable.getFlexCellFormatter();
		cellFormatter.setColSpan(0, 1, 3);
		cellFormatter.setColSpan(1, 1, 3);

		addressTable.setWidget(0, 0, new Label("Address :"));
		addressTable.setWidget(0, 1, address1TextBox);
		addressTable.setWidget(1, 1, address2TextBox);
		addressTable.setWidget(2, 0, new Label("Zipcode :"));
		addressTable.setWidget(2, 1, zipcodeTextBox);
		addressTable.setWidget(2, 2, new Label("City :"));
		addressTable.setWidget(2, 3, cityTextBox);
		addressTable.setWidget(3, 0, new Label("State :"));
		addressTable.setWidget(3, 1, stateTextBox);
		addressTable.setWidget(3, 2, new Label("Country :"));
		addressTable.setWidget(3, 3, countryTextBox);
		
	    HorizontalPanel editTable = new HorizontalPanel();
	    editTable.setSpacing(50);
	    editTable.add(personTable);
	    editTable.add(addressTable);
	    
		initWidget(editTable);

	}
	
	private void clear() {
		firstnameTextBox.setText("");
		lastnameTextBox.setText("");
		phoneTextBox.setText("");
		emailTextBox.setText("");
		addressesListBox.clear();
		address1TextBox.setText("");
		address2TextBox.setText("");
		zipcodeTextBox.setText("");
		cityTextBox.setText("");
		stateTextBox.setText("");
		countryTextBox.setText("");
		saveButton.setEnabled(false);
	}

	public void addAddressChangeHandler(ChangeHandler handler) {
		addressesListBox.addChangeHandler(handler);
	}

	public void addSaveButtonClickHandler(ClickHandler handler) {
		saveButton.addClickHandler(handler);
	}


	public void setPerson(PersonProxy person) {
		if (currentPerson == person) {
			return;
		}
		if (currentPerson != null) {
			currentPerson.setFirstname(firstnameTextBox.getText());
			currentPerson.setLastname(lastnameTextBox.getText());
			currentPerson.setEmail(emailTextBox.getText());
			currentPerson.setPhone(phoneTextBox.getText());
			setAddress(null);
		}
		clear();
		currentPerson = person;
		if (currentPerson != null) {
			firstnameTextBox.setText(person.getFirstname());
			lastnameTextBox.setText(person.getLastname());
			phoneTextBox.setText(person.getPhone());
			emailTextBox.setText(person.getEmail());
			addressesListBox.clear();
			Map<Integer, AddressProxy> addresses = new HashMap<Integer, AddressProxy>();
			if (person.getAddresses() != null) {
				for (AddressProxy address : person.getAddresses()) {
					addresses.put(address.getId(), address);
					addressesListBox.addItem(address.getZipcode() + " " + address.getCity(), String.valueOf(address.getId()));
				}
				addressesListBox.setSelectedIndex(0);
				AddressProxy address = addresses.get(Integer.valueOf(addressesListBox.getValue(0)));
				setAddress(address);
			}
			saveButton.setEnabled(true);
		}
	}
	
	public void setAddress(AddressProxy address) {
		if (address == currentAddress) {
			return;
		}
		if (currentAddress != null) {
			currentAddress.setLine1(address1TextBox.getText());
			currentAddress.setLine2(address2TextBox.getText());
			currentAddress.setZipcode(zipcodeTextBox.getText());
			currentAddress.setCity(cityTextBox.getText());
			currentAddress.setState(stateTextBox.getText());
			currentAddress.setCountry(countryTextBox.getText());
		}
		currentAddress = address;
		if (currentAddress != null) {
			address1TextBox.setText(address.getLine1());
			address2TextBox.setText(address.getLine2());
			zipcodeTextBox.setText(address.getZipcode());
			cityTextBox.setText(address.getCity());
			stateTextBox.setText(address.getState());
			countryTextBox.setText(address.getCountry());
		}
	}

	public void save() {
		setPerson(null);
	}

}
