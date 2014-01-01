/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.data;

import com.google.gwt.user.client.ui.AbstractImagePrototype;

public interface ModelIconProvider<M extends ModelData> {

  public AbstractImagePrototype getIcon(M model);

}
