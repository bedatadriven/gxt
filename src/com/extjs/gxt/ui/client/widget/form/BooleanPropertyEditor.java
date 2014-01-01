/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.widget.form;

/**
 * <code>PropertyEditor</code> for boolean values.
 */
public class BooleanPropertyEditor implements PropertyEditor<Boolean> {

  public Boolean convertStringValue(String value) {
    String v = value != null ? value.toString().toLowerCase() : null;
    if (v != null && (v.equals("true") || v.equals("on"))) {
      return true;
    } else {
      return false;
    }
  }

  public String getStringValue(Boolean value) {
    return value.toString();
  }

}
