package org.vaadin.appfoundation.example;

import org.vaadin.appfoundation.example.ExampleLoader.Examples;
import org.vaadin.appfoundation.example.components.CodeExample;
import org.vaadin.appfoundation.i18n.Lang;
import org.vaadin.appfoundation.view.AbstractView;

import ys.wikiparser.WikiParser;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public abstract class Page extends AbstractView<VerticalLayout> {

    private static final long serialVersionUID = 7964249978981814597L;

    public Page(String tuid) {
        super(new VerticalLayout());
        addWikiText(tuid);
    }

    protected void addCodeExample(Examples example, String captionTuid) {
        CodeExample codeExample = new CodeExample(example);
        codeExample.setWidth("100%");
        if (captionTuid != null) {
            codeExample.setDefaultCaption(Lang.getMessage(captionTuid));
        }

        getContent().addComponent(codeExample);
    }

    protected void addWikiText(String tuid) {
        getContent().addComponent(
                new Label(WikiParser.renderXHTML(Lang.getMessage(tuid)),
                        Label.CONTENT_XHTML));
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
