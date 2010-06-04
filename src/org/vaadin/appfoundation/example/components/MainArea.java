package org.vaadin.appfoundation.example.components;

import org.vaadin.appfoundation.view.AbstractView;
import org.vaadin.appfoundation.view.ViewContainer;
import org.vaadin.appfoundation.view.ViewHandler;

import com.vaadin.ui.Panel;

public class MainArea extends AbstractView<Panel> implements ViewContainer {

    private static final long serialVersionUID = 9010669373711637452L;

    private AbstractView<?> currentView;

    public MainArea() {
        super(new Panel());
        getContent().addComponent(ViewHandler.getUriFragmentUtil());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void activated(Object... params) {
        // Do nothing
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deactivated(Object... params) {
        // Do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void activate(AbstractView<?> view) {
        if (currentView == null) {
            getContent().addComponent(view);
        } else {
            getContent().replaceComponent(currentView, view);
        }
        currentView = view;
    }

    /**
     * {@inheritDoc}
     */
    public void deactivate(AbstractView<?> view) {
        if (currentView != null) {
            getContent().removeComponent(view);
        }
        currentView = null;
    }

}
