package org.vaadin.appfoundation.example.i18n;

import org.vaadin.appfoundation.example.Page;
import org.vaadin.appfoundation.example.ExampleLoader.Examples;

public class GettingMessages extends Page {

    private static final long serialVersionUID = 987190244067153965L;

    public GettingMessages() {
        super("getting messages text");
        addCodeExample(Examples.I18N_EXAMPLE_XML, "example translation xml");
        addWikiText("getting messages text2");
        addCodeExample(Examples.I18N_GET_MSG_TEXT_FIELD, "show code");
        addWikiText("getting messages text3");
        addCodeExample(Examples.I18N_GET_MSG_WITH_PARAM, "show code");
        addWikiText("getting messages text4");
        addCodeExample(Examples.I18N_GET_MSG_VIA_LANG, "show code");
    }
}
