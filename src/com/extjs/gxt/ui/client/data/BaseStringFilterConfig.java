/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.data;

public class BaseStringFilterConfig extends BaseFilterConfig {

  public BaseStringFilterConfig() {
    super();
  }

  public BaseStringFilterConfig(String type, Object value) {
    super(type, value);
  }

  public BaseStringFilterConfig(String type, String comparison, Object value) {
    super(type, comparison, value);
  }

  public boolean isFiltered(ModelData model, Object test, String comparison, Object value) {
    String val = value == null ? null : value.toString();
    String v = test == null ? "" : test.toString();
    if (v.length() == 0 && (val == null || val.length() == 0)) {
      return false;
    } else if (val == null) {
      return true;
    } else {
      return val.toLowerCase().indexOf(v.toLowerCase()) == -1;
    }
  }

}
