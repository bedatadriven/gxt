package com.extjs.gxt.ui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ExternalTextResource;

/**
 * Resource bundle that contains GXT's global CSS.
 */
public interface GXTResourceBundle extends ClientBundle {
  
  public static final GXTResourceBundle INSTANCE = GWT.create(GXTResourceBundle.class);
  
  @Source("gxt-all.css")
  ExternalTextResource global();
  
  @Source("gxt-all-rtl.css")
  ExternalTextResource globalRtl();
  
}
