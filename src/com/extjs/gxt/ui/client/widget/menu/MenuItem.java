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
import com.extjs.gxt.ui.client.core.XDOM;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.*;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ComponentHelper;
import com.extjs.gxt.ui.client.widget.IconSupport;
import com.extjs.gxt.ui.client.widget.Layer;
import com.google.gwt.i18n.client.BidiUtils;
import com.google.gwt.i18n.shared.WordCountDirectionEstimator;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Accessibility;
import com.google.gwt.user.client.ui.Widget;

/**
 * A base class for all menu items that require menu-related functionality (like
 * sub-menus) and are not static display items. Item extends the base
 * functionality of {@link Item} by adding menu-specific activation and click
 * handling.
 */
public class MenuItem extends Item implements IconSupport {

  protected Menu subMenu;
  protected String itemStyle = "x-menu-item";
  protected AbstractImagePrototype icon;
  protected SafeHtml html;
  protected Widget widget;
  
  private boolean directionEstimated = true;
  
  /**
   * Creates a new item.
   */
  public MenuItem() {
    canActivate = true;

    addStyleName("x-unselectable");
  }

  /**
   * Creates a new item with the given text.
   * 
   * @param html the item's text as HTML
   */
  public MenuItem(SafeHtml html) {
    this();
    this.html = html;
  }

  public MenuItem(String text) {
    this();
    setText(text);
  }

  /**
   * Creates a new item.
   * 
   * @param html the item's text as HTML
   * @param icon the item's icon
   */
  public MenuItem(SafeHtml html, AbstractImagePrototype icon) {
    this(html);
    setIcon(icon);
  }

  public MenuItem(String text, AbstractImagePrototype icon) {
    this(SafeGxt.fromNullableString(text), icon);
  }

  /**
   * Creates a new item.
   * 
   * @param text the item's text
   * @param icon the item's icon
   * @param listener the selection listener
   */
  public MenuItem(SafeHtml text, AbstractImagePrototype icon, SelectionListener<? extends MenuEvent> listener) {
    this(text, icon);
    addSelectionListener(listener);
  }

  public MenuItem(String text, AbstractImagePrototype icon, SelectionListener<? extends MenuEvent> listener) {
    this(SafeGxt.fromNullableString(text), icon, listener);
  }

  /**
   * Creates a new item.
   * 
   * @param text the item text
   * @param listener the selection listener
   */
  public MenuItem(SafeHtml text, SelectionListener<? extends MenuEvent> listener) {
    this(text);
    addSelectionListener(listener);
  }

  public MenuItem(String text, SelectionListener<? extends MenuEvent> listener) {
    this(SafeGxt.fromNullableString(text), listener);
  }

  /**
   * Expands the item's sub menu.
   */
  public void expandMenu() {
    if (isEnabled() && subMenu != null) {
      subMenu.setFocusOnShow(true);
      subMenu.show(el().dom, "tl-tr?");
    }
  }

  /**
   * Returns the item's icon style.
   * 
   * @return the icon style
   */
  public AbstractImagePrototype getIcon() {
    return icon;
  }

  /**
   * Returns the item's sub menu.
   * 
   * @return the sub menu
   */
  public Menu getSubMenu() {
    return subMenu;
  }

  /**
   * Returns the item's text.
   * 
   * @return the text
   */
  public SafeHtml getHtml() {
    return html;
  }

  /**
   * Returns the item's widget.
   * 
   * @return the widget
   */
  public Widget getWidget() {
    return widget;
  }

  /**
   * Sets the item's icon style. The style name should match a CSS style that
   * specifies a background image using the following format:
   * 
   * <pre>
   * &lt;code&gt;
   * .my-icon {
   *    background: url(images/icons/my-icon.png) no-repeat center left !important;
   * }
   * &lt;/code&gt;
   * </pre>
   * 
   * @param icon the icon
   */
  public void setIcon(AbstractImagePrototype icon) {
    this.icon = icon;
    if (rendered) {
      El oldIcon = el().selectNode(".x-menu-item-icon");
      if (oldIcon != null) {
        oldIcon.remove();
      }
      if (icon != null) {
        Element e = icon.createElement().cast();
        El.fly(e).addStyleName("x-menu-item-icon");
        el().insertChild(e, 0);
      }
    }
    this.icon = icon;
  }

  public void setIconStyle(String icon) {
    setIcon(IconHelper.create(icon));
  }

  /**
   * Sets the item's sub menu.
   * 
   * @param menu the sub menu
   */
  public void setSubMenu(Menu menu) {
    this.subMenu = menu;
    menu.parentItem = this;
  }
  
  public boolean isDirectionEstimated() {
    return directionEstimated;
  }

  /**
   * Enables/disables the use of direction estimation to set the direction of this menu item
   */
  public void setDirectionEstimated(boolean directionEstimated) {
    this.directionEstimated = directionEstimated;
  }

  /**
   * Sets the item's text.
   * 
   * @param text the text
   */
  public void setText(String text) {
    setHtml(SafeGxt.fromNullableString(text));
  }

  /**
   * Sets the item's text as HTML.
   * 
   * @param html the text as HTML
   */
  public void setHtml(SafeHtml html) {
    this.html = html;
    if (rendered) {
      el().update(SafeGxt.emptyToNbSpace(this.html));
      setIcon(icon);
    }
  }

  /**
   * Sets the item's widget.
   * 
   * @param widget the widget
   */
  public void setWidget(Widget widget) {
    this.widget = widget;
    if (rendered) {
      if (widget instanceof Component) {
        Component c = (Component) widget;
        if (!c.isRendered()) {
          c.render(getElement());
          setIcon(icon);
          return;
        }
      }
      getElement().appendChild(widget.getElement());
      setIcon(icon);
    }
  }

  @Override
  protected void activate(boolean autoExpand) {
    super.activate(autoExpand);
    if (autoExpand && subMenu != null) {
      expandMenu();
    }
  }

  @Override
  protected void afterRender() {
    super.afterRender();
    if (html != null) setHtml(html);
  }

  @Override
  protected void deactivate() {
    super.deactivate();
    if (subMenu != null && subMenu.isVisible()) {
      subMenu.hide();
    }
  }

  @Override
  protected void doAttachChildren() {
    super.doAttachChildren();
    if (widget != null) {
      ComponentHelper.doAttach(widget);
    }
  }

  @Override
  protected void doDetachChildren() {
    super.doDetachChildren();
    if (widget != null) {
      ComponentHelper.doDetach(widget);
    }
  }

  @Override
  protected void expandMenu(boolean autoActivate) {
    if (!disabled && subMenu != null) {
      if (!subMenu.isVisible()) {
        expandMenu();
        subMenu.tryActivate(0, 1);
      }
    }
  }

  @Override
  protected void onDisable() {
    super.onDisable();
    if (widget != null && widget instanceof Component) {
      ((Component) widget).disable();
    }
  }

  @Override
  protected void onEnable() {
    super.onEnable();
    if (widget != null && widget instanceof Component) {
      ((Component) widget).enable();
    }
  }

  @Override
  protected void onRender(Element target, int index) {
    super.onRender(target, index);
    setElement(DOM.createSpan(), target, index);
    
    getElement().setAttribute("unselectable", "on");

    
    if (GXT.isAriaEnabled()) {
      Accessibility.setRole(getElement(), Accessibility.ROLE_MENUITEM);
    } else {
      getElement().setPropertyString("href", "#");
    }

    String s = itemStyle + (subMenu != null ? " x-menu-item-arrow" : "");
    addStyleName(s);

    if (widget != null) {
      setWidget(widget);
    } else {
      setHtml(html);
      if(directionEstimated && html!=null && !Util.isEmptyString(html)) {
        BidiUtils.setDirectionOnElement(getElement(), WordCountDirectionEstimator.get().estimateDirection(html));
      }
    }

    if (subMenu != null) {
      Accessibility.setState(getElement(), "aria-haspopup", "true");
    }
  }

  @Override
  protected boolean shouldDeactivate(ComponentEvent ce) {
    if (super.shouldDeactivate(ce)) {
      if (subMenu != null && subMenu.isVisible()) {
        Point xy = ce.getXY();
        xy.x += XDOM.getBodyScrollLeft();
        xy.y += XDOM.getBodyScrollTop();

        Rectangle rec = subMenu.el().getBounds();
        if ((subMenu.el() instanceof Layer)) {
          Layer l = (Layer) subMenu.el();
          if (l.isShim() && l.isShadow()) {
            return !rec.contains(xy) && !l.getShadow().getBounds().contains(xy)
                && !l.getShim().getBounds().contains(xy);
          } else if (l.isShadow()) {
            return !rec.contains(xy) && !l.getShadow().getBounds().contains(xy);
          } else if (l.isShim()) {
            return !rec.contains(xy) && !l.getShim().getBounds().contains(xy);
          }
        }

        return !rec.contains(xy);
      }
    }
    return true;
  }
}
