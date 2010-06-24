package org.vaadin.appfoundation.example;

import org.vaadin.appfoundation.authentication.data.User;
import org.vaadin.appfoundation.authentication.util.PasswordUtil;
import org.vaadin.appfoundation.view.ViewHandler;

import com.vaadin.Application;
import com.vaadin.service.ApplicationContext.TransactionListener;

public class ExampleData implements TransactionListener {

    private static final long serialVersionUID = 5184835038248219005L;

    // Store this instance of the view handler in this thread local variable
    private static final ThreadLocal<ExampleData> instance = new ThreadLocal<ExampleData>();

    private final Application application;

    private User user1;

    private User user2;

    public ExampleData(Application application) {
        instance.set(this);
        this.application = application;
    }

    /**
     * {@inheritDoc}
     */
    public void transactionEnd(Application application, Object transactionData) {
        // Clear thread local instance at the end of the transaction
        if (this.application == application) {
            instance.set(null);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void transactionStart(Application application, Object transactionData) {
        // Set the thread local instance
        if (this.application == application) {
            instance.set(this);
        }
    }

    /**
     * Initializes the {@link ViewHandler} for the given {@link Application}
     * 
     * @param application
     */
    public static void initialize(Application application) {
        if (application == null) {
            throw new IllegalArgumentException("Application may not be null");
        }
        ExampleData exampleData = new ExampleData(application);
        application.getContext().addTransactionListener(exampleData);
    }

    public static User getUser(Long id) {
        if (id == 1L) {
            if (instance.get().user1 == null) {
                instance.get().user1 = new User();
                instance.get().user1.setUsername("demo");
                instance.get().user1.setPassword(PasswordUtil
                        .generateHashedPassword("demo123"));
            }

            return instance.get().user1;
        } else if (id == 2L) {
            if (instance.get().user2 == null) {
                instance.get().user2 = new User();
                instance.get().user2.setUsername("demo2");
                instance.get().user2.setPassword(PasswordUtil
                        .generateHashedPassword("demo123"));
            }

            return instance.get().user2;
        }

        return null;
    }

}
