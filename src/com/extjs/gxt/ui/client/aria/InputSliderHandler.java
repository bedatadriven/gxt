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
import com.extjs.gxt.ui.client.widget.InputSlider;
import com.extjs.gxt.ui.client.widget.form.NumberField;

public class InputSliderHandler extends FocusHandler {

  @Override
  public boolean canHandleKeyPress(Component component, PreviewEvent pe) {
    return component.getData("gxt-input-slider") != null;
  }

  @Override
  public void onTab(Component component, PreviewEvent pe) {
    if (component instanceof NumberField) {
      pe.stopEvent();
      focusNextWidget(component.getParent());
    }
  }
  
  @Override
  public void onEscape(Component component, PreviewEvent pe) {
    if (!(component instanceof InputSlider)) {
      super.onEscape((Component)component.getParent(), pe);
    } else {
      super.onEscape(component, pe);
    }
   
  }

}
