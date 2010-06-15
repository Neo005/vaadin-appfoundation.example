package org.vaadin.appfoundation.example.components;

import org.vaadin.appfoundation.view.AbstractView;
import org.vaadin.appfoundation.view.View;
import org.vaadin.appfoundation.view.ViewContainer;
import org.vaadin.appfoundation.view.ViewHandler;

import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;

public class MainArea extends AbstractView<Panel> implements ViewContainer {

    private static final long serialVersionUID = 9010669373711637452L;

    private View currentView;

    public MainArea() {
        super(new Panel());
        getContent().setSizeFull();
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
    public void activate(View view) {
        if (currentView == null) {
            getContent().addComponent((Component) view);
        } else {
            getContent().replaceComponent((Component) currentView,
                    (Component) view);
        }
        currentView = view;
    }

    /**
     * {@inheritDoc}
     */
    public void deactivate(View view) {
        if (currentView != null) {
            getContent().removeComponent((Component) view);
        }
        currentView = null;
    }

}
