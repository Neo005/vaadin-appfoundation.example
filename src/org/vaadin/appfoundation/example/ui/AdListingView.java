package org.vaadin.appfoundation.example.ui;

import java.util.List;

import org.vaadin.appfoundation.example.controller.AdsController;
import org.vaadin.appfoundation.example.data.Advertisement;
import org.vaadin.appfoundation.view.AbstractView;

import com.vaadin.ui.VerticalLayout;

/**
 * View for listing ads
 * 
 * @author Kim
 * 
 */
public class AdListingView extends AbstractView<VerticalLayout> {

    private static final long serialVersionUID = -1500635399708409124L;

    public AdListingView() {
        super(new VerticalLayout());
    }

    /**
     * Each time the view is activated, we remove all the components from the
     * view, fetch valid advertisements from the database and add the ads to the
     * view. In a real application, we probably would use polling for checking
     * for new messages.
     */
    @Override
    public void activated(Object... params) {
        content.removeAllComponents();
        // Loop through all the ads and add them to the view
        List<Advertisement> ads = AdsController.getAds();
        if (ads != null) {
            for (Advertisement ad : ads) {
                AdView adView = new AdView(ad);
                content.addComponent(adView);
            }
        }
    }

}
