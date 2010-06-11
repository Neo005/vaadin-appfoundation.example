package org.vaadin.appfoundation.example.authentication;

import org.vaadin.appfoundation.example.ExampleLoader.Examples;
import org.vaadin.appfoundation.example.components.CodeExample;
import org.vaadin.appfoundation.i18n.Lang;
import org.vaadin.appfoundation.view.AbstractView;

import ys.wikiparser.WikiParser;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ConfiguringAuth extends AbstractView<VerticalLayout> {

    private static final long serialVersionUID = -3149135945432852122L;

    public ConfiguringAuth() {
        super(new VerticalLayout());
        getContent().addComponent(
                new Label(WikiParser.renderXHTML(Lang
                        .getMessage("configuring auth text")),
                        Label.CONTENT_XHTML));

        CodeExample contextListenerExample = new CodeExample(
                Examples.AUTHENTICATE_CONTEXT_LISTENER);
        contextListenerExample.setWidth("100%");
        contextListenerExample.setDefaultCaption(Lang
                .getMessage("example for auth context listener"));
        getContent().addComponent(contextListenerExample);

        CodeExample initAuthModuleExample = new CodeExample(
                Examples.AUTHENTICATE_INIT_MODULE);
        initAuthModuleExample.setWidth("100%");
        initAuthModuleExample.setDefaultCaption(Lang
                .getMessage("auth module init example"));
        getContent().addComponent(initAuthModuleExample);
    }

    @Override
    public void activated(Object... params) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deactivated(Object... params) {
        // TODO Auto-generated method stub

    }

}
