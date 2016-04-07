/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.widget.form;

import com.extjs.gxt.ui.client.util.SafeGxt;
import com.extjs.gxt.ui.client.util.Util;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
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
public class LabelField extends Field<SafeHtml> {

  private SafeHtml value;

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
  public LabelField(SafeHtml html) {
    this();
    setValue(html);
  }

  public LabelField(String text) {
    this();
    setValue(SafeHtmlUtils.fromString(text));
  }

  @Override
  public SafeHtml getValue() {
    return value;
  }

  @Override
  public boolean isDirty() {
    return false;
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


  @Override
  public void setValue(SafeHtml value) {
    this.value = value;

    if (rendered) {
        el().update(SafeGxt.emptyToNbSpace(this.value));
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
