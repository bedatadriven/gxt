/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.data;

/**
 * A <code>ListLoadConfig</code> which adds a parent property.
 * 
 * @see RemoteSortTreeLoader
 */
public interface RemoteSortTreeLoadConfig extends ListLoadConfig {

  /**
   * Returns the parent.
   * 
   * @return the parent
   */
  public ModelData getParent();

  /**
   * Sets the parent.
   * 
   * @param parent the parent
   */
  public void setParent(ModelData parent);

}
