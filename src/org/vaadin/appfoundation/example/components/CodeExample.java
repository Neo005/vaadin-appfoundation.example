package org.vaadin.appfoundation.example.components;

import org.vaadin.appfoundation.example.ExampleLoader;
import org.vaadin.appfoundation.example.ExampleLoader.Examples;
import org.vaadin.appfoundation.i18n.Lang;
import org.vaadin.codelabel.CodeLabel;
import org.vaadin.henrik.drawer.Drawer;

public class CodeExample extends Drawer {

    private static final long serialVersionUID = 981189256494004603L;

    public CodeExample(Examples example) {
        super(Lang.getMessage("show code"), null);

        CodeLabel codeExample = new CodeLabel();
        codeExample.setValue(ExampleLoader.getExample(example));
        setDrawerComponent(codeExample);
    }
}
