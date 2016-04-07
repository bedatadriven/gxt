/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.widget;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.util.SafeGxt;
import com.extjs.gxt.ui.client.util.TextMetrics;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

/**
 * Status component typically used within a <code>ToolBar</code>.
 */
public class Status extends BoxComponent {

  private SafeHtml html;
  private String iconStyle;
  private boolean box;
  private El textEl;

  public Status() {
    baseStyle = "x-status";
  }

  /**
   * Clears the current status by removing the current icon and change the text.
   * 
   * @param html the new text value as HTML
   */
  public void clearStatus(SafeHtml html) {
    setIconStyle(null);
    setHtml(html);
  }

  public void clearStatus() {
    clearStatus(SafeHtmlUtils.EMPTY_SAFE_HTML);
  }

  /**
   * Returns the icon style.
   * 
   * @return the icon style
   */
  public String getIconStyle() {
    return iconStyle;
  }

  /**
   * Returns the text.
   * 
   * @return the text
   */
  public SafeHtml getHtml() {
    return html;
  }

  /**
   * Returns true if the box effect is enabled.
   * 
   * @return the box state
   */
  public boolean isBox() {
    return box;
  }

  /**
   * True to enable a 3D insert border (defaults to false).
   * 
   * @param box true for the box effect
   */
  public void setBox(boolean box) {
    this.box = box;
    if (rendered) {
      if (box) {
        addStyleName("x-status-text-panel");
      } else {
        removeStyleName("x-status-text-panel");
      }
    }
  }

  /**
   * Enables a busy icon and displays the given text.
   * 
   * @param html the text to display as HTML
   */
  public void setBusy(SafeHtml html) {
    setIconStyle("x-status-busy");
    setHtml(html);
  }

  /**
   * Sets the icon style.
   * 
   * @param iconStyle the CSS style name
   */
  public void setIconStyle(String iconStyle) {
    if (this.iconStyle != iconStyle) {
      if (rendered) {
        if (this.iconStyle != null) {
          textEl.removeStyleName("x-status-icon");
          textEl.removeStyleName(this.iconStyle);
        }
        if (iconStyle != null) {
          textEl.addStyleName("x-status-icon");
          textEl.addStyleName(iconStyle);
        }
        autoWidth();
      }
      this.iconStyle = iconStyle;
    }
  }

  /**
   * Sets both the text and icon style.
   * 
   * @param text the text to display
   * @param iconStyle the icon style
   */
  public void setStatus(String text, String iconStyle) {
    setText(text);
    setIconStyle(iconStyle);
  }

  /**
   * Sets the text.
   * 
   * @param text the text
   */
  public void setText(String text) {
    setHtml(SafeGxt.fromNullableString(text));
  }
  
  /**
   * Sets the html of the status.
   * @param html the html content to draw in the status
   */
  public void setHtml(SafeHtml html) {
    if (this.html != html) {
      this.html = html;
      if (rendered) {
        textEl.update(SafeGxt.emptyToNbSpace (html));
        autoWidth();
      }
    }
  }

  protected void autoWidth() {
    if (rendered && isAutoWidth()) {
      setWidth("auto");
      if (GXT.isIE) {
        if (textEl != null) {
          textEl.clip();
          TextMetrics.get().bind(textEl.dom);
          int adj = iconStyle != null ? 25 : 0;
          int w = TextMetrics.get().getWidth(html) + adj + 5;
          textEl.setWidth(w, true);
        }
      }
    }
  }

  protected void onRender(Element target, int index) {
    setElement(DOM.createDiv(), target, index);
    textEl = new El(DOM.createSpan());
    textEl.addStyleName("x-status-text");
    getElement().appendChild(textEl.dom);
    super.onRender(target, index);
    disableTextSelection(true);
    setBox(box);
    
    String iconStyle = this.iconStyle;
    this.html = null;
    this.iconStyle = null;
    setHtml(html);
    setIconStyle(iconStyle);
  }
}
