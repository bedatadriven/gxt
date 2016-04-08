/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.widget;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.data.ModelData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.text.shared.SafeHtmlRenderer;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;

import java.util.ArrayList;
import java.util.List;

public class CheckBoxListView<M extends ModelData> extends ListView<M> {

  interface Templates extends SafeHtmlTemplates {

    @Template("<div class='x-view-item x-view-item-check'><table cellspacing=\"{0}\"" +
                 "cellpadding=0><tr><td><input class=\"x-view-item-checkbox\" type=\"checkbox\"/></td><td><td>{1}" +
                  "</td></tr></table></div>")
    SafeHtml item(int cellSpacing, SafeHtml content);
  }

  private static final Templates TEMPLATES = GWT.create(Templates.class);


  protected List<M> checkedPreRender;
  private String checkBoxSelector = ".x-view-item-checkbox";

  public CheckBoxListView() {
    rowSelectorDepth = 10;
  }

  public String getCheckBoxSelector() {
    return checkBoxSelector;
  }

  public List<M> getChecked() {
    if (rendered) {
      List<M> l = new ArrayList<M>();
      NodeList<Element> nodes = el().select(checkBoxSelector);
      for (int i = 0; i < nodes.getLength(); i++) {
        if (InputElement.is(nodes.getItem(i))) {
          InputElement e = InputElement.as(nodes.getItem(i));
          if (e.isChecked()) {
            l.add(getStore().getAt(i));
          }
        }
      }
      return l;
    } else {
      return checkedPreRender != null ? new ArrayList<M>(checkedPreRender) : new ArrayList<M>();
    }
  }

  @Override
  public void onBrowserEvent(Event event) {
    if (disabled && event.getTypeInt() == Event.ONCLICK && Element.is(event.getEventTarget())
        && fly((Element) Element.as(event.getEventTarget())).is(checkBoxSelector)) {
      event.preventDefault();
    }
    super.onBrowserEvent(event);
  }

  @Override
  public void refresh() {
    List<M> checked = getChecked();
    super.refresh();
    if (checkedPreRender != null) {
      for (M m : checkedPreRender) {
        setChecked(m, true);
      }
      checkedPreRender = null;
    } else if (checked != null) {
      for (M m : checked) {
        setChecked(m, true);
      }
    }
  }

  public void setCheckBoxSelector(String checkBoxSelector) {
    this.checkBoxSelector = checkBoxSelector;
  }

  /**
   * Selects a specific item in the view
   * 
   * @param m the modeldata that should be checked
   * @param checked true to check
   */
  public void setChecked(M m, boolean checked) {
    if (rendered) {
      NodeList<Element> nodes = el().select(checkBoxSelector);
      int index = store.indexOf(m);
      if (index != -1) {
        Element e = nodes.getItem(index);
        if (InputElement.is(e)) {
          InputElement i = InputElement.as(e);
          i.setChecked(checked);
        }
      }
    } else {
      if (checkedPreRender == null) {
        checkedPreRender = new ArrayList<M>();
      }
      if (checked) {
        if (!checkedPreRender.contains(m)) {
          checkedPreRender.add(m);
        }
      } else {
        checkedPreRender.remove(m);
      }
    }
  }

  @Override
  protected void onRender(Element target, int index) {
    if (getRenderer() == null) {
      setRenderer(new ListRenderer(new ModelPropertyRenderer<M>(getDisplayProperty())));
    }
    super.onRender(target, index);
  }

  @Override
  protected void onUpdate(M model, int index) {
    boolean isChecked = getChecked().contains(model);
    super.onUpdate(model, index);
    if (isChecked) {
      setChecked(model, true);
    }
  }

  public class ListRenderer implements SafeHtmlRenderer<List<M>> {

    private SafeHtmlRenderer<M> itemRenderer;
    private int cellSpacing =  GXT.isIE && !GXT.isStrict ? 0 : 3;

    public ListRenderer(SafeHtmlRenderer<M> itemRenderer) {
      this.itemRenderer = itemRenderer;
    }

    @Override
    public void render(List<M> list, SafeHtmlBuilder builder) {
      for (M item : list) {
        builder.append(TEMPLATES.item(cellSpacing, itemRenderer.render(item)));
      }
    }

    @Override
    public SafeHtml render(List<M> list) {
      SafeHtmlBuilder builder = new SafeHtmlBuilder();
      render(list, builder);
      return builder.toSafeHtml();
    }
  }
}
