/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.event;

import com.extjs.gxt.ui.client.widget.form.Field;
import com.google.gwt.user.client.Event;

public class HtmlEditorEvent extends FieldEvent {

  public HtmlEditorEvent(Field<?> field, Event event) {
    super(field, event);
  }

  public HtmlEditorEvent(Field<?> field) {
    super(field);
  }

}
