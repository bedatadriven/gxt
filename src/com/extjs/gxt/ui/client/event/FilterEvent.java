/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.event;

import java.util.List;

import com.extjs.gxt.ui.client.data.FilterConfig;
import com.extjs.gxt.ui.client.widget.grid.filters.Filter;

public class FilterEvent extends BaseEvent {

  private Filter filter;
  private List<FilterConfig> params;

  public FilterEvent(Filter filter) {
    super(filter);
    this.filter = filter;
  }

  public FilterEvent(Filter filter, List<FilterConfig> params) {
    this(filter);
    setParams(params);
  }

  public Filter getFilter() {
    return filter;
  }

  public void setFilter(Filter filter) {
    this.filter = filter;
  }

  public List<FilterConfig> getParams() {
    return params;
  }

  public void setParams(List<FilterConfig> params) {
    this.params = params;
  }

}
