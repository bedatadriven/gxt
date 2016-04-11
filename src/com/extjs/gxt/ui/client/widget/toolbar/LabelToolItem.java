/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.widget.toolbar;

import com.extjs.gxt.ui.client.util.SafeGxt;
import com.extjs.gxt.ui.client.widget.Html;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.Element;

/**
 * A label tool item.
 */
public class LabelToolItem extends Html {

  /**
   * Creates a new label.
   */
  public LabelToolItem() {
    getFocusSupport().setIgnore(true);
  }

  /**
   * Creates a new label.
   * 
   * @param html the label treated as HTML
   */
  public LabelToolItem(SafeHtml html) {
    this();
    setHtml(html);
  }

  public LabelToolItem(String text) {
    this(SafeGxt.fromNullableString(text));
  }

  @Override
  protected void onRender(Element target, int index) {
    super.onRender(target, index);
    addStyleName("xtb-text");
  }
}
