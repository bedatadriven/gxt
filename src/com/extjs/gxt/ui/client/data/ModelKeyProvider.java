/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.data;

/**
 * Instances of this class provide unique keys for models.
 * 
 * @param <M> the model type
 */
public interface ModelKeyProvider<M extends ModelData> {

  /**
   * Returns a unique key for the given model. The key must remain constant for
   * a given model.
   * 
   * @param model the model
   * @return the unique key
   */
  public String getKey(M model);

}
