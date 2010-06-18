package org.vaadin.appfoundation.example;

import java.io.File;
import java.net.URL;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.vaadin.appfoundation.authentication.data.User;
import org.vaadin.appfoundation.authentication.util.PasswordUtil;
import org.vaadin.appfoundation.i18n.InternationalizationServlet;
import org.vaadin.appfoundation.persistence.facade.FacadeFactory;

public class DemoContextListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent arg0) {

    }

    public void contextInitialized(ServletContextEvent arg0) {
        URL url = DemoApplication.class.getClassLoader().getResource(
                "translations.xml");
        File file = new File(url.getPath());
        InternationalizationServlet.loadTranslations(file);
        try {
            FacadeFactory.registerFacade("default", true);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        User user = new User();
        user.setUsername("demo");
        user.setPassword(PasswordUtil.generateHashedPassword("demo123"));
        FacadeFactory.getFacade().store(user);

        User user2 = new User();
        user2.setUsername("demo2");
        user2.setPassword(PasswordUtil.generateHashedPassword("demo123"));
        FacadeFactory.getFacade().store(user2);
    }

}
