/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.aria;

import com.extjs.gxt.ui.client.event.PreviewEvent;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.Field;

public class FieldHandler extends FocusHandler {

  @Override
  public boolean canHandleKeyPress(Component component, PreviewEvent pe) {
    return component instanceof Field<?>;
  }

  @Override
  public void onTab(Component component, PreviewEvent pe) {
    if (!isManaged()) return;

    if (!pe.isShiftKey() && forwardIfOverride(component)) {
      pe.stopEvent();
      return;
    } else if (pe.isShiftKey() && previousIfOverride(component)) {
      pe.stopEvent();
      return;
    }
  }

  @Override
  public void onEscape(Component component, PreviewEvent pe) {
    if (component instanceof ComboBox<?>) {
      ComboBox<?> combo = (ComboBox<?>) component;
      if (combo.isExpanded()) {
        return;
      }
    }
    super.onEscape(component, pe);
  }

}
