package com.extjs.gxt.ui.client.widget.form;

import com.extjs.gxt.ui.client.data.ModelData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.text.shared.SafeHtmlRenderer;

import java.util.List;

/**
 * Renders combo box list items
 */
public class ComboBoxListRenderer<T> implements SafeHtmlRenderer<List<T>> {

    interface Templates extends SafeHtmlTemplates {

        @Template("<div role=\"listitem\" class=\"{0}\">{1}</div>")
        SafeHtml item(String itemStyle, SafeHtml contents);
    }

    private static final Templates TEMPLATES = GWT.create(Templates.class);

    private final String itemStyle;
    private final SafeHtmlRenderer<T> itemRenderer;

    public ComboBoxListRenderer(String listStyle, SafeHtmlRenderer<T> itemRenderer) {
        this.itemStyle = listStyle + "-item";
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
            builder.append(TEMPLATES.item(itemStyle, itemRenderer.render(item)));
        }
    }
}
