/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.widget.button;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.ButtonArrowAlign;
import com.extjs.gxt.ui.client.Style.ButtonScale;
import com.extjs.gxt.ui.client.Style.IconAlign;
import com.extjs.gxt.ui.client.aria.FocusFrame;
import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.core.Template;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.PreviewEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.*;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.Document;
import com.extjs.gxt.ui.client.widget.IconSupport;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.SafeHtmlHeader;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Accessibility;

/**
 * A button component.
 * 
 * <dl>
 * <dt><b>Events:</b></dt>
 * 
 * <dd><b>BeforeSelect</b> : ButtonEvent(button, event)<br>
 * <div>Fires before this button is selected.</div>
 * <ul>
 * <li>button : this</li>
 * <li>event : the dom event</li>
 * </ul>
 * </dd>
 * 
 * <dd><b>Select</b> : ButtonEvent(button, event)<br>
 * <div>Fires when this button is selected.</div>
 * <ul>
 * <li>button : this</li>
 * <li>event : the dom event</li>
 * </ul>
 * </dd>
 * 
 * <dd><b>MenuShow</b> : ButtonEvent(button, item)<br>
 * <div>If this button has a menu, this event fires when it is shown.</div>
 * <ul>
 * <li>button : this</li>
 * <li>menu : the menu</li>
 * </ul>
 * </dd>
 * 
 * <dd><b>MenuHide</b> : ButtonEvent(button, item)<br>
 * <div>If this button has a menu, this event fires when it is hidden.</div>
 * <ul>
 * <li>button : this</li>
 * <li>menu : the menu</li>
 * </ul>
 * </dd>
 * 
 * </dl>
 */
@SuppressWarnings("deprecation")
public class Button extends BoxComponent implements IconSupport {

  interface Templates extends SafeHtmlTemplates {

    @Template("<table cellspacing=\"0\" role=\"presentation\"><tbody class=\"{2}\" >" +
            "<tr><td class=\"{4}-tl\"><i>&#160;</i></td><td class=\"{4}-tc\"></td><td class=\"{4}-tr\"><i>&#160;</i></td></tr>" +
            "<tr><td class=\"{4}-ml\"><i>&#160;</i></td><td class=\"{4}-mc\"><em class=\"{3}\" unselectable=\"on\">" +
              "<button class=\"{4}-text\" type=\"{1}\" style=\'position: static\'>{0}</button></em></td><td class=\"{4}-mr\"><i>&#160;</i></td></tr>" +
            "<tr><td class=\"{4}-bl\"><i>&#160;</i></td><td class=\"{4}-bc\"></td><td class=\"{4}-br\"><i>&#160;</i></td></tr>" +
            "</tbody></table>")
    SafeHtml button(SafeHtml label, String type, String style, String menuClass, String baseStyle);
  }

  private static final Templates TEMPLATES = GWT.create(Templates.class);

  private static Template buttonTemplate;

  protected El buttonEl;
  protected String buttonSelector = "button";
  protected AbstractImagePrototype icon;
  protected Menu menu;
  protected ButtonScale scale = ButtonScale.SMALL;
  protected SafeHtml html;
  private ButtonArrowAlign arrowAlign = ButtonArrowAlign.RIGHT;
  private boolean handleMouseEvents = true;
  private IconAlign iconAlign = IconAlign.LEFT;
  private String menuAlign = "tl-bl?";
  private Listener<MenuEvent> menuListener;
  private int minWidth = Style.DEFAULT;
  private BaseEventPreview preview;

  private int tabIndex = 0;
  private String type = "button";

  /**
   * Creates a new button.
   */
  public Button() {
    baseStyle = "x-btn";
    preview = new BaseEventPreview() {
      protected boolean onAutoHide(PreviewEvent ce) {
        Button.this.onMouseOut(null);
        return true;
      }
    };
    // if the user holds down the mouse button on menu and split
    // buttons, content on the page is selected
    disableTextSelection(true);
  }

  /**
   * Creates a new button with the given HTML.
   * 
   * @param html the button text as HTML
   */
  public Button(SafeHtml html) {
    this();
    setHtml(html);
  }

  public Button(String text) {
    this(SafeHtmlUtils.fromString(text));
  }

  /**
   * Creates a new button with the given HTML and icon.
   * 
   * @param html the button text as HTML
   * @param icon the icon
   */
  public Button(SafeHtml html, AbstractImagePrototype icon) {
    this(html);
    setIcon(icon);
  }

  public Button(String text, AbstractImagePrototype icon) {
    this(SafeHtmlUtils.fromString(text), icon);
  }

  /**
   * Creates a new button with the given HTML, icon and specified selection
   * listener.
   * 
   * @param html the button text as HTML
   * @param icon the icon
   * @param listener the selection listener
   */
  public Button(SafeHtml html, AbstractImagePrototype icon, SelectionListener<ButtonEvent> listener) {
    this(html, icon);
    addSelectionListener(listener);
  }

  public Button(String text, AbstractImagePrototype icon, SelectionListener<ButtonEvent> listener) {
    this(SafeHtmlUtils.fromString(text), icon, listener);
  }

  /**
   * Creates a new button with the given HTML and specified selection listener.
   * 
   * @param html the button's text as HTML
   * @param listener the selection listener
   */
  public Button(SafeHtml html, SelectionListener<ButtonEvent> listener) {
    this(html);
    addSelectionListener(listener);
  }

  public Button(String text, SelectionListener<ButtonEvent> listener) {
    this(SafeGxt.fromNullableString(text), listener);
  }

  /**
   * Adds a selection listener.
   * 
   * @param listener the listener to add
   */
  public void addSelectionListener(SelectionListener<ButtonEvent> listener) {
    addListener(Events.Select, listener);
  }

  /**
   * Returns the button's arrow alignment.
   * 
   * @return the arrow alignment
   */
  public ButtonArrowAlign getArrowAlign() {
    return arrowAlign;
  }

  /**
   * Returns the button's icon.
   * 
   * @return the icon
   */
  public AbstractImagePrototype getIcon() {
    return icon;
  }

  /**
   * Returns the button's icon alignment.
   * 
   * @return the icon alignment
   */
  public IconAlign getIconAlign() {
    return iconAlign;
  }

  /**
   * Returns the button's menu (if it has one).
   * 
   * @return the menu
   */
  public Menu getMenu() {
    return menu;
  }

  /**
   * Returns the button's menu alignment.
   * 
   * @return the menu alignment
   */
  public String getMenuAlign() {
    return menuAlign;
  }

  /**
   * Returns the button's minimum width.
   * 
   * @return the minWidth the minimum width
   */
  public int getMinWidth() {
    return minWidth;
  }

  /**
   * Returns true if mouse over effect is disabled.
   * 
   * @return the handleMouseEvents the handle mouse event state
   */
  public boolean getMouseEvents() {
    return handleMouseEvents;
  }

  /**
   * Returns the buttons scale.
   * 
   * @return the scale
   */
  public ButtonScale getScale() {
    return scale;
  }

  /**
   * Returns the button's HTML content.
   * 
   * @return the button html content
   */
  public SafeHtml getHtml() {
    return html;
  }

  /**
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * Hide this button's menu (if it has one).
   */
  public void hideMenu() {
    if (menu != null) {
      menu.hide();
    }
  }

  @Override
  public void onComponentEvent(ComponentEvent ce) {
    super.onComponentEvent(ce);
    ButtonEvent be = (ButtonEvent) ce;
    switch (ce.getEventTypeInt()) {
      case Event.ONMOUSEOVER:
        onMouseOver(ce);
        break;
      case Event.ONMOUSEOUT:
        onMouseOut(ce);
        break;
      case Event.ONMOUSEDOWN:
        onMouseDown(ce);
        break;
      case Event.ONMOUSEUP:
        onMouseUp(ce);
        break;
      case Event.ONCLICK:
        onClick(ce);
        break;
      case Event.ONFOCUS:
        onFocus(ce);
        break;
      case Event.ONBLUR:
        onBlur(be);
        break;
      case Event.ONKEYUP:
        onKeyPress(be);
        break;
    }
  }

  /**
   * Removes a previously added listener.
   * 
   * @param listener the listener to be removed
   */
  public void removeSelectionListener(SelectionListener<ButtonEvent> listener) {
    removeListener(Events.Select, listener);
  }

  /**
   * Sets the arrow alignment (defaults to RIGHT).
   * 
   * @param arrowAlign the arrow alignment
   */
  public void setArrowAlign(ButtonArrowAlign arrowAlign) {
    this.arrowAlign = arrowAlign;
  }

  /**
   * Sets the button's icon style. The style name should match a CSS style that
   * specifies a background image using the following format:
   * 
   * <pre>
   * 
   * &lt;code&gt; .my-icon { background: url(images/icons/my-icon.png) no-repeat
   * center left !important; } &lt;/code&gt;
   * 
   * </pre>
   * 
   * @param icon the icon
   */
  public void setIcon(AbstractImagePrototype icon) {
    if (rendered) {
      El oldIcon = buttonEl.selectNode("." + baseStyle + "-image");
      if (oldIcon != null) {
        oldIcon.remove();
        el().removeStyleName(baseStyle + "-text-icon", baseStyle + "-icon", baseStyle + "-noicon");
      }
      el().addStyleName(
          (icon != null ? (!Util.isEmptyString(html) ? " " + baseStyle + "-text-icon" : " " + baseStyle + "-icon")
              : " " + baseStyle + "-noicon"));
      Element e = null;

      if (icon != null) {
        e = (Element) icon.createElement().cast();

        Accessibility.setRole(e, "presentation");
        fly(e).addStyleName(baseStyle + "-image");

        buttonEl.insertFirst(e);
        El.fly(e).makePositionable(true);

      }
      autoWidth();
      alignIcon(e);
    }
    this.icon = icon;
  }

  /**
   * Sets the icon alignment (defaults to LEFT).
   * 
   * @param iconAlign the icon alignment
   */
  public void setIconAlign(IconAlign iconAlign) {
    this.iconAlign = iconAlign;
  }

  public void setIconStyle(String icon) {
    setIcon(IconHelper.create(icon));
  }

  /**
   * Sets the button's menu.
   * 
   * @param menu the menu
   */
  public void setMenu(Menu menu) {
    if (menuListener == null) {
      menuListener = new Listener<MenuEvent>() {
        public void handleEvent(MenuEvent be) {
          if (Events.Show.equals(be.getType())) {
            onMenuShow(be);
          } else if (Events.Hide.equals(be.getType())) {
            onMenuHide(be);
          }
        }
      };
    }
    if (this.menu != null) {
      this.menu.setData("parent", null);
      this.menu.removeListener(Events.Hide, menuListener);
      this.menu.removeListener(Events.Show, menuListener);
    }
    this.menu = menu;
    if (this.menu != null) {
      this.menu.setData("parent", this);
      this.menu.addListener(Events.Hide, menuListener);
      this.menu.addListener(Events.Show, menuListener);
      this.menu.getAriaSupport().setLabelledBy(getId());
      getElement().setAttribute("aria-owns", menu.getId());
    }
  }

  /**
   * Sets the position to align the menu to, see {@link El#alignTo} for more
   * details (defaults to 'tl-bl?', pre-render).
   * 
   * @param menuAlign the menu alignment
   */
  public void setMenuAlign(String menuAlign) {
    this.menuAlign = menuAlign;
  }

  /**
   * Sets he minimum width for this button (used to give a set of buttons a
   * common width)
   * 
   * @param minWidth the minimum width
   */
  public void setMinWidth(int minWidth) {
    this.minWidth = minWidth;
  }

  /**
   * False to disable visual cues on mouseover, mouseout and mousedown (defaults
   * to true).
   * 
   * @param handleMouseEvents false to disable mouse over changes
   */
  public void setMouseEvents(boolean handleMouseEvents) {
    this.handleMouseEvents = handleMouseEvents;
  }

  /**
   * Sets the button scale.
   * 
   * @param scale the scale to set
   */
  public void setScale(ButtonScale scale) {
    this.scale = scale;
  }

  /**
   * Sets the button's tab index.
   * 
   * @param index the tab index
   */
  @Override
  public void setTabIndex(int index) {
    this.tabIndex = index;
    if (rendered && buttonEl != null) {
      buttonEl.dom.setPropertyInt("tabIndex", index);
    }
  }

  /**
   * Sets the button's text.
   * 
   * @param text the new text
   */
  public void setText(String text) {
    setHtml(SafeGxt.fromNullableString(text));
  }

  /**
   * Sets the button's text as HTML.
   * 
   * @param html the new html content
   */
  public void setHtml(SafeHtml html) {
    this.html = html;
    if (rendered) {
      buttonEl.update(SafeGxt.emptyToNbSpace(html));
      setIcon(icon);
    }
  }

  /**
   * Submit, reset or button (defaults to 'button').
   * 
   * @param type the new type
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Show this button's menu (if it has one).
   */
  public void showMenu() {
    if (menu != null) {
      if (GXT.isAriaEnabled()) {
        // delay need for readers
        DeferredCommand.addCommand(new Command() {
          public void execute() {
            menu.show(getElement(), menuAlign);
          }
        });
      } else {
        menu.show(getElement(), menuAlign);
      }
    }
  }

  @Override
  protected void afterRender() {
    super.afterRender();

    setTabIndex(tabIndex);

    setIcon(icon);
  }

  protected void alignIcon(Element icon) {
    if (icon != null) {
      String align = null;
      if (iconAlign == IconAlign.BOTTOM) {
        align = "b-b";
      } else if (iconAlign == IconAlign.TOP) {
        align = "t-t";
      } else if (iconAlign == IconAlign.LEFT) {
        align = "l-l";
      } else if (iconAlign == IconAlign.RIGHT) {
        align = "r-r";
      }
      int[] offset = null;
      if (GXT.isIE8 && GXT.isStrict && (iconAlign == IconAlign.LEFT || iconAlign == IconAlign.RIGHT)
          && (scale == ButtonScale.LARGE || scale == ButtonScale.MEDIUM)) {
        if (scale == ButtonScale.LARGE) {
          offset = new int[] {0, -8};
        } else if (scale == ButtonScale.MEDIUM) {
          offset = new int[] {0, -4};
        }
        icon.getStyle().setProperty("left", "");
        icon.getStyle().setProperty("top", "");
      }
      El.fly(icon).alignTo(buttonEl.dom, align, offset);
    }
  }

  protected void autoWidth() {
    if (rendered && width == null && buttonEl != null) {
      int w = 0;
      if (!Util.isEmptyString(html)) {
        TextMetrics.get().bind(buttonEl);
        w = TextMetrics.get().getWidth(html);
        w += buttonEl.getFrameWidth("lr");
        if (GXT.isGecko || GXT.isWebKit) {
          w += 6;
        }
      } else {
        buttonEl.dom.getStyle().setProperty("width", "");
        w = buttonEl.getStyleSize(false).width;
      }

      int adj = 0;
      if (GXT.isAriaEnabled()) {
        adj += html == null ? 10 : 25;
      }

      w += adj;
      
      if (w < minWidth - 6) {
        buttonEl.setWidth(minWidth - 6, true);
      } else if (w > 0) {
        buttonEl.setWidth(w, true);
      }
    }
  }

  @Override
  protected ComponentEvent createComponentEvent(Event event) {
    return new ButtonEvent(this);
  }

  @Override
  protected El getFocusEl() {
    return buttonEl;
  }

  protected String getMenuClass() {
    if (menu != null) {
      if (arrowAlign == ButtonArrowAlign.BOTTOM) {
        return baseStyle + "-arrow-bottom";
      } else {
        return baseStyle + "-arrow";
      }
    } else {
      return "";
    }
  }

  @Override
  protected void notifyShow() {
    super.notifyShow();
    if (icon != null) {
      El e = buttonEl.selectNode("." + baseStyle + "-image");
      if (e != null) {
        alignIcon(e.dom);
      }
    }
  }

  protected void onBlur(ButtonEvent e) {
    removeStyleName(baseStyle + "-focus");
    if (GXT.isFocusManagerEnabled()) {
      FocusFrame.get().unframe();
    }
  }

  protected void onClick(ComponentEvent ce) {
    ce.preventDefault();
    focus();
    hideToolTip();
    if (!disabled) {
      ButtonEvent be = new ButtonEvent(this);
      if (!fireEvent(Events.BeforeSelect, be)) {
        return;
      }
      if (menu != null && !menu.isVisible()) {
        showMenu();
      }
      fireEvent(Events.Select, be);
    }
  }

  @Override
  protected void onDetach() {
    super.onDetach();
    preview.remove();
    removeStyleName(baseStyle + "-click");
    removeStyleName(baseStyle + "-over");
    removeStyleName(baseStyle + "-menu-active");
    removeStyleName(baseStyle + "-focus");
  }

  @Override
  protected void onDisable() {
    if (!GXT.isIE6 || html == null) {
      addStyleName(disabledStyle);
    }
    removeStyleName(baseStyle + "-over");
    removeStyleName(baseStyle + "-focus");
    el().disable();
    buttonEl.dom.setAttribute("aria-disabled", "true");
  }

  @Override
  protected void onEnable() {
    super.onEnable();
    el().enable();
    buttonEl.dom.setAttribute("aria-disabled", "false");
  }

  protected void onFocus(ComponentEvent ce) {
    if (!disabled) {
      addStyleName(baseStyle + "-focus");
      if (GXT.isFocusManagerEnabled() && !GXT.isIE) {
        FocusFrame.get().frame(this);
      }
    }
  }

  protected void onKeyPress(ButtonEvent be) {
    if (be.getEvent().getKeyCode() == KeyCodes.KEY_DOWN) {
      if (menu != null && !menu.isVisible()) {
        showMenu();
      }
    }
  }

  protected void onMenuHide(ComponentEvent ce) {
    removeStyleName(baseStyle + "-menu-active");

    ButtonEvent be = new ButtonEvent(this);
    be.setMenu(menu);
    fireEvent(Events.MenuHide, be);
    focus();
  }

  protected void onMenuShow(ComponentEvent ce) {
    addStyleName(baseStyle + "-menu-active");

    ButtonEvent be = new ButtonEvent(this);
    be.setMenu(menu);
    fireEvent(Events.MenuShow, be);

    if (GXT.isFocusManagerEnabled()) {
      if (menu.getItemCount() > 0) {
        menu.setActiveItem(menu.getItem(0), false);
      }
    }
  }

  protected void onMouseDown(ComponentEvent ce) {
    if (handleMouseEvents) {
      addStyleName(baseStyle + "-click");
    }
  }

  protected void onMouseOut(ComponentEvent ce) {
    removeStyleName(baseStyle + "-click");
    removeStyleName(baseStyle + "-over");
  }

  protected void onMouseOver(ComponentEvent ce) {
    if (handleMouseEvents) {
      addStyleName(baseStyle + "-over");
      preview.add();
    }
  }

  protected void onMouseUp(ComponentEvent ce) {
    removeStyleName(baseStyle + "-click");
  }

  @Override
  protected void onRender(Element target, int index) {
    SafeHtml label = SafeGxt.emptyToNbSpace(html);
    String style = baseStyle + "-"
            + scale.name().toLowerCase() + " " + baseStyle + "-icon-" + scale.name().toLowerCase() + "-"
            + iconAlign.name().toLowerCase();

    DivElement element = com.google.gwt.dom.client.Document.get().createDivElement();
    element.setInnerSafeHtml(TEMPLATES.button(label, getType(), style, getMenuClass(), baseStyle));

    setElement(element, target, index);

    super.onRender(target, index);

    buttonEl = el().selectNode(buttonSelector);
    buttonEl.makePositionable();

    if (getFocusEl() != null) {
      getFocusEl().addEventsSunk(Event.FOCUSEVENTS);
    }

    preview.getIgnoreList().add(getElement());

    buttonEl.setTabIndex(0);

    if (GXT.isAriaEnabled()) {
      Accessibility.setRole(buttonEl.dom, Accessibility.ROLE_BUTTON);
      if (menu != null) {
        Accessibility.setState(buttonEl.dom, "aria-haspopup", "true");
        addStyleName(baseStyle + "-menu");
      }
    }

    sinkEvents(Event.ONCLICK | Event.MOUSEEVENTS | Event.FOCUSEVENTS | Event.KEYEVENTS);
  }

  @Override
  protected void onResize(int width, int height) {
    super.onResize(width, height);
    int adj = 0;
    if (GXT.isAriaEnabled()) {
      adj += html == null ? 10 : 25;
    }
    buttonEl.setSize(adj + width - 6, height - 6, true);
  }

  @Override
  protected void setAriaState(String stateName, String stateValue) {
    Accessibility.setState(buttonEl.dom, stateName, stateValue);
  }
}
