package org.vaadin.appfoundation.example.authentication;

import org.vaadin.appfoundation.i18n.Lang;
import org.vaadin.appfoundation.view.AbstractView;

import ys.wikiparser.WikiParser;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class AuthIntro extends AbstractView<VerticalLayout> {

	private static final long serialVersionUID = 3319859864849238330L;

	public AuthIntro() {
		super(new VerticalLayout());

		getContent().addComponent(
				new Label(WikiParser.renderXHTML(Lang
						.getMessage("intro text to auth module")),
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
