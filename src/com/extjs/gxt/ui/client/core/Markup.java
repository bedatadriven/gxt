/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.core;

import com.google.gwt.user.client.Element;

public interface Markup {
  public String getHtml();

  public Element getRootElement();

  public Element select(String selector);
}
