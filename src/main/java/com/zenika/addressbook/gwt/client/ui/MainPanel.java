package com.zenika.addressbook.gwt.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MainPanel extends Composite {

	public MainPanel(Composite searchPanel, Composite personePanel) {
	    VerticalPanel mainPanel = new VerticalPanel();
		mainPanel.add(searchPanel);
		mainPanel.add(personePanel);
		initWidget(mainPanel);
	}

}
