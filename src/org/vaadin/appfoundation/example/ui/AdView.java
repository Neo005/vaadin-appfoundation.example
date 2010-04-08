package org.vaadin.appfoundation.example.ui;

import java.text.SimpleDateFormat;

import org.vaadin.appfoundation.authentication.SessionHandler;
import org.vaadin.appfoundation.authentication.data.User;
import org.vaadin.appfoundation.example.data.Advertisement;
import org.vaadin.appfoundation.example.i18n.SystemMsg;
import org.vaadin.appfoundation.persistence.facade.FacadeFactory;
import org.vaadin.appfoundation.view.ViewHandler;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
 * View for showing an ad. This class does not need to extend AbstractView, as
 * it is not explicitly activated via the ViewHandler.
 * 
 * @author Kim
 * 
 */
public class AdView extends CustomComponent implements ClickListener {

    private static final long serialVersionUID = -3676098762660167331L;

    private final Advertisement ad;

    private final Label text;

    private final Button deleteBtn = new Button(SystemMsg.GENERIC_DELETE.get(),
            this);
    private final Button modifyBtn = new Button(SystemMsg.GENERIC_MODIFY.get(),
            this);

    private final Panel content = new Panel();

    public AdView(Advertisement ad) {
        setCompositionRoot(content);
        this.ad = ad;

        SimpleDateFormat format = new SimpleDateFormat();

        content.setCaption(ad.getSubject() + " - "
                + format.format(ad.getDate()));
        text = new Label();
        text.setValue(ad.getMessage());
        content.addComponent(text);

        // If the user is logged in, then show the modify and delete-buttons for
        // his ads.
        User user = SessionHandler.get();
        if (user != null && ad.getSender().getId().equals(user.getId())) {
            HorizontalLayout buttonLayout = new HorizontalLayout();
            buttonLayout.setWidth("100%");

            Label spacer = new Label();
            buttonLayout.addComponent(spacer);
            buttonLayout.setExpandRatio(spacer, 1);

            buttonLayout.addComponent(modifyBtn);
            buttonLayout.setComponentAlignment(modifyBtn,
                    Alignment.BOTTOM_RIGHT);
            buttonLayout.addComponent(deleteBtn);
            buttonLayout.setComponentAlignment(deleteBtn,
                    Alignment.BOTTOM_RIGHT);
            content.addComponent(buttonLayout);
        }
    }

    public Advertisement getAd() {
        return ad;
    }

    public void buttonClick(ClickEvent event) {
        if (event.getButton().equals(modifyBtn)) {
            ViewHandler.activateView(AdModificationView.class, getAd());
        } else if (event.getButton().equals(deleteBtn)) {
            FacadeFactory.getFacade().delete(getAd());
            // Activate the view to update the ad list
            ViewHandler.activateView(AdListingView.class);
        }
    }

}
