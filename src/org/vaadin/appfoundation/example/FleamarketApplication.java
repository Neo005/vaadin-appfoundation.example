package org.vaadin.appfoundation.example;

import java.util.Locale;

import org.vaadin.appfoundation.authentication.SessionHandler;
import org.vaadin.appfoundation.example.ui.MainView;
import org.vaadin.appfoundation.i18n.Lang;
import org.vaadin.appfoundation.view.ViewHandler;

import com.vaadin.Application;
import com.vaadin.ui.Window;

/**
 * Application class for the fleamarket example
 * 
 * @author Kim
 * 
 */
public class FleamarketApplication extends Application {
    private static final long serialVersionUID = -1625787661063752023L;

    @Override
    public void init() {
        // Register the SessionHandler and the ViewHandler to the application
        getContext().addTransactionListener(new SessionHandler(this));
        getContext().addTransactionListener(new ViewHandler(this));
        getContext().addTransactionListener(new Lang(this));
        Lang.setLocale(Locale.ENGLISH);

        // Create the main window and add the MainView to it
        Window mainWindow = new Window("Fleamarket Application");
        mainWindow.addComponent(new MainView());
        setMainWindow(mainWindow);
    }

}
