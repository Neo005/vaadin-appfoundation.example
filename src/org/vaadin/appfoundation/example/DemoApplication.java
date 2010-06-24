package org.vaadin.appfoundation.example;

import java.util.Locale;

import org.vaadin.appfoundation.authentication.SessionHandler;
import org.vaadin.appfoundation.i18n.Lang;
import org.vaadin.appfoundation.view.ViewHandler;

import com.vaadin.Application;
import com.vaadin.ui.Window;

public class DemoApplication extends Application {

    private static final long serialVersionUID = 1L;

    private Window mainWindow;

    @Override
    public void init() {
        Lang.initialize(this);
        Lang.setLocale(Locale.ENGLISH);
        ViewHandler.initialize(this);
        SessionHandler.initialize(this);
        ExampleData.initialize(this);

        mainWindow = new MainWindow();
        mainWindow.setSizeFull();
        setMainWindow(mainWindow);

    }

}
