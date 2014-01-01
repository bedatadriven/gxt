/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.data;

/**
 * List load config with support for grouping.
 */
public interface GroupingLoadConfig extends ListLoadConfig {

  /**
   * Returns the group by field.
   * 
   * @return the group by field
   */
  public String getGroupBy();

  /**
   * Sets the group by field.
   * 
   * @param groupBy the group by field
   */
  public void setGroupBy(String groupBy);

}
