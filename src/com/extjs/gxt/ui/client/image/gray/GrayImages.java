/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.image.gray;

import com.extjs.gxt.ui.client.image.XImages;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;

public interface GrayImages extends XImages {

	@Override
	@Source("page-first.gif")
	@ImageOptions(flipRtl=true)
	ImageResource paging_toolbar_first();

	@Override
	@Source("page-last.gif")
	@ImageOptions(flipRtl=true)
	ImageResource paging_toolbar_last();

	@Override
	@Source("page-next.gif")
	@ImageOptions(flipRtl=true)
	ImageResource paging_toolbar_next();

	@Override
	@Source("page-prev.gif")
	@ImageOptions(flipRtl=true)
	ImageResource paging_toolbar_prev();

	@Override
	@Source("refresh.gif")
	ImageResource paging_toolbar_refresh();
}
