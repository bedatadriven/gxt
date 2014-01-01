/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.data;

/**
 * Compares the model instances for equality.
 * 
 * @param <M> the model type
 */
public interface ModelComparer<M extends ModelData> {

  /**
   * Returns true if and model instances represent the same entity.
   * 
   * @param m1 model one
   * @param m2 model two
   * @return true if the models are equal
   */
  public boolean equals(M m1, M m2);

}
