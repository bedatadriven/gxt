/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.widget.toolbar;

import com.extjs.gxt.ui.client.widget.Component;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

/**
 * A tool bar separator.
 */
public class SeparatorToolItem extends Component {

  public SeparatorToolItem() {
    getAriaSupport().setPresentation(true);
  }
  
  @Override
  protected void onRender(Element target, int index) {
    setElement(DOM.createSpan(), target, index);
    setStyleName("xtb-sep");
  }

}
