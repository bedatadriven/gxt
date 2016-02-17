/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.image;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;


/**
 * Defines the icons used by the Ext GWT library.
 * 
 * <p>
 * To 'override' these images extend this interface, override the desired
 * methods and add a @Resource annotation.
 * 
 * <pre>
 * public interface CustomImages extends XImages {
 *   @Resource("youimage.gif") 
 *   public ImageResource toolbar_more(); 
 * } </pre>
 * 
 */
public interface XImages extends ClientBundle {
	@Source("hmenu-asc.gif")
	ImageResource grid_sortAsc();

	@Source("hmenu-desc.gif")
	ImageResource grid_sortDesc();

	@Source("columns.gif")
	ImageResource grid_columns();

	@Source("group-by.gif")
	ImageResource grid_groupBy();

	@Source("checked.gif")
	ImageResource checked();

	@Source("unchecked.gif")
	ImageResource unchecked();

	@Source("group-checked.gif")
	ImageResource group_checked();

	@Source("exclamation.gif")
	ImageResource field_invalid();

	@Source("tb-bold.gif")
	ImageResource editor_bold();

	@Source("tb-font-color.gif")
	ImageResource editor_font_color();

	@Source("tb-font-decrease.gif")
	ImageResource editor_font_decrease();

	@Source("tb-font-highlight.gif")
	ImageResource editor_font_highlight();

	@Source("tb-font-increase.gif")
	ImageResource editor_font_increase();

	@Source("tb-italic.gif")
	ImageResource editor_italic();

	@Source("tb-justify-center.gif")
	ImageResource editor_justify_center();

	@Source("tb-justify-left.gif")
	ImageResource editor_justify_left();

	@Source("tb-justify-right.gif")
	ImageResource editor_justify_right();

	@Source("tb-link.gif")
	ImageResource editor_link();

	@Source("tb-ol.gif")
	ImageResource editor_ol();

	@Source("tb-ul.gif")
	ImageResource editor_ul();

	@Source("tb-source.gif")
	ImageResource editor_source();

	@Source("tb-underline.gif")
	ImageResource editor_underline();

	@Source("page-prev.gif")
	@ImageOptions(flipRtl=true)
	ImageResource paging_toolbar_prev();

	@Source("page-prev-disabled.gif")
	@ImageOptions(flipRtl=true)
	ImageResource paging_toolbar_prev_disabled();

	@Source("page-next.gif")
	@ImageOptions(flipRtl=true)
	ImageResource paging_toolbar_next();

	@Source("page-next-disabled.gif")
	@ImageOptions(flipRtl=true)
	ImageResource paging_toolbar_next_disabled();

	@Source("page-first.gif")
	@ImageOptions(flipRtl=true)
	ImageResource paging_toolbar_first();

	@Source("page-first-disabled.gif")
	@ImageOptions(flipRtl=true)
	ImageResource paging_toolbar_first_disabled();

	@Source("page-last.gif")
	@ImageOptions(flipRtl=true)
	ImageResource paging_toolbar_last();

	@Source("page-last-disabled.gif")
	@ImageOptions(flipRtl=true)
	ImageResource paging_toolbar_last_disabled();

	@Source("refresh.png")
	ImageResource paging_toolbar_refresh();

	@Source("more.gif")
	@ImageOptions(flipRtl=true)
	ImageResource toolbar_more();

	@Source("folder.png")
	ImageResource tree_folder();

	@Source("folder-closed.png")
	ImageResource tree_folder_closed();

	@Source("tree-collapsed.png")
	@ImageOptions(flipRtl=true)
	ImageResource tree_collapsed();

	@Source("tree-collapsed-over.png")
	@ImageOptions(flipRtl=true)
	ImageResource tree_collapsed_over();

	@Source("tree-expanded.png")
	@ImageOptions(flipRtl=true)
	ImageResource tree_expanded();

	@Source("tree-expanded-over.png")
	@ImageOptions(flipRtl=true)
	ImageResource tree_expanded_over();

	@Source("wait.gif")
	ImageResource icon_wait();
  
	@Source("less_than.png")
	ImageResource grid_filter_lessThan();
  
	@Source("greater_than.png")
	ImageResource grid_filter_greaterThan();
  
	@Source("equals.png")
	ImageResource grid_filter_equal();
  
	@Source("find.png")
	ImageResource grid_filter_find();
}
