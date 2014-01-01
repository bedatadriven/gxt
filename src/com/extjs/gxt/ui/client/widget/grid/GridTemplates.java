/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.widget.grid;

import com.extjs.gxt.ui.client.core.Templates;

public interface GridTemplates extends Templates {

  @Cache
  public String master(String body, String header);

  @Cache
  public String body(String rows);

  @Cache
  public String startGroup(String groupId, String cls, String style, String group);

  @Cache
  public String endGroup();

}
