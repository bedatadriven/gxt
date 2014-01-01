/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.widget;

/**
 * Defines the interface for component plugins. Component will call the
 * {@link #init(Component)} method where each plugin can then call methods or
 * respond to events on the component as needed to provide its functionality.
 */
public interface ComponentPlugin {

  /**
   * Initializes the plugin when the component is created.
   * 
   * @param component the source component
   */
  public void init(Component component);

}
