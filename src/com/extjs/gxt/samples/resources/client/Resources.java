/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.samples.resources.client;

import com.extjs.gxt.samples.resources.client.icons.ExampleIcons;
import com.extjs.gxt.samples.resources.client.images.ExampleImages;
import com.google.gwt.core.client.GWT;

public class Resources {

  public static final ExampleImages IMAGES = GWT.create(ExampleImages.class);
  public static final ExampleIcons ICONS = GWT.create(ExampleIcons.class);

}
