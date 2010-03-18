package org.vaadin.appfoundation.example.i18n;

import java.util.Locale;

import org.vaadin.appfoundation.i18n.InternationalizationServlet;

/**
 * An enumeration of all translation messages in the application. Using
 * enumerations will make it a bit easier to maintain messages, as refactoring
 * names will be quite easy.
 * 
 * @author kim
 * 
 */
public enum SystemMsg {

    GENERIC_USERNAME,
    GENERIC_PASSWORD,
    GENERIC_NAME,
    GENERIC_EMAIL,
    GENERIC_CANCEL,
    GENERIC_SAVE,
    GENERIC_MODIFY,
    GENERIC_DELETE,

    REGISTER_BTN,
    LOGIN_BTN,

    INVALID_CREDENTIALS,
    AUTH_SUCCESSFUL,

    ADD_AD_BTN,
    LOGOUT_BTN,

    VERIFY_PASSWORD,
    REGISTRATION,

    SUBJECT,
    AD_TEXT,

    TOO_SHORT_PASSWORD,
    TOO_SHORT_USERNAME,
    PASSWORDS_DO_NOT_MATCH,
    USERNAME_TAKEN,
    REGISTRATION_COMPLETED,

    ERROR_FIELD_MAY_NOT_BE_EMPTY,
    OOPS,
    ERROR_OPTIMISTIC_LOCKING
    ;

    public String get(Object... params) {
        return InternationalizationServlet.getMessage(Locale.getDefault()
                .getLanguage(), name(), params);
    }

}
