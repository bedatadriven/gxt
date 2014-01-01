/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.widget.menu;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.util.Util;
import com.extjs.gxt.ui.client.widget.Component;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Accessibility;

/**
 * Child of MenuBar.
 */
@SuppressWarnings("deprecation")
public class MenuBarItem extends Component {

  protected Menu menu;
  protected boolean expanded;
  protected MenuBar bar;
  private String html;

  /**
   * Creates a new menu bar item.
   * 
   * @param html the item's text as HTML
   * @param menu the item's sub menu
   */
  public MenuBarItem(String html, Menu menu) {
    baseStyle = "x-menubar-item";
    this.html = html;
    this.menu = menu;
    assert (menu != null);
  }

  protected void onMenuKeyLeft(ComponentEvent ce) {
    if (menu.isVisible() && bar.getItemCount() > 1) {
      DeferredCommand.addCommand(new Command() {
        public void execute() {
          int index = bar.indexOf(MenuBarItem.this);
          index = index > 1 ? index - 1 : bar.getItemCount() - 1;
          MenuBarItem item = bar.getItem(index);
          bar.setActiveItem(item, true);
        }
      });
    }
  }

  /**
   * Returns the item's sub menu.
   * 
   * @return the menu
   */
  public Menu getMenu() {
    return menu;
  }

  /**
   * Returns the bar's text.
   * 
   * @return the text
   */
  public String getHtml() {
    return html;
  }

  /**
   * Sets the item's menu.
   * 
   * @param menu the menu
   */
  public void setMenu(Menu menu) {
    this.menu = menu;
  }

  /**
   * Sets the item's text.
   * 
   * @param text the text
   */
  public void setText(String text) {
    setHtml(El.toSafeHTML(text));
  }
  
  /**
   * Sets the item's text as HTML.
   * 
   * @param html the new html content
   */
  public void setHtml(String html) {
    this.html = html;
    if (rendered) {
      el().update(Util.isEmptyString(html) ? "&#160;" : html);
    }
  }

  @Override
  protected void onRender(Element target, int index) {
    super.onRender(target, index);
    setElement(DOM.createDiv(), target, index);
    setStyleAttribute("display", "inline");
    setHtml(html);

    if (GXT.isAriaEnabled()) {
      if (menu != null) {
        getAriaSupport().setState("aria-owns", menu.getId());
        menu.getAriaSupport().setLabelledBy(getId());
      }
      // el().setTabIndex(-1);
      Accessibility.setRole(getElement(), Accessibility.ROLE_MENUITEM);
      Accessibility.setState(getElement(), "aria-haspopup", "true");
    }
  }

}
