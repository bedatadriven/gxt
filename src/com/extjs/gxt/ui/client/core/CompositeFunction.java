/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.core;

import com.google.gwt.user.client.Element;

/**
 * Interface for functions that can be applied to all the elements of a
 * <code>CompositeElement</code>.
 */
public interface CompositeFunction {

  /**
   * Called for each element in the composite element.
   * 
   * @param elem the child element
   * @param ce the composite element
   * @param index the child index
   */
  public void doFunction(Element elem, CompositeElement ce, int index);

}
