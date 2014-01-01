/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.widget.form;

import com.extjs.gxt.ui.client.util.Util;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

/**
 * Displays static text.
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
 * <dd>BoxComponent Move</dd>
 * <dd>BoxComponent Resize</dd>
 * <dd>Component Enable</dd>
 * <dd>Component Disable</dd>
 * <dd>Component BeforeHide</dd>
 * <dd>Component Hide</dd>
 * <dd>Component BeforeShow</dd>
 * <dd>Component Show</dd>
 * <dd>Component Attach</dd>
 * <dd>Component Detach</dd>
 * <dd>Component BeforeRender</dd>
 * <dd>Component Render</dd>
 * <dd>Component BrowserEvent</dd>
 * <dd>Component BeforeStateRestore</dd>
 * <dd>Component StateRestore</dd>
 * <dd>Component BeforeStateSave</dd>
 * <dd>Component SaveState</dd>
 * </dl>
 */
public class LabelField extends Field<Object> {

  private String value;

  private boolean useHtml = true;

  /**
   * Creates a new label field.
   */
  public LabelField() {
    baseStyle = "x-form-label";
    focusStyle = null;
    setLabelSeparator("");
  }

  /**
   * Creates a new label field.
   * 
   * @param html the label text as HTML
   */
  public LabelField(String html) {
    this();
    setValue(html);
  }

  @Override
  public Object getValue() {
    return value;
  }

  @Override
  public boolean isDirty() {
    return false;
  }

  /**
   * Returns {@code true} if the field's value is treated as HTML (defaults to
   * true).
   * 
   * @return the use HTML state
   */
  public boolean isUseHtml() {
    return useHtml;
  }

  @Override
  public boolean isValid(boolean silent) {
    return true;
  }

  @Override
  public void markInvalid(String msg) {

  }

  @Override
  protected void onRender(Element parent, int index) {
    setElement(DOM.createDiv(), parent, index);
    if (value != null) {
      originalValue = value;
      setValue(value);
    }
  }

  /**
   * Sets whether the field's value is treated as HTML (defaults to true,
   * pre-render).
   * 
   * @param useHtml {@code true} to treat value as HTML
   */
  public void setUseHtml(boolean useHtml) {
    assertPreRender();
    this.useHtml = useHtml;
  }

  @Override
  public void setValue(Object value) {
    if (value == null) {
      this.value = null;
    } else {
      this.value = value.toString();
    }

    if (rendered) {
      if (useHtml) {
        el().update(Util.isEmptyString(this.value) ? "&#160;" : this.value);
      } else {
        getElement().setInnerText(this.value);
      }
    }
  }

  @Override
  public boolean validate(boolean silent) {
    return true;
  }

  @Override
  protected boolean validateValue(String value) {
    return true;
  }

}
