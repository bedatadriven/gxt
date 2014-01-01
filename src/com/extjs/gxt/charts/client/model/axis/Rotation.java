/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.charts.client.model.axis;

/**
 * Rotation enumeration.
 */
public enum Rotation {

  VERTICAL("vertical"), DIAGONAL("diagonal"), HORIZONTAL("horizontal");

  private final String text;

  Rotation(String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return text;
  }
}
