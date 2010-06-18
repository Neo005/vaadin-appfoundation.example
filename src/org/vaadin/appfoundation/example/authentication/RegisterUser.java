package org.vaadin.appfoundation.example.authentication;

import org.vaadin.appfoundation.authentication.data.User;
import org.vaadin.appfoundation.authentication.exceptions.PasswordRequirementException;
import org.vaadin.appfoundation.authentication.exceptions.PasswordsDoNotMatchException;
import org.vaadin.appfoundation.authentication.exceptions.TooShortPasswordException;
import org.vaadin.appfoundation.authentication.exceptions.TooShortUsernameException;
import org.vaadin.appfoundation.authentication.exceptions.UsernameExistsException;
import org.vaadin.appfoundation.authentication.util.UserUtil;
import org.vaadin.appfoundation.example.Page;
import org.vaadin.appfoundation.persistence.facade.FacadeFactory;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class RegisterUser extends Page {

    private static final long serialVersionUID = 3319859864849238330L;

    private Panel examplePanel = new Panel();

    public RegisterUser() {
        super("registering a user");

        getContent().addComponent(examplePanel);
    }

    private Layout buildRegisterForm() {
        FormLayout layout = new FormLayout();

        final Label feedbackLabel = new Label();
        layout.addComponent(feedbackLabel);

        final TextField username = new TextField("Username");
        username.setNullRepresentation("");
        layout.addComponent(username);

        final TextField password = new TextField("Password");
        password.setNullRepresentation("");
        password.setSecret(true);
        layout.addComponent(password);

        final TextField verifyPassword = new TextField("Verify password");
        verifyPassword.setNullRepresentation("");
        verifyPassword.setSecret(true);
        layout.addComponent(verifyPassword);

        final TextField realName = new TextField("Real name");
        realName.setNullRepresentation("");
        layout.addComponent(realName);

        final TextField email = new TextField("Email address");
        email.setNullRepresentation("");
        layout.addComponent(email);

        Button registerButton = new Button("Register", new ClickListener() {

            private static final long serialVersionUID = 9048069425045731789L;

            @Override
            public void buttonClick(ClickEvent event) {
                try {
                    User user = UserUtil.registerUser((String) username
                            .getValue(), (String) password.getValue(),
                            (String) verifyPassword.getValue());
                    // user successfully registered
                    user.setName((String) realName.getValue());
                    user.setEmail((String) email.getValue());
                    // The user pojo needs to be stored as we've added the name
                    // and email address as additional information
                    FacadeFactory.getFacade().store(user);
                } catch (TooShortPasswordException e) {
                    feedbackLabel
                            .setValue("Password is too short, it needs to be at least "
                                    + UserUtil.getMinPasswordLength()
                                    + " characters long");
                } catch (TooShortUsernameException e) {
                    feedbackLabel
                            .setValue("Username is too short, it needs to be at least "
                                    + UserUtil.getMinUsernameLength()
                                    + " characters long");
                } catch (PasswordsDoNotMatchException e) {
                    feedbackLabel.setValue("Password verification has failed");
                } catch (UsernameExistsException e) {
                    feedbackLabel
                            .setValue("The chosen username already exists, please pick another one");
                } catch (PasswordRequirementException e) {
                    feedbackLabel
                            .setValue("Password does not meet the set requirements");
                }

                password.setValue(null);
                verifyPassword.setValue(null);

            }
        });
        layout.addComponent(registerButton);

        return layout;
    }

    @Override
    public void activated(Object... params) {
        examplePanel.removeAllComponents();
        examplePanel.addComponent(buildRegisterForm());
    }
}
