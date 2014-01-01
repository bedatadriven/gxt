/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.charts.client.event;

import com.extjs.gxt.ui.client.event.Listener;

/**
 * Chart listener.
 */
public abstract class ChartListener implements Listener<ChartEvent> {

  public void handleEvent(ChartEvent ce) {
    chartClick(ce);
  }

  public void chartClick(ChartEvent ce) {
  }

}
