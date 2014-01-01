/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.binding;

/**
 * Provides a mechanism to convert data when passing values between model and
 * fields.
 */
public abstract class Converter {

  /**
   * Converts the model's value before being set on the field.
   * 
   * @param value the model value
   * @return the field value
   */
  public Object convertModelValue(Object value) {
    return value;
  }

  /**
   * Converts the field's value before being set on the model.
   * 
   * @param value the field value
   * @return the model value
   */
  public Object convertFieldValue(Object value) {
    return value;
  }

}
