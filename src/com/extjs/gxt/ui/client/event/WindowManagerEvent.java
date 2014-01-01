/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.event;

import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.WindowManager;

public class WindowManagerEvent extends BaseEvent {

  private WindowManager manager;

  private Window window;

  public WindowManagerEvent(WindowManager manager) {
    super(manager);
    this.manager = manager;
  }

  public WindowManagerEvent(WindowManager manager, Window window) {
    this(manager);
    this.window = window;
  }

  public WindowManager getManager() {
    return manager;
  }

  public void setManager(WindowManager manager) {
    this.manager = manager;
  }

  public Window getWindow() {
    return window;
  }

  public void setWindow(Window window) {
    this.window = window;
  }

}
