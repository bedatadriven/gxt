/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.widget.button;

import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

/**
 * A horizontal row of buttons.
 */
public class ButtonBar extends ToolBar {

  /**
   * Creates a left aligned button bar.
   */
  public ButtonBar() {
    super();
    setSpacing(5);
    baseStyle = "x-panel-fbar";
  }

}
