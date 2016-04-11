/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.widget.form;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.aria.FocusFrame;
import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.core.XDOM;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Accessibility;

import static com.extjs.gxt.ui.client.util.SafeGxt.fromNullableString;

/**
 * Single checkbox field. Unlike other fields, checkbox fires change events when
 * the radios state is changed, not on blur.
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
public class CheckBox extends Field<Boolean> {

  protected El wrap, input, boxLabelEl;
  private SafeHtml boxLabel;
  private String valueAttribute;
  private Timer t;

  /**
   * Creates a new checkbox.
   */
  public CheckBox() {
    setFireChangeEventOnSetValue(true);
    value = false;
    propertyEditor = new BooleanPropertyEditor();
    ensureVisibilityOnSizing = true;
  }

  @Override
  public void clearInvalid() {
    // do nothing
  }

  /**
   * Returns the text displayed next to the checkbox.
   * 
   * @return the box label
   */
  public SafeHtml getBoxLabel() {
    return boxLabel;
  }

  @Override
  public String getRawValue() {
    if (!rendered) {
      return value.toString();
    }
    return String.valueOf(((InputElement) getInputEl().dom.cast()).isChecked());
  }

  @Override
  public Boolean getValue() {
    if (!isAttached() && rendered) {
      return ((InputElement) input.dom.cast()).isDefaultChecked();
    }
    return super.getValue();
  }

  /**
   * 
   * Returns the value property of the input element
   */
  public String getValueAttribute() {
    if (rendered) {
      return input.getValue();
    }
    return valueAttribute;
  }

  @Override
  public void markInvalid(String msg) {

  }

  public void setBoxLabel(SafeHtml boxLabelHtml) {
    this.boxLabel = boxLabelHtml;
    if (rendered) {
      boxLabelEl.update(boxLabelHtml);
    }
  }

  public void setBoxLabel(String boxLabel) {
    setBoxLabel(fromNullableString(boxLabel));
  }

  @Override
  public void setRawValue(String value) {
    boolean b = Boolean.valueOf(value).booleanValue();
    ((InputElement) getInputEl().dom.cast()).setChecked(b);
  }

  @Override
  public void setValue(Boolean value) {
    if (value == null) {
      value = false;
    }
    focusValue = value;
    if (rendered) {
      ((InputElement) input.dom.cast()).setDefaultChecked(value);
    }
    super.setValue(value);
  }

  /**
   * Sets a new value attribute to the input element
   * 
   * @param valueAttribute the value attribute to set
   */
  public void setValueAttribute(String valueAttribute) {
    this.valueAttribute = valueAttribute;
    if (rendered) {
      input.setValue(valueAttribute);
    }
  }

  protected void alignElements() {
    input.dom.getStyle().setProperty("left", "");
    input.dom.getStyle().setProperty("top", "");
    if (boxLabelEl != null) {
      boxLabelEl.dom.getStyle().setProperty("left", "");
      boxLabelEl.dom.getStyle().setProperty("top", "");
    }
    if (t != null) {
      t.cancel();
      t = null;
    }
    t = new Timer() {

      @Override
      public void run() {
        if (boxLabel == null) {
          input.alignTo(getElement(), "c-c", null);
          if (GXT.isIE || GXT.isOpera) {
            input.alignTo(getElement(), "c-c", null);
          }
        } else {
          input.alignTo(getElement(), "l-l", new int[] {0, 0});
          if (GXT.isIE || GXT.isOpera) {
            input.alignTo(getElement(), "l-l", new int[] {0, 0});
          }

          boxLabelEl.alignTo(input.dom, "l-r", new int[] {5, GXT.isIE ? -1 : 0});
          if (GXT.isIE || GXT.isOpera) {
            boxLabelEl.alignTo(input.dom, "l-r", new int[] {5, GXT.isIE ? -1 : 0});
          }
        }
        el().repaint();
        t = null;
      }
    };
    t.schedule(1);

  }

  @Override
  protected El getFocusEl() {
    return input;
  }

  @Override
  protected El getInputEl() {
    return input;
  }

  @Override
  protected El getStyleEl() {
    return input;
  }

  @Override
  protected void notifyShow() {
    super.notifyShow();
    alignElements();
  }

  @Override
  protected void onAttach() {
    super.onAttach();
    alignElements();
  }

  @Override
  protected void onBlur(ComponentEvent be) {
    super.onBlur(be);
    FocusFrame.get().unframe();
  }

  @Override
  protected void onClick(ComponentEvent ce) {
    super.onClick(ce);
    if (readOnly) {
      ce.stopEvent();
      return;
    }

    boolean v = getInputEl().dom.getPropertyBoolean("checked");
    setValue(v);
  }

  @Override
  protected void onFocus(ComponentEvent ce) {
    super.onFocus(ce);
    FocusFrame.get().frame(this);
  }

  @Override
  protected void onRender(Element target, int index) {
    if (this instanceof Radio) {
      input = new El(DOM.createInputRadio(name));
    } else {
      input = new El(DOM.createInputCheck());
    }

    input.setId(XDOM.getUniqueId());
    input.makePositionable();

    wrap = new El(DOM.createDiv());
    wrap.dom.setPropertyString("hideFocus", "hideFocus");
    wrap.dom.setClassName("x-form-check-wrap");
    wrap.dom.setAttribute("role", "presentation");
    wrap.dom.appendChild(input.dom);

    setElement(wrap.dom, target, index);
    wrap.makePositionable();
    if (boxLabel != null) {
      boxLabelEl = new El(DOM.createLabel());
      boxLabelEl.setElementAttribute("for", input.getId());
      boxLabelEl.setElementAttribute("htmlFor", input.getId());
      boxLabelEl.dom.setClassName("x-form-cb-label");
      boxLabelEl.makePositionable();
      wrap.dom.appendChild(boxLabelEl.dom);
      setBoxLabel(boxLabel);
    }

    super.onRender(target, index);

    setValueAttribute(valueAttribute);

    focusStyle = null;
  }

  @Override
  protected void onResize(int width, int height) {
    super.onResize(width, height);
    if (boxLabel == null) {
      // center it again
      alignElements();
    }
  }

  @Override
  protected void setAriaState(String stateName, String stateValue) {
    Accessibility.setState(input.dom, stateName, stateValue);
  }
}
