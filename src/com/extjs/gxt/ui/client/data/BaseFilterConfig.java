/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.data;

import java.io.Serializable;

public abstract class BaseFilterConfig extends BaseModelData implements FilterConfig, Serializable {

  protected String field;
  
  public BaseFilterConfig() {
    
  }
  
  public BaseFilterConfig(String type, Object value) {
    setType(type);
    setValue(value);
  }
  
  public BaseFilterConfig(String type, String comparison, Object value) {
    setType(type);
    setComparison(comparison);
    setValue(value);
  }
  
  public String getComparison() {
    return get("comparison");
  }

  public String getField() {
    return get("field");
  }

  public String getType() {
    return get("type");
  }

  public Object getValue() {
    return get("value");
  }

  public void setComparison(String comparison) {
    set("comparison", comparison);
  }

  public void setField(String field) {
    this.field = field;
    set("field", field);
  }

  public void setType(String type) {
    set("type", type);
  }

  public void setValue(Object value) {
    set("value", value);
  }

}
