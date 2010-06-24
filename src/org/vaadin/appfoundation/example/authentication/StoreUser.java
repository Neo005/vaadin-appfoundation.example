package org.vaadin.appfoundation.example.authentication;

import org.vaadin.appfoundation.example.Page;
import org.vaadin.appfoundation.example.ExampleLoader.Examples;

public class StoreUser extends Page {

    private static final long serialVersionUID = 1531144454612314170L;

    public StoreUser() {
        super("storing a user object");
        addCodeExample(Examples.AUTHENTICATE_STORE_USER, "show code");
    }

}
