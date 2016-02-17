/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.themes.client.access.image;

import com.extjs.gxt.ui.client.image.XImages;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;

public interface AccessImages extends XImages {
  
	@Override
	@Source("tree-collapsed.png")
	@ImageOptions(flipRtl=true)
	ImageResource tree_collapsed();

	@Override
	@Source("tree-collapsed.png")
	@ImageOptions(flipRtl=true)
	ImageResource tree_collapsed_over();

	@Override
	@Source("tree-expanded.png")
	@ImageOptions(flipRtl=true)
	ImageResource tree_expanded();

	@Override
	@Source("tree-expanded.png")
	@ImageOptions(flipRtl=true)
	ImageResource tree_expanded_over();

	@Override
	@Source("more.gif")
	@ImageOptions(flipRtl=true)
	ImageResource toolbar_more();

	@Override
	@Source("tb-bold.gif")
	ImageResource editor_bold();

	@Override
	@Source("tb-font-color.gif")
	ImageResource editor_font_color();

	@Override
	@Source("tb-font-decrease.gif")
	ImageResource editor_font_decrease();

	@Override
	@Source("tb-font-highlight.gif")
	ImageResource editor_font_highlight();

	@Override
	@Source("tb-font-increase.gif")
	ImageResource editor_font_increase();

	@Override
	@Source("tb-italic.gif")
	ImageResource editor_italic();

	@Override
	@Source("tb-justify-center.gif")
	ImageResource editor_justify_center();

	@Override
	@Source("tb-justify-left.gif")
	ImageResource editor_justify_left();

	@Override
	@Source("tb-justify-right.gif")
	ImageResource editor_justify_right();

	@Override
	@Source("tb-underline.gif")
	ImageResource editor_underline();

	@Override
	@Source("group-checked.gif")
	ImageResource group_checked();

	@Override
	@Source("page-prev.gif")
	@ImageOptions(flipRtl=true)
	ImageResource paging_toolbar_prev();

	@Override
	@Source("page-prev-disabled.gif")
	@ImageOptions(flipRtl=true)
	ImageResource paging_toolbar_prev_disabled();

	@Override
	@Source("page-next.gif")
	@ImageOptions(flipRtl=true)
	ImageResource paging_toolbar_next();

	@Override
	@Source("page-next-disabled.gif")
	@ImageOptions(flipRtl=true)
	ImageResource paging_toolbar_next_disabled();

	@Override
	@Source("page-first.gif")
	@ImageOptions(flipRtl=true)
	ImageResource paging_toolbar_first();

	@Override
	@Source("page-first-disabled.gif")
	@ImageOptions(flipRtl=true)
	ImageResource paging_toolbar_first_disabled();

	@Override
	@Source("page-last.gif")
	@ImageOptions(flipRtl=true)
	ImageResource paging_toolbar_last();

	@Override
	@Source("page-last-disabled.gif")
	@ImageOptions(flipRtl=true)
	ImageResource paging_toolbar_last_disabled();

	@Override
	@Source("refresh.gif")
	ImageResource paging_toolbar_refresh();
}
