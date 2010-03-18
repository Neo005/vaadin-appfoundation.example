package org.vaadin.appfoundation.example.ui;

import java.util.Calendar;

import org.vaadin.appfoundation.authentication.SessionHandler;
import org.vaadin.appfoundation.example.data.Advertisement;
import org.vaadin.appfoundation.example.i18n.SystemMsg;
import org.vaadin.appfoundation.view.AbstractView;
import org.vaadin.appfoundation.view.ViewHandler;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
 * View which contains links for a user that is logged in.
 * 
 * @author Kim
 * 
 */
public class MenuView extends AbstractView<VerticalLayout> implements
        ClickListener {

    private static final long serialVersionUID = -36926441457470523L;

    private final Label name;
    private final Button addAdvertisementBtn;
    private final Button logoutBtn;

    public MenuView() {
        super(new VerticalLayout());

        name = new Label();
        getContent().addComponent(name);

        addAdvertisementBtn = new Button(SystemMsg.ADD_AD_BTN.get(), this);
        addAdvertisementBtn.setStyleName(Button.STYLE_LINK);
        getContent().addComponent(addAdvertisementBtn);

        logoutBtn = new Button(SystemMsg.LOGOUT_BTN.get(), this);
        logoutBtn.setStyleName(Button.STYLE_LINK);
        getContent().addComponent(logoutBtn);
    }

    @Override
    public void activated(Object... params) {
        name.setValue(SessionHandler.get().getName());
    }

    @Override
    public void buttonClick(ClickEvent event) {
        if (event.getButton().equals(logoutBtn)) {
            SessionHandler.logout();
            ViewHandler.activateView(LoginView.class);
            // Update the ad listing
            ViewHandler.activateView(AdListingView.class);
        } else if (event.getButton().equals(addAdvertisementBtn)) {
            Advertisement ad = new Advertisement();
            // We'll set a short expiration time in this example, so that
            // test messages won't be shown for long
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MINUTE, 5);
            ad.setExpires(cal.getTime());

            ViewHandler.activateView(AdModificationView.class, ad);
        }
    }

}
