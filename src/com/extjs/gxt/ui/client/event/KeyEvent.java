/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.event;

import com.extjs.gxt.ui.client.widget.Component;
import com.google.gwt.user.client.Event;

public class KeyEvent extends ComponentEvent {

  public KeyEvent(Component component, Event event) {
    super(component, event);
  }

  public KeyEvent(Component component) {
    super(component);
  }

}
