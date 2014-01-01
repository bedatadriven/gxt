/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.widget;

import com.google.gwt.user.client.Element;

/**
 * A component that contains text in a HTML label element.
 * 
 * <dl>
 * <dt>Inherited Events:</dt>
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
public class Label extends Html {

  private String labelFor;

  /**
   * Creates a new label.
   */
  public Label() {
    setTagName("label");
  }

  /**
   * Creates a new label.
   * 
   * @param html the label's text
   */
  public Label(String html) {
    this();
    setHtml(html);
  }

  /**
   * Sets the label for id.
   * 
   * @param id the element id
   */
  public void setLabelFor(String id) {
    this.labelFor = id;
  }

  @Override
  protected void onRender(Element target, int index) {
    super.onRender(target, index);

    if (labelFor != null) {
      getElement().setAttribute("for", labelFor);
    }
  }
}
