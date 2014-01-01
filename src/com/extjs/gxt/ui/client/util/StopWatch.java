/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.util;

import java.util.HashMap;
import java.util.Map;

public class StopWatch {

  private static Map<String, Long> times = new HashMap<String, Long>();
  
  public static void start(String name) {
    times.put(name, System.currentTimeMillis());
    System.out.println(name + " start");
  }
  
  public static void lap(String name) {
    long end = System.currentTimeMillis();
    long start = times.get(name);
    System.out.println(name + " lap: " + (end - start));
  }
  
  public static void end(String name) {
    long end = System.currentTimeMillis();
    long start = times.get(name);
    System.out.println(name + " end: " + (end - start));
  }
  
}
