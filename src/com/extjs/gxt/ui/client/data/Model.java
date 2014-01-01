/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.data;

/**
 * Primary interface for GXT model objects with event support.
 * 
 * <dl>
 * <dt><b>Events:</b></dt>
 * 
 * <dd><b>ChangeEventSource.Update</b> : ChangeEvent(source, name, oldvalue, newvalue)<br>
 * <div>Fires after the item is updated.</div>
 * <ul>
 * <li>source : this</li>
 * <li>name : property name </li>
 * <li>oldvalue : old value</li>
 * <li>newvalue : new value</li>
 * </ul>
 * </dd>
 * </dl>
 * 
 * <p/>For a default implementation see {@link BaseModel} or
 * {@link BaseTreeModel}.
 */
public interface Model extends ModelData, ChangeEventSource {
}
