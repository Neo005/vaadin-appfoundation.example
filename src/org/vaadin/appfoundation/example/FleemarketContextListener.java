package org.vaadin.appfoundation.example;

import java.io.File;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.vaadin.appfoundation.authentication.SessionHandler;
import org.vaadin.appfoundation.authentication.data.User;
import org.vaadin.appfoundation.authentication.util.PasswordUtil;
import org.vaadin.appfoundation.authentication.util.UserUtil;
import org.vaadin.appfoundation.example.data.Advertisement;
import org.vaadin.appfoundation.i18n.InternationalizationServlet;
import org.vaadin.appfoundation.persistence.facade.FacadeFactory;

/**
 * A servlet context listener for the application which registers the facade,
 * sets the password salt value and sets up initial data.
 * 
 * @author Kim
 * 
 */
public class FleemarketContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // Nothing
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        try {
            // Register facade
            FacadeFactory.registerFacade("default", true);

            // Set the salt for passwords
            Properties prop = new Properties();
            prop.setProperty("password.salt", "p98t43qwtg43q8hg+8hq");
            prop.setProperty("password.length.min", "4");
            prop.setProperty("username.length.min", "4");
            PasswordUtil.setProperties(prop);
            UserUtil.setProperties(prop);

            // Register a default user
            UserUtil.registerUser("demo", "demo", "demo");
            UserUtil.registerUser("demo2", "demo", "demo");
            UserUtil.registerUser("demo3", "demo", "demo");

            // Get the user we just created and set a name for him
            List<User> users = FacadeFactory.getFacade().list(User.class);

            User defaultUser = null;
            if (users != null && users.size() > 0) {
                defaultUser = users.get(0);
                defaultUser.setName("John Doe");
                FacadeFactory.getFacade().store(defaultUser);
            }

            Calendar cal = Calendar.getInstance();
            cal.set(2030, 1, 1);
            // Create some initial ads
            for (int i = 0; i < 5; i++) {
                SessionHandler.setUser(users.get(rand(users.size())));

                Advertisement ad = new Advertisement();
                ad.setSubject("Lorem ipsum");
                ad.setMessage("Lorem ipsum dolor sit amet, consectetur "
                        + "adipiscing elit. Phasellus a mauris arcu, at "
                        + "fermentum lorem. Fusce a felis volutpat mi "
                        + "euismod vulputate sed nec risus. Aenean pharetra "
                        + "elementum facilisis. Proin ipsum augue, aliquam "
                        + "vel iaculis a, pulvinar vitae libero. Curabitur "
                        + "aliquet commodo tortor vitae venenatis. Ut nulla "
                        + "elit, semper eu vulputate a, laoreet a ipsum.");
                ad.setExpires(cal.getTime());
                FacadeFactory.getFacade().store(ad);
            }

            SessionHandler.setUser(users.get(0));
            Advertisement ad = new Advertisement();
            ad.setSubject("Welcome to the example fleamarket application");
            ad.setMessage("Try registering to this application or use one of "
                    + "the example user accounts, 'demo', 'demo2' or 'demo3'."
                    + " All example accounts have the password 'demo'");
            ad.setExpires(cal.getTime());
            FacadeFactory.getFacade().store(ad);

        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Load translation files into memory
        URL url = FleamarketApplication.class.getClassLoader().getResource(
                "translations.xml");
        File file = new File(url.getPath());
        InternationalizationServlet.loadTranslations(file);
    }

    private int rand(int max) {
        double d = Math.random() * 1000;
        return (int) (Math.round(d) % max);
    }

}
