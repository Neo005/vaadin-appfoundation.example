package org.vaadin.appfoundation.example.authentication;

import org.vaadin.appfoundation.authentication.SessionHandler;
import org.vaadin.appfoundation.authentication.data.User;
import org.vaadin.appfoundation.authentication.exceptions.AccountLockedException;
import org.vaadin.appfoundation.authentication.exceptions.InvalidCredentialsException;
import org.vaadin.appfoundation.authentication.util.AuthenticationUtil;
import org.vaadin.appfoundation.example.ExampleLoader.Examples;
import org.vaadin.appfoundation.example.components.CodeExample;
import org.vaadin.appfoundation.i18n.Lang;
import org.vaadin.appfoundation.persistence.facade.FacadeFactory;
import org.vaadin.appfoundation.view.AbstractView;

import ys.wikiparser.WikiParser;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class UserAuth extends AbstractView<VerticalLayout> {

    private static final long serialVersionUID = 3319859864849238330L;

    private Panel examplePanel;

    public UserAuth() {
        super(new VerticalLayout());
        getContent().addComponent(
                new Label(WikiParser.renderXHTML(Lang
                        .getMessage("text for auth a user")),
                        Label.CONTENT_XHTML));

        CodeExample example = new CodeExample(Examples.AUTHENTICATE_USER);
        example.setWidth("100%");
        initExamplePanel();

        getContent().addComponent(examplePanel);
        getContent().addComponent(example);

    }

    private void initExamplePanel() {
        examplePanel = new Panel();
    }

    private Layout buildLoginForm() {
        FormLayout layout = new FormLayout();
        // Create a label which we can use to give feedback to the user
        final Label feedbackLabel = new Label();

        // Create input fields for username and password
        final TextField usernameField = new TextField("Username");
        final TextField passwordField = new TextField("Password");
        passwordField.setSecret(true);

        // Add the login button
        Button login = new Button("Login", new ClickListener() {

            private static final long serialVersionUID = -5577423546946890721L;

            public void buttonClick(ClickEvent event) {
                // Try to log in the user when the button is clicked
                String username = (String) usernameField.getValue();
                String password = (String) passwordField.getValue();
                try {
                    loggedInAs(AuthenticationUtil.authenticate(username,
                            password));
                } catch (InvalidCredentialsException e) {
                    feedbackLabel
                            .setValue("Either username or password was wrong");
                } catch (AccountLockedException e) {
                    feedbackLabel.setValue("The given account has been locked");
                }
            }
        });

        layout.addComponent(feedbackLabel);
        layout.addComponent(usernameField);
        layout.addComponent(passwordField);
        layout.addComponent(login);

        return layout;
    }

    @Override
    public void activated(Object... params) {
        User user = FacadeFactory.getFacade().find(User.class, 1L);
        if (user.isAccountLocked()) {
            user.setAccountLocked(false);
            FacadeFactory.getFacade().store(user);
        }
        refreshExamplePanel();
    }

    @Override
    public void deactivated(Object... params) {
        // TODO Auto-generated method stub

    }

    private void loggedInAs(User user) {
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(new Label("You are now logged in as the user "
                + user.getUsername()));
        layout.addComponent(new Button("Click here to log out",
                new ClickListener() {

                    private static final long serialVersionUID = 4067453946827830672L;

                    @Override
                    public void buttonClick(ClickEvent event) {
                        SessionHandler.logout();
                        refreshExamplePanel();
                    }
                }));

        examplePanel.removeAllComponents();
        examplePanel.addComponent(layout);
    }

    private void refreshExamplePanel() {
        examplePanel.removeAllComponents();
        examplePanel.addComponent(buildLoginForm());
    }

}
