package com.zenika.addressbook.gwt.client.ui;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class SearchPanel extends Composite {

	private TextBox firstnameTextBox = new TextBox();
	private TextBox lastnameTextBox  = new TextBox();;
	private CheckBox checkboxAddresses = new CheckBox("Fetch addresses");;
	private Button searchButton = new Button("Search");
	
	public SearchPanel() {
		
		firstnameTextBox.setText("Guillaume");  // TODO - for testing convenience (to remove)
		lastnameTextBox.setText("Giamarchi");   // TODO - for testing convenience (to remove)

		FlexTable searchFormPanel = new FlexTable();
		searchFormPanel.setWidget(0, 0, new Label("First name : "));
		searchFormPanel.setWidget(0, 1, firstnameTextBox);
		searchFormPanel.setWidget(0, 2, new Label("Last name : "));
		searchFormPanel.setWidget(0, 3, lastnameTextBox);
		searchFormPanel.setWidget(0, 4, checkboxAddresses);
		searchFormPanel.setWidget(0, 5, searchButton);
		searchFormPanel.setCellSpacing(15);

		initWidget(searchFormPanel);

	}

	public String getLastnameText() {
		return lastnameTextBox.getText();
	}

	public String getFirstnameText() {
		return firstnameTextBox.getText();
	}
	
	public boolean isFetchAdresses() {
		return checkboxAddresses.getValue();
	}

	public void addSearchButonClickHandler(ClickHandler clickHandler) {
		searchButton.addClickHandler(clickHandler);
	}

}
