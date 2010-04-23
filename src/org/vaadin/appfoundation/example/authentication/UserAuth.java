package org.vaadin.appfoundation.example.authentication;

import org.vaadin.appfoundation.authentication.exceptions.AccountLockedException;
import org.vaadin.appfoundation.authentication.exceptions.InvalidCredentialsException;
import org.vaadin.appfoundation.authentication.util.AuthenticationUtil;
import org.vaadin.appfoundation.example.ExampleLoader.Examples;
import org.vaadin.appfoundation.example.components.CodeExample;
import org.vaadin.appfoundation.i18n.Lang;
import org.vaadin.appfoundation.view.AbstractView;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class UserAuth extends AbstractView<VerticalLayout> {

    private static final long serialVersionUID = 3319859864849238330L;

    public UserAuth() {
        super(new VerticalLayout());
        setCaption(Lang.getMessage("auth a user"));
        getContent().addComponent(
                new Label(Lang.getMessage("text for auth a user")));

        CodeExample example = new CodeExample(Examples.AUTHENTICATE_USER);
        example.setWidth("100%");
        getContent().addComponent(example);

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
        Button login = new Button(Lang.getMessage("Login"),
                new ClickListener() {

                    private static final long serialVersionUID = -5577423546946890721L;

                    public void buttonClick(ClickEvent event) {
                        // Try to log in the user when the button is clicked
                        String username = (String) usernameField.getValue();
                        String password = (String) passwordField.getValue();
                        try {
                            AuthenticationUtil.authenticate(username, password);
                        } catch (InvalidCredentialsException e) {
                            feedbackLabel
                                    .setValue("Either username or password was wrong");
                        } catch (AccountLockedException e) {
                            feedbackLabel
                                    .setValue("The given account has been locked");
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
        // TODO Auto-generated method stub

    }

    @Override
    public void deactivated(Object... params) {
        // TODO Auto-generated method stub

    }

}
