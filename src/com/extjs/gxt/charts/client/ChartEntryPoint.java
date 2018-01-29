package com.extjs.gxt.charts.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.user.client.Window;

public class ChartEntryPoint implements EntryPoint {

  private static final ChartResources RESOURCES = GWT.create(ChartResources.class);

  @Override
  public void onModuleLoad() {
    ScriptInjector.FromString tag = ScriptInjector.fromString(RESOURCES.swfobject().getText());
    tag.setWindow(getOuterWindow());
    tag.inject();
  }

  /**
   * Finds the outer window. THis is where the rest of the chart library expects swfobject
   * to be.
   */
  private static native JavaScriptObject getOuterWindow() /*-{ return $wnd; } -*/;
}
