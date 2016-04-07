/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.widget.grid;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.safehtml.shared.SafeHtml;

/**
 * Returns the HTML used for a grid cell.
 */
public interface GridCellRenderer<M extends ModelData> {

  /**
   * Returns the HTML to be used in a grid cell.
   * 
   * @param model the model
   * @param property the model property
   * @param config the column config
   * @param rowIndex the row index
   * @param colIndex the cell index
   * @param store the data store
   * @param grid the grid
   * @return the cell HTML or Component instance
   */
  public SafeHtml render(M model, String property, ColumnData config, int rowIndex, int colIndex,
                         ListStore<M> store, Grid<M> grid);

}
