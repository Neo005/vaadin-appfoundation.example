package org.vaadin.appfoundation.example;

import java.io.File;
import java.net.URL;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.vaadin.appfoundation.i18n.InternationalizationServlet;

public class DemoContextListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent arg0) {

    }

    public void contextInitialized(ServletContextEvent arg0) {
        URL url = DemoApplication.class.getClassLoader().getResource(
                "translations.xml");
        File file = new File(url.getPath());
        InternationalizationServlet.loadTranslations(file);
    }

}
