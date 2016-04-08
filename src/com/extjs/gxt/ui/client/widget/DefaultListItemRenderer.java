package com.extjs.gxt.ui.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.text.shared.SafeHtmlRenderer;

import java.util.List;

public class DefaultListItemRenderer<T> implements SafeHtmlRenderer<List<T>> {

    interface Templates extends SafeHtmlTemplates {

        @Template("<div class='x-view-item'>{0}</div>")
        SafeHtml item(SafeHtml contents);
    }

    private static final Templates TEMPLATES = GWT.create(Templates.class);

    private SafeHtmlRenderer<? super T> itemRenderer;

    /**
     * @param itemRenderer a renderer for each element in the list
     */
    public DefaultListItemRenderer(SafeHtmlRenderer<? super T> itemRenderer) {
        this.itemRenderer = itemRenderer;
    }

    @Override
    public SafeHtml render(List<T> list) {
        SafeHtmlBuilder builder = new SafeHtmlBuilder();
        render(list, builder);
        return builder.toSafeHtml();
    }

    @Override
    public void render(List<T> list, SafeHtmlBuilder builder) {
        for (T item : list) {
            builder.append(TEMPLATES.item(itemRenderer.render(item)));
        }
    }
}
