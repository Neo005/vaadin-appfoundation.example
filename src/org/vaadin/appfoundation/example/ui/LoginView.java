package org.vaadin.appfoundation.example.ui;

import org.vaadin.appfoundation.authentication.AuthenticationMessage;
import org.vaadin.appfoundation.authentication.util.AuthenticationUtil;
import org.vaadin.appfoundation.authentication.util.AuthenticationUtil.AFAuthenticationMessage;
import org.vaadin.appfoundation.example.i18n.SystemMsg;
import org.vaadin.appfoundation.view.AbstractView;
import org.vaadin.appfoundation.view.ViewHandler;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
 * View for the login form.
 * 
 * @author Kim
 * 
 */
public class LoginView extends AbstractView<VerticalLayout> implements
        ClickListener {

    private static final long serialVersionUID = -1317007371538208352L;

    private final Label feedback;

    private final TextField username;
    private final TextField password;

    private final Button registerBtn;
    private final Button loginBtn;

    public LoginView() {
        super(new VerticalLayout());

        feedback = new Label();
        content.addComponent(feedback);

        username = new TextField(SystemMsg.GENERIC_USERNAME.get());
        username.setWidth("100%");
        username.setNullRepresentation("");
        content.addComponent(username);

        password = new TextField(SystemMsg.GENERIC_PASSWORD.get());
        password.setWidth("100%");
        password.setSecret(true);
        password.setNullRepresentation("");
        content.addComponent(password);

        loginBtn = new Button(SystemMsg.LOGIN_BTN.get(), this);
        content.addComponent(loginBtn);

        registerBtn = new Button(SystemMsg.REGISTER_BTN.get(), this);
        registerBtn.setStyleName(Button.STYLE_LINK);
        content.addComponent(registerBtn);
    }

    @Override
    public void activated(Object... params) {
        feedback.setValue("");
    }

    @Override
    public void buttonClick(ClickEvent event) {

        if (event.getButton().equals(loginBtn)) {
            String username = (String) this.username.getValue();
            String password = (String) this.password.getValue();

            AuthenticationMessage msg = AuthenticationUtil.authenticate(
                    username, password);

            if (msg.equals(AFAuthenticationMessage.AUTH_SUCCESSFUL)) {
                this.username.setValue(null);
                this.password.setValue(null);
                ViewHandler.activateView(MenuView.class);
                // Update the ad listing
                ViewHandler.activateView(AdListingView.class);
            } else {
                feedback.setValue(SystemMsg.INVALID_CREDENTIALS.get());
                this.password.setValue(null);
            }
        } else {
            ViewHandler.activateView(RegistrationView.class);
        }

    }

}
