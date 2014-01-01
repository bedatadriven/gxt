/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.charts.client.model.charts.dots;

/**
 * OFC Bow
 */
public class Bow extends BaseDot {

  public Bow() {
    this(null);
  }

  public Bow(Number value) {
    super("bow", value);
  }

  /**
   * Gets the rotation.
   * 
   * @return the rotation
   */
  public Integer getRotation() {
    return (Integer) get("rotation");
  }

  /**
   * Sets the rotation.
   * 
   * @param rotation the rotation
   */
  public void setRotation(Integer rotation) {
    set("rotation", rotation);
  }
}
