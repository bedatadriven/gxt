package com.extjs.gxt.ui.client.widget;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.text.shared.SafeHtmlRenderer;

import java.util.List;

public abstract class ListRenderer<T> implements SafeHtmlRenderer<List<T>> {

    @Override
    public final void render(List<T> list, SafeHtmlBuilder builder) {
        for (T item : list) {
            renderItem(item, builder);
        }
    }

    protected abstract void renderItem(T item, SafeHtmlBuilder builder);

    @Override
    public final SafeHtml render(List<T> object) {
        SafeHtmlBuilder builder = new SafeHtmlBuilder();
        render(object, builder);
        return builder.toSafeHtml();
    }
}
