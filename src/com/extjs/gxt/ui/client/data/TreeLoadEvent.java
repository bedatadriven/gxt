/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.data;

import java.util.List;

/**
 * Event type for tree loaders.
 */
public class TreeLoadEvent extends LoadEvent {

  /**
   * The parent which will be null when returning the root elements.
   */
  public ModelData parent;

  public TreeLoadEvent(TreeLoader<?> loader, Object config, List<?> result) {
    super(loader, config, result);
  }

  public TreeLoadEvent(TreeLoader<?> loader) {
    super(loader);
  }

  public TreeLoadEvent(TreeLoader<?> loader, Object config, Throwable t) {
    super(loader, config, t);
  }

}
