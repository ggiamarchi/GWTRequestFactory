package com.zenika.addressbook.gwt.client.util;

import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import com.google.web.bindery.requestfactory.shared.EntityProxy;

public class EntityProxyLogger {

	public static String toJson(EntityProxy entityProxy)
	{
	    return AutoBeanCodex.encode(AutoBeanUtils.getAutoBean(entityProxy)).getPayload();
	}
	
}
