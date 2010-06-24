package org.vaadin.appfoundation.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class ExampleLoader {

    public static enum Examples {
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
