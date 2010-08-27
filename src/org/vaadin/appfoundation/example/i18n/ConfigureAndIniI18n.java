package org.vaadin.appfoundation.example.i18n;

import org.vaadin.appfoundation.example.Page;
import org.vaadin.appfoundation.example.ExampleLoader.Examples;

public class ConfigureAndIniI18n extends Page {

    private static final long serialVersionUID = -2316783633541238871L;

    public ConfigureAndIniI18n() {
        super("configure i18n text");
        addCodeExample(Examples.I18N_SERVLET, "show code");
        addCodeExample(Examples.I18N_INIT_LANG, "show code");
    }
}
