package com.extjs.gxt.ui.client.util;


import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

public class SafeGxt {

    public static SafeHtml NO_BREAK_SPACE = SafeHtmlUtils.fromSafeConstant("&#160;");

    public static SafeHtml emptyToNbSpace(SafeHtml html) {
        if (html == null) {
            return NO_BREAK_SPACE;
        } else if (html.asString().length() == 0) {
            return NO_BREAK_SPACE;
        } else {
            return html;
        }
    }

    public static SafeHtml fromNullableString(String text) {
        if(text == null) {
            return SafeHtmlUtils.EMPTY_SAFE_HTML;
        } else {
            return SafeHtmlUtils.fromString(text);
        }
    }

    public static SafeHtml fromNullable(Object o) {
        if(o == null) {
            return SafeHtmlUtils.EMPTY_SAFE_HTML;
        }
        return SafeHtmlUtils.fromString(o.toString());
    }

    public static boolean isNullOrEmpty(SafeHtml safeHtml) {
        if(safeHtml == null) {
            return true;
        }
        String html = safeHtml.asString();
        if(html.length() == 0 || html.equals("&#160;") || html.equals("&nbsp;")) {
            return true;
        }
        return false;
    }

    public static SafeHtml emptyToNbSpace(String text) {
        if(text == null) {
            return NO_BREAK_SPACE;
        }
        if(text.length() == 0) {
            return NO_BREAK_SPACE;
        }
        return SafeHtmlUtils.fromString(text);
    }
}
