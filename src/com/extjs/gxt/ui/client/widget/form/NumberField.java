/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.widget.form;

import com.google.gwt.user.client.Element;

/**
 * Numeric text field that provides automatic keystroke filtering and numeric
 * validation.
 * 
 * <p>
 * When the field wraps any thing other than Double, either
 * {@link #setPropertyEditorType(Class)} or
 * {@link #setPropertyEditor(PropertyEditor)} should be called with the
 * appropriate number type.
 * 
 * <code><pre>
 * NumberField field = new NumberField();
 * field.setPropertyEditorType(Integer.class);
 * </pre></code>
 * 
 * <dl>
 * <dt>Inherited Events:</dt>
 * <dd>Field Focus</dd>
 * <dd>Field Blur</dd>
 * <dd>Field Change</dd>
 * <dd>Field Invalid</dd>
 * <dd>Field Valid</dd>
 * <dd>Field KeyPress</dd>
 * <dd>Field SpecialKey</dd>
 * </dl>
 */
public class NumberField extends SpinnerField {

  /**
   * NumberField messages.
   */
  public class NumberFieldMessages extends SpinnerFieldMessages {

  }

  /**
   * Creates a new number field.
   */
  public NumberField() {
    messages = new NumberFieldMessages();
    setHideTrigger(true);
    setIncrement(0d);
  }

  @Override
  public NumberFieldMessages getMessages() {
    return (NumberFieldMessages) messages;
  }

  @Override
  protected void onRender(Element target, int index) {
    super.onRender(target, index);
    keyNav.bind(null);
  }
}