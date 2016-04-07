/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.widget;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.util.Params;
import com.google.gwt.safehtml.shared.SafeHtml;

/**
 * Configuration settings for {@link Info}.
 */
public class InfoConfig {

  /**
   * The info title as HTML (defaults to null).
   */
  public SafeHtml titleHtml;

  /**
   * The info text as HTML (defaults to null).
   */
  public SafeHtml html;


  /**
   * The time in milliseconds to display a message (defaults to 2500).
   */
  public int display = 2500;

  /**
   * The info width (defaults to 225).
   */
  public int width = 225;

  /**
   * The info height (defaults to 75).
   */
  public int height = 75;

  /**
   * Listener to be notified when the info is displayed (defaults to null).
   */
  public Listener<ComponentEvent> listener;

  /**
   * Creates a new instance.
   * 
   * @param titleHtml the title as HTML
   * @param html the text as HTML
   */
  public InfoConfig(SafeHtml titleHtml, SafeHtml html) {
    this.titleHtml = titleHtml;
    this.html = html;
  }
}
