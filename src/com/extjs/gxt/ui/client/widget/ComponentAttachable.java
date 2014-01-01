/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.widget;

/**
 * Instances of this class are notified when their containing component is
 * attached and detached.
 */
public interface ComponentAttachable {
  void doAttach();

  void doDetach();
}
