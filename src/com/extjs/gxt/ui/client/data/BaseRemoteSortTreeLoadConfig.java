/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.data;

/**
 * Default implementation of the <code>RemoteSortTreeLoadConfig</code>.
 * 
 * @see RemoteSortTreeLoader
 */
public class BaseRemoteSortTreeLoadConfig extends BaseListLoadConfig implements RemoteSortTreeLoadConfig {

  protected ModelData parent;

  public ModelData getParent() {
    return parent;
  }

  public void setParent(ModelData parent) {
    this.parent = parent;
  }

}
