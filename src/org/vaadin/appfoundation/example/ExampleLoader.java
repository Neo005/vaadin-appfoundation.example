package org.vaadin.appfoundation.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class ExampleLoader {

    public static enum Examples {
        // Example code for the authentication module
        AUTHENTICATE_USER("examples/authenticate_user.txt"),
        AUTHENTICATE_CONTEXT_LISTENER(
                "examples/authentication_context_listener.txt"),
        AUTHENTICATE_INIT_MODULE("examples/authentication_init_module.txt"),
        AUTHENTICATE_GET_USER_INSTANCE(
                "examples/authentication_get_user_instance.txt"),
        AUTHENTICATE_LOGOUT_LISTENER(
                "examples/authentication_logout_listener.txt"),
        AUTHENTICATE_LOGOUT_BUTTON("examples/authentication_logout_button.txt"),
        AUTHENTICATE_CHANGE_PASSWORD(
                "examples/authentication_change_password.txt"),
        AUTHENTICATE_REGISTER_USER("examples/authentication_register.txt"),
        AUTHENTICATE_FETCH_USER("examples/authentication_fetch_user.txt"),
        AUTHENTICATE_STORE_USER("examples/authentication_store_user.txt"),
        AUTHENTICATE_GENERATE_HASHED_PASSWORD(
                "examples/authentication_generateHashedPassword.txt"),
        AUTHENTICATE_GET_MIN_PWD_LENGTH(
                "examples/authentication_getMinPasswordLength.txt"),
        AUTHENTICATE_PASSWORD_VALIDATORS(
                "examples/authentication_password_validators.txt"),
        AUTHENTICATE_PASSWORD_VALIDITY(
                "examples/authentication_password_validity.txt"),
        AUTHENTICATE_PASSWORD_VERIFICATION(
                "examples/authentication_verify_password.txt"),

        // Example code for the i18n module
        I18N_SERVLET("examples/i18n_configure_servlet.txt"),
        I18N_INIT_LANG("examples/i18n_init_lang.txt"),
        I18N_ORIGINAL_XML("examples/i18n_original_xml.txt"),
        I18N_UPDATED_XML("examples/i18n_updated_xml.txt"),
        I18N_FILE_UPDATER("examples/i18n_file_updater.txt"),
        I18N_LOAD_TRANSLATIONS("examples/i18n_load_translations.txt"),
        I18N_EXAMPLE_XML("examples/i18n_example_translations.txt"),
        I18N_GET_MSG_TEXT_FIELD("examples/i18n_getmsg_ex1.txt"),
        I18N_GET_MSG_WITH_PARAM("examples/i18n_getmsg_ex2.txt"),
        I18N_GET_MSG_VIA_LANG("examples/i18n_getmsg_ex3.txt"),

        ;

        private final String fileName;

        private Examples(String fileName) {
            this.fileName = fileName;
        }

        public String getFileName() {
            return fileName;
        }
    }

    public static String getExample(Examples example) {
        URL url = ExampleLoader.class.getClassLoader().getResource(
                example.getFileName());
        StringBuffer fileData = new StringBuffer(1000);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(url
                    .getPath()));
            char[] buf = new char[1024];
            int numRead = 0;
            while ((numRead = reader.read(buf)) != -1) {
                String readData = String.valueOf(buf, 0, numRead);
                fileData.append(readData);
                buf = new char[1024];
            }
            reader.close();
            return fileData.toString();
        } catch (FileNotFoundException e) {
            return "";
        } catch (IOException e) {
            return "";
        }
    }
}
