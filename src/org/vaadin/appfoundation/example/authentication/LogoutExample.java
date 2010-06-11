package org.vaadin.appfoundation.example.authentication;

import org.vaadin.appfoundation.example.ExampleLoader.Examples;
import org.vaadin.appfoundation.example.components.CodeExample;
import org.vaadin.appfoundation.i18n.Lang;
import org.vaadin.appfoundation.view.AbstractView;

import ys.wikiparser.WikiParser;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class LogoutExample extends AbstractView<VerticalLayout> {

    private static final long serialVersionUID = 3319859864849238330L;

    public LogoutExample() {
        super(new VerticalLayout());

        getContent()
                .addComponent(
                        new Label(WikiParser.renderXHTML(Lang
                                .getMessage("logging out a user")),
                                Label.CONTENT_XHTML));

        CodeExample logoutButtonExample = new CodeExample(
                Examples.AUTHENTICATE_LOGOUT_BUTTON);
        logoutButtonExample.setWidth("100%");
        logoutButtonExample
                .setDefaultCaption(Lang.getMessage("logout example"));
        getContent().addComponent(logoutButtonExample);

        CodeExample logoutEventExample = new CodeExample(
                Examples.AUTHENTICATE_LOGOUT_LISTENER);
        logoutEventExample.setWidth("100%");
        logoutEventExample.setDefaultCaption(Lang
                .getMessage("logout event example"));
        getContent().addComponent(logoutEventExample);
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
