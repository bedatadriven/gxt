/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.themes.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface ThemeNames extends Messages {

  public static ThemeNames NAMES = (ThemeNames) GWT.create(ThemeNames.class);

  public String slate();
  
  public String access();

}
