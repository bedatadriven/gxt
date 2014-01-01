/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.samples.resources.client.model;

import com.extjs.gxt.ui.client.data.BaseModelData;

public class Task extends BaseModelData {

  public Task(int id, String project, int taskId, String desc, double estimate, double rate,
      String due) {
    set("id", id);
    set("project", project);
    set("taskId", taskId);
    set("description", desc);
    set("estimate", estimate);
    set("rate", rate);
    set("due", due);
  }

  public Double getEstimate() {
    return (Double) get("estimate", 0.0);
  }

  public double getRate() {
    return (Double) get("rate", 0.0);
  }

}
