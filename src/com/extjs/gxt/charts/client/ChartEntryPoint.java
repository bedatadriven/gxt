package com.extjs.gxt.charts.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.ScriptInjector;

public class ChartEntryPoint implements EntryPoint {

  private static final ChartResources RESOURCES = GWT.create(ChartResources.class);

  @Override
  public void onModuleLoad() {
    ScriptInjector.fromString(RESOURCES.swfobject().getText()).inject();
  }
}
