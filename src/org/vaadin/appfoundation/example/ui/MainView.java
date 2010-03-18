package org.vaadin.appfoundation.example.ui;

import org.vaadin.appfoundation.view.AbstractView;
import org.vaadin.appfoundation.view.ViewContainer;
import org.vaadin.appfoundation.view.ViewHandler;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class MainView extends HorizontalLayout implements ViewContainer {

    /**
     * 
     */
    private static final long serialVersionUID = 7814598633768569230L;

    private final VerticalLayout ads = new VerticalLayout();

    private AbstractView<?> leftView = null;
    private AbstractView<?> rightView = null;

    public MainView() {
        setSizeFull();
        addComponent(ads);

        // Add margins and spacings
        setMargin(true);
        setSpacing(true);

        // Register the child views for this view container
        ViewHandler.addView(AdListingView.class, this);
        ViewHandler.addView(RegistrationView.class, this);
        ViewHandler.addView(LoginView.class, this);
        ViewHandler.addView(MenuView.class, this);
        ViewHandler.addView(AdModificationView.class, this);

        // Activate the left and right views
        ViewHandler.activateView(AdListingView.class);
        ViewHandler.activateView(LoginView.class);
    }

    @Override
    public void activate(AbstractView<?> view) {
        if (view instanceof LoginView || view instanceof MenuView) {
            if (rightView == null) {
                rightView = view;
                addComponent(view);
            } else {
                replaceComponent(rightView, view);
                rightView = view;
            }

            setComponentAlignment(rightView, Alignment.TOP_RIGHT);
            setExpandRatio(rightView, 1);
        } else {
            if (leftView == null) {
                leftView = view;
                addComponent(view, 0);
            } else {
                replaceComponent(leftView, view);
                leftView = view;
            }
            setExpandRatio(leftView, 3);
        }
    }

}
