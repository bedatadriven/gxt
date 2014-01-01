/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client;

/**
 * Interface for objects that can return new object instances based on a part
 * id.
 */
public interface PartProvider {
  /**
   * Returns a new object instance given the part id.
   * 
   * @param partId the part id
   * @return the new object, or null if provider cannot handle given id
   */
  public Object createPart(String partId);
}
