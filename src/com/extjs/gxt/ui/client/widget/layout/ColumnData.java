/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.widget.layout;

import com.extjs.gxt.ui.client.Style;

public class ColumnData extends LayoutData {

  private double width = Style.DEFAULT;

  public ColumnData() {

  }

  public ColumnData(double width) {
    this.width = width;
  }

  /**
   * Sets the width of the column.
   * 
   * @param width the width, values <= 1 treated a percentages.
   */
  public void setWidth(double width) {
    this.width = width;
  }

  /**
   * Returns the width.
   * 
   * @return the wdith
   */
  public double getWidth() {
    return width;
  }

}
