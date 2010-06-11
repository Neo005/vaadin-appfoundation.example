package org.vaadin.appfoundation.example.authentication;

import org.vaadin.appfoundation.example.ExampleLoader.Examples;
import org.vaadin.appfoundation.example.components.CodeExample;
import org.vaadin.appfoundation.i18n.Lang;
import org.vaadin.appfoundation.view.AbstractView;

import ys.wikiparser.WikiParser;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class GettingUserInstance extends AbstractView<VerticalLayout> {

    private static final long serialVersionUID = 3319859864849238330L;

    public GettingUserInstance() {
        super(new VerticalLayout());

        getContent().addComponent(
                new Label(WikiParser.renderXHTML(Lang
                        .getMessage("getting user instance")),
                        Label.CONTENT_XHTML));

        CodeExample getUserInstanceExample = new CodeExample(
                Examples.AUTHENTICATE_GET_USER_INSTANCE);
        getUserInstanceExample.setWidth("100%");
        getContent().addComponent(getUserInstanceExample);
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
