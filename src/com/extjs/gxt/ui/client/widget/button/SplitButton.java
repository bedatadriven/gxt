/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.widget.button;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.Style.ButtonArrowAlign;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

/**
 * A split button that provides a built-in dropdown arrow that can fire an event
 * separately from the default click event of the button.
 * 
 * <dl>
 * <dt><b>Events:</b></dt>
 * 
 * <dd><b>ArrowClick</b> : ButtonEvent(button, item, event)<br>
 * <div>Fires when this button's arrow is clicked.</div>
 * <ul>
 * <li>button : this</li>
 * <li>item : the menu</li>
 * <li>event : the dom event</li>
 * </ul>
 * </dd>
 * 
 * </dl>
 */
public class SplitButton extends Button {

  protected String arrowSelector = "em";

  /**
   * Creates a new split button.
   */
  public SplitButton() {
    super();
  }

  /**
   * Creates a new split button.
   * 
   * @param html the button's text
   */
  public SplitButton(SafeHtml html) {
    super(html);
  }

  public SplitButton(String text) {
    super(SafeHtmlUtils.fromString(text));
  }

  /**
   * Creates a new split button.
   * 
   * @param text the button's text
   * @param listener the selection listener
   */
  public SplitButton(SafeHtml text, SelectionListener<ButtonEvent> listener) {
    super(text, listener);
  }

  protected String getMenuClass() {
    if (menu != null) {
      if (getArrowAlign() == ButtonArrowAlign.BOTTOM) {
        return baseStyle + "-split-bottom";
      } else {
        return baseStyle + "-split";
      }
    } else {
      return "";
    }
  }

  protected boolean isClickOnArrow(ComponentEvent ce) {
    return (getArrowAlign() == ButtonArrowAlign.BOTTOM) ? ce.getClientY() > buttonEl.getRegion().bottom
        : ce.getClientX() > buttonEl.getRegion().right;
  }

  @Override
  protected void onClick(ComponentEvent ce) {
    ce.preventDefault();
    focus();
    hideToolTip();
    if (!disabled) {
      ButtonEvent be = new ButtonEvent(this);
      be.setEvent(ce.getEvent());
      if (isClickOnArrow(ce)) {
        if (menu != null && !menu.isVisible()) {
          showMenu();
        }
        be.setMenu(menu);
        fireEvent(Events.ArrowClick, be);
      } else {
        fireEvent(Events.Select, be);
      }
    }
  }

  @Override
  protected void onDisable() {
    if (rendered) {
      if (!GXT.isIE6) {
        el().addStyleName(disabledStyle);
      }
      buttonEl.disable();
      el().child(arrowSelector).disable();
    }
    disabled = true;
  }

  @Override
  protected void onEnable() {
    if (rendered) {
      if (!GXT.isIE6) {
        el().removeStyleName(disabledStyle);
      }
      buttonEl.enable();
      el().child(arrowSelector).enable();
    }
    disabled = true;
  }

  protected boolean isMenuTriggerOver(ComponentEvent ce) {
    return menu != null && ce.getTarget().getTagName().equalsIgnoreCase("EM");
  }

  protected boolean isMenuTriggerOut(ComponentEvent ce) {
    return menu != null && !ce.getTarget().getTagName().equalsIgnoreCase("EM");
  }

}
