package com.extjs.gxt.ui.client.widget;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.util.SafeGxt;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.text.shared.SafeHtmlRenderer;


public class ModelPropertyRenderer<M extends ModelData> implements SafeHtmlRenderer<M> {

    private String propertyName;

    public ModelPropertyRenderer(String propertyName) {
        this.propertyName = propertyName;
    }

    @Override
    public SafeHtml render(ModelData object) {
        return SafeGxt.fromNullable(object.get(propertyName));
    }

    @Override
    public void render(ModelData model, SafeHtmlBuilder builder) {
        Object value = model.get(propertyName);
        if(value != null) {
            String stringValue = value.toString();
            if(stringValue.length() > 0) {
                builder.appendEscaped(stringValue);
            }
        }
    }
}
