package org.vaadin.appfoundation.example.i18n;

import org.vaadin.appfoundation.example.Page;
import org.vaadin.appfoundation.example.ExampleLoader.Examples;

public class UpdatingTranslationsFile extends Page {

    private static final long serialVersionUID = -4620887553412342431L;

    public UpdatingTranslationsFile() {
        super("updating translations file text");
        addCodeExample(Examples.I18N_ORIGINAL_XML,
                "update trans file code 1 caption");
        addWikiText("updating translations file text2");
        addCodeExample(Examples.I18N_FILE_UPDATER,
                "update trans file code 2 caption");
        addWikiText("updating translations file text3");
        addCodeExample(Examples.I18N_UPDATED_XML,
                "update trans file code 3 caption");
        addWikiText("updating translations file text4");
        addCodeExample(Examples.I18N_LOAD_TRANSLATIONS,
                "update trans file code 4 caption");
    }
}
