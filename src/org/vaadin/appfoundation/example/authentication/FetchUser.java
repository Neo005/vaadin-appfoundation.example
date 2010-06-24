package org.vaadin.appfoundation.example.authentication;

import org.vaadin.appfoundation.example.Page;
import org.vaadin.appfoundation.example.ExampleLoader.Examples;

public class FetchUser extends Page {

    private static final long serialVersionUID = 3336970179429259887L;

    public FetchUser() {
        super("fetching a user object");
        addCodeExample(Examples.AUTHENTICATE_FETCH_USER, "show code");
    }

}
