/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.data;

import com.extjs.gxt.ui.client.store.GroupingStore;

/**
 * A <code>ListLoadConfig</code> with grouping support.
 *
 * @see GroupingStore
 */
public class BaseGroupingLoadConfig extends BaseListLoadConfig implements GroupingLoadConfig {

  public String getGroupBy() {
    return (String) get("groupBy");
  }

  public void setGroupBy(String groupBy) {
    set("groupBy", groupBy);
  }

}
