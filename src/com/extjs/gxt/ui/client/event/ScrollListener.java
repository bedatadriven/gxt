/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.event;

import com.extjs.gxt.ui.client.widget.ScrollContainer;

/**
 * Event interface for scroll events.
 * 
 * @see ScrollContainer
 */
public class ScrollListener implements Listener<ComponentEvent> {

  public void handleEvent(ComponentEvent e) {
    EventType type = e.getType();
    if (type == Events.Scroll) {
      widgetScrolled(e);
    }
  }

  /**
   * Fires when a component is scrolled.
   * 
   * @param ce the component event
   */
  public void widgetScrolled(ComponentEvent ce) {

  }

}
