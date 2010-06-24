package org.vaadin.appfoundation.example.authentication;

import org.vaadin.appfoundation.authentication.util.PasswordUtil;
import org.vaadin.appfoundation.example.Page;
import org.vaadin.appfoundation.example.ExampleLoader.Examples;

import com.vaadin.data.Validator;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.ui.TextField;

public class PasswordUtilityMethods extends Page {

    private static final long serialVersionUID = 3319859864849238330L;

    public PasswordUtilityMethods() {
        super("password utility methods");
        addWikiText("generateHashedPassword");
        addCodeExample(Examples.AUTHENTICATE_GENERATE_HASHED_PASSWORD,
                "show code");

        addWikiText("getMinPasswordLength");
        addCodeExample(Examples.AUTHENTICATE_GET_MIN_PWD_LENGTH, "show code");

        addWikiText("password validators");
        getContent().addComponent(buildPasswordTextField());
        addCodeExample(Examples.AUTHENTICATE_PASSWORD_VALIDATORS, "show code");
    }

    private TextField buildPasswordTextField() {
        final TextField password = new TextField("Password");
        password.setSecret(true);
        password.setNullRepresentation("");

        for (Validator validator : PasswordUtil.getValidators()) {
            password.addValidator(validator);
        }

        // Set the password field to immediate
        password.setImmediate(true);
        // Validate the password right after it has been changed
        password.addListener(new ValueChangeListener() {
            private static final long serialVersionUID = 1666908198186227437L;

            @Override
            public void valueChange(ValueChangeEvent event) {
                try {
                    password.validate();
                } catch (InvalidValueException e) {
                    // IGNORE
                }
            }
        });

        return password;
    }

}
