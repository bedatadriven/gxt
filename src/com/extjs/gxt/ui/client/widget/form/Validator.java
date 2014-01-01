/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.widget.form;

/**
 * Interface for validating a field's value.
 */
public interface Validator {

  /**
   * Validates the fields value.
   * 
   * @param field the field
   * @param value the value to validate
   * @return <code>null</code> if validation passes, otherwise the error message
   */
  public String validate(Field<?> field, String value);

}
