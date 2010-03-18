package org.vaadin.appfoundation.example.ui;

import java.util.HashMap;
import java.util.Map;

import org.vaadin.appfoundation.authentication.data.User;
import org.vaadin.appfoundation.authentication.util.UserUtil;
import org.vaadin.appfoundation.authentication.util.UserUtil.RegistrationMsg;
import org.vaadin.appfoundation.example.i18n.SystemMsg;
import org.vaadin.appfoundation.persistence.facade.FacadeFactory;
import org.vaadin.appfoundation.view.AbstractView;
import org.vaadin.appfoundation.view.ViewHandler;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window.Notification;

/**
 * Registration form view
 * 
 * @author Kim
 * 
 */
public class RegistrationView extends AbstractView<Panel> implements
        ClickListener {

    private static final long serialVersionUID = -1646064143497548805L;

    private final Label feedback;
    private final TextField username;
    private final TextField password;
    private final TextField verifyPassword;
    private final TextField name;
    private final TextField email;

    private final Button registerBtn;
    private final Button cancelBtn;

    public RegistrationView() {
        super(new Panel());
        getContent().setCaption(SystemMsg.REGISTRATION.get());

        feedback = new Label();
        getContent().addComponent(feedback);

        username = new TextField(SystemMsg.GENERIC_USERNAME.get());
        username.setWidth("300px");
        username.setNullRepresentation("");
        getContent().addComponent(username);

        password = new TextField(SystemMsg.GENERIC_PASSWORD.get());
        password.setWidth("300px");
        password.setSecret(true);
        password.setNullRepresentation("");
        getContent().addComponent(password);

        verifyPassword = new TextField(SystemMsg.VERIFY_PASSWORD.get());
        verifyPassword.setWidth("300px");
        verifyPassword.setSecret(true);
        verifyPassword.setNullRepresentation("");
        getContent().addComponent(verifyPassword);

        name = new TextField(SystemMsg.GENERIC_NAME.get());
        name.setWidth("300px");
        name.setNullRepresentation("");
        getContent().addComponent(name);

        email = new TextField(SystemMsg.GENERIC_EMAIL.get());
        email.setWidth("300px");
        email.setNullRepresentation("");
        getContent().addComponent(email);

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setSpacing(true);
        registerBtn = new Button(SystemMsg.REGISTER_BTN.get(), this);
        buttonLayout.addComponent(registerBtn);

        cancelBtn = new Button(SystemMsg.GENERIC_CANCEL.get(), this);
        buttonLayout.addComponent(cancelBtn);
        getContent().addComponent(buttonLayout);
    }

    @Override
    public void activated(Object... params) {
        username.setValue(null);
        password.setValue(null);
        verifyPassword.setValue(null);
        name.setValue(null);
        email.setValue(null);
    }

    @Override
    public void buttonClick(ClickEvent event) {
        if (event.getButton().equals(registerBtn)) {
            RegistrationMsg msg = UserUtil.registerUser((String) username
                    .getValue(), (String) password.getValue(),
                    (String) verifyPassword.getValue());

            switch (msg) {
            case TOO_SHORT_USERNAME:
                feedback.setValue(SystemMsg.TOO_SHORT_USERNAME.get(UserUtil
                        .getMinUsernameLength()));
                break;
            case TOO_SHORT_PASSWORD:
                feedback.setValue(SystemMsg.TOO_SHORT_PASSWORD.get(UserUtil
                        .getMinPasswordLength()));
                break;
            case USERNAME_TAKEN:
                feedback.setValue(SystemMsg.USERNAME_TAKEN.get());
                break;
            case PASSWORDS_DO_NOT_MATCH:
                feedback.setValue(SystemMsg.PASSWORDS_DO_NOT_MATCH.get());
                break;
            case REGISTRATION_COMPLETED:
                getApplication().getMainWindow().showNotification(
                        SystemMsg.REGISTRATION_COMPLETED.get(),
                        Notification.TYPE_TRAY_NOTIFICATION);

                String queryStr = "SELECT u FROM User AS u WHERE u.username = :username";
                Map<String, Object> parameters = new HashMap<String, Object>();
                parameters.put("username", username.getValue());
                User user = FacadeFactory.getFacade()
                        .find(queryStr, parameters);
                user.setName((String) name.getValue());
                user.setEmail((String) email.getValue());
                FacadeFactory.getFacade().store(user);

                ViewHandler.activateView(AdListingView.class);
                break;

            default:
                break;
            }

        } else {
            ViewHandler.activateView(AdListingView.class);
        }
    }
}
