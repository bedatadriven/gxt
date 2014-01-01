/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.data;

/**
 * Interface for objects that translate raw data into a given type.
 * 
 * @param <D> the data type being returned by the reader
 */
public interface DataReader<D> {

  /**
   * Reads the raw data and returns the typed data.
   * 
   * @param data the data to read
   * @return the data
   */
  public D read(Object loadConfig, Object data);

}
