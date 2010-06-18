package org.vaadin.appfoundation.example.authentication;

import javax.persistence.OptimisticLockException;

import org.vaadin.appfoundation.authentication.LogoutEvent;
import org.vaadin.appfoundation.authentication.LogoutListener;
import org.vaadin.appfoundation.authentication.SessionHandler;
import org.vaadin.appfoundation.authentication.data.User;
import org.vaadin.appfoundation.authentication.exceptions.InvalidCredentialsException;
import org.vaadin.appfoundation.authentication.exceptions.PasswordRequirementException;
import org.vaadin.appfoundation.authentication.exceptions.PasswordsDoNotMatchException;
import org.vaadin.appfoundation.authentication.exceptions.TooShortPasswordException;
import org.vaadin.appfoundation.authentication.util.PasswordUtil;
import org.vaadin.appfoundation.authentication.util.UserUtil;
import org.vaadin.appfoundation.example.Page;
import org.vaadin.appfoundation.example.ExampleLoader.Examples;
import org.vaadin.appfoundation.persistence.facade.FacadeFactory;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class ChangePassword extends Page {

    private static final long serialVersionUID = 5204650511382347253L;

    private User currentUser;

    private Panel examplePanel = new Panel();

    public ChangePassword() {
        super("change user password");

        getContent().addComponent(examplePanel);
        addCodeExample(Examples.AUTHENTICATE_CHANGE_PASSWORD, "show code");
    }

    private Layout buildPwdChangeForm() {
        FormLayout layout = new FormLayout();
        // Create a label which we can use to give feedback to the user
        final Label feedbackLabel = new Label();
        final Label logoutLabel = new Label();

        SessionHandler.addListener(new LogoutListener() {

            @Override
            public void logout(LogoutEvent event) {
                logoutLabel
                        .setValue("You entered the password incorrectly too many "
                                + "times, so you would have now been logged out");
                SessionHandler.setUser(event.getUser());
                event.getUser().clearFailedPasswordChangeAttempts();
            }
        });

        // Create input fields for passwords
        final TextField currentPassword = new TextField("Current password");
        currentPassword.setSecret(true);
        currentPassword.setNullRepresentation("");
        final TextField newPassword = new TextField("New password");
        newPassword.setSecret(true);
        newPassword.setNullRepresentation("");
        final TextField newPasswordVerified = new TextField(
                "Verify new password");
        newPasswordVerified.setSecret(true);
        newPasswordVerified.setNullRepresentation("");

        // Add the save button
        Button saveBtn = new Button("Save", new ClickListener() {

            private static final long serialVersionUID = -5577423546946890721L;

            public void buttonClick(ClickEvent event) {
                logoutLabel.setValue(null);
                // Try to change the password when the button is clicked
                String password = (String) currentPassword.getValue();
                String newPasswordStr = (String) newPassword.getValue();
                String newPasswordStr2 = (String) newPasswordVerified
                        .getValue();

                try {
                    UserUtil.changePassword(SessionHandler.get(), password,
                            newPasswordStr, newPasswordStr2);
                } catch (InvalidCredentialsException e) {
                    feedbackLabel.setValue("Current password was invalid!");
                    clearPwdFields();
                    return;
                } catch (TooShortPasswordException e) {
                    feedbackLabel
                            .setValue("New password was too short, the password "
                                    + "needs to be at least "
                                    + PasswordUtil.getMinPasswordLength()
                                    + " characters long.");
                    clearPwdFields();
                    return;
                } catch (PasswordsDoNotMatchException e) {
                    feedbackLabel
                            .setValue("Password verification was incorrect");
                    clearPwdFields();
                    return;
                } catch (PasswordRequirementException e) {
                    feedbackLabel
                            .setValue("Password did not meet set requirements");
                    clearPwdFields();
                    return;
                } catch (OptimisticLockException e) {
                    // IGNORE only in this example, because we don't care that
                    // the password wasn't actually stored.
                }

                feedbackLabel.setValue("Password changed!");
                clearPwdFields();
            }

            private void clearPwdFields() {
                currentPassword.setValue(null);
                newPassword.setValue(null);
                newPasswordVerified.setValue(null);
            }
        });

        layout.addComponent(logoutLabel);
        layout.addComponent(feedbackLabel);
        layout.addComponent(currentPassword);
        layout.addComponent(newPassword);
        layout.addComponent(newPasswordVerified);
        layout.addComponent(saveBtn);

        return layout;
    }

    @Override
    public void activated(Object... params) {
        currentUser = FacadeFactory.getFacade().find(User.class, 2L);
        currentUser.setPassword(PasswordUtil.generateHashedPassword("demo123"));
        FacadeFactory.getFacade().store(currentUser);

        examplePanel.removeAllComponents();
        examplePanel.addComponent(buildPwdChangeForm());
        SessionHandler.setUser(currentUser);
    }

    @Override
    public void deactivated(Object... params) {
        // TODO Auto-generated method stub

    }

}
