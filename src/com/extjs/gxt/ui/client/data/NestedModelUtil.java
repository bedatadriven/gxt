/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NestedModelUtil {

  @SuppressWarnings("unchecked")
  public static <X> X getNestedValue(ModelData model, String property) {
    return (X) getNestedValue(model, getPath(property));
  }

  @SuppressWarnings("unchecked")
  public static <X> X getNestedValue(ModelData model, List<String> paths) {
    Object obj = model.get(paths.get(0));
    if (paths.size() == 1) {
      return (X) obj;
    } else if (obj != null && obj instanceof ModelData) {
      List<String> tmp = new ArrayList<String>(paths);
      tmp.remove(0);
      return (X) getNestedValue((ModelData) obj, tmp);
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  public static <X> X setNestedValue(ModelData model, String property, Object value) {
    return (X) setNestedValue(model, getPath(property), value);
  }

  @SuppressWarnings("unchecked")
  public static <X> X setNestedValue(ModelData model, List<String> paths, Object value) {
    int index = paths.size() - 1;
    String path = paths.get(index);
    paths.remove(index);
    ModelData m = getNestedValue(model, paths);
    return (X) m.set(path, value);
  }

  public static boolean isNestedProperty(String property) {
    return property != null && property.contains(".");
  }

  private static List<String> getPath(String property) {
    return new ArrayList<String>(Arrays.asList(property.split("\\.")));
  }
}
