/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.event;

import java.util.Date;

import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.menu.Menu;

/**
 * Menu event type.
 * 
 * <p/>
 * Note: For a given event, only the fields which are appropriate will be filled
 * in. The appropriate fields for each event are documented by the event source.
 * 
 * @see Menu
 */
public class MenuEvent extends ContainerEvent<Menu, Component> {

  private boolean checked;
  private Menu menu;
  private Date date;

  public MenuEvent(Menu menu) {
    super(menu);
    this.menu = menu;
  }

  public MenuEvent(Menu menu, Component component) {
    super(menu, component);
    this.menu = menu;
  }

  public Menu getMenu() {
    return menu;
  }

  public void setMenu(Menu menu) {
    this.menu = menu;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public boolean isChecked() {
    return checked;
  }

  public void setChecked(boolean checked) {
    this.checked = checked;
  }

}
