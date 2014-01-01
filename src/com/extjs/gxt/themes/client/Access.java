/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.themes.client;

import com.extjs.gxt.themes.client.access.image.AccessImages;
import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.util.Theme;
import com.google.gwt.core.client.GWT;

/**
 * Accessibility theme (default path is 'gxt/themes/access/css/xtheme-access.css').
 */
public class Access extends Theme {

  public static Theme ACCESS = new Access();

  public Access() {
    super("access", "Access", "gxt/themes/access/css/xtheme-access.css");
  }

  public Access(String name) {
    super("access", name, "gxt/themes/access/css/xtheme-access.css");
  }

  @Override
  public void init() {
    super.init();
    GXT.IMAGES = GWT.create(AccessImages.class);
  }
  
}
