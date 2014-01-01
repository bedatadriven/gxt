/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.store;

import com.extjs.gxt.ui.client.data.BaseTreeModel;
import com.extjs.gxt.ui.client.data.ModelData;

/**
 * "Wraps" a model and provides parent-child relationships to the model.
 */
public class TreeStoreModel extends BaseTreeModel {

  public TreeStoreModel(ModelData model) {
    set("model", model);
  }

  /**
   * Returns the actual model.
   * 
   * @return the model
   */
  public ModelData getModel() {
    return (ModelData)get("model");
  }

}
