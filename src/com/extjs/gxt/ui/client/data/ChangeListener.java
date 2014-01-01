/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.data;

/**
 * Interface for objects that listen for model change events.
 * 
 * @see ChangeEvent
 * @see BaseModel
 */
public interface ChangeListener {

  /**
   * Fired when the model's state has changed.
   * 
   * @param event an event describing the change
   */
  public void modelChanged(ChangeEvent event);

}
