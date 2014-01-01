/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.data;

/**
 * Default <code>ModelComparer</code>
 * 
 * @param <M> the model type
 */
public class DefaultModelComparer<M extends ModelData> implements ModelComparer<M> {

  /**
   * Global instance.
   */
  @SuppressWarnings("rawtypes")
  public static final DefaultModelComparer DEFAULT = new DefaultModelComparer();

  public boolean equals(M a, M b) {
    return (a == b || (a != null && a.equals(b)));
  }

}
