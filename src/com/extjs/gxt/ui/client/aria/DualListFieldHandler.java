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
import com.extjs.gxt.ui.client.widget.form.DualListField;
import com.extjs.gxt.ui.client.widget.form.ListField;

public class DualListFieldHandler extends FocusHandler {

  @SuppressWarnings("rawtypes")
  @Override
  public boolean canHandleKeyPress(Component component, PreviewEvent pe) {
    if (component.getParent() instanceof ListField<?>) {
      ListField lf = (ListField) component.getParent();
      Component p = (Component) lf.getParent();
      return p != null && p.getParent() instanceof DualListField;

    }
    return component instanceof DualListField;
  }

  @Override
  public void onTab(Component component, PreviewEvent pe) {

  }

}
