/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.data;

import java.util.List;

public class BaseFilterPagingLoadConfig extends BasePagingLoadConfig implements FilterPagingLoadConfig {

  List<FilterConfig> ignore;
  
  public BaseFilterPagingLoadConfig() {
    super();
  }

  public BaseFilterPagingLoadConfig(int offset, int limit) {
    super(offset, limit);
  }

  public List<FilterConfig> getFilterConfigs() {
    return get("filters");
  }

  public void setFilterConfigs(List<FilterConfig> configs) {
    set("filters", configs);
  }

}
