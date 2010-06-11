package org.vaadin.appfoundation.example;

import org.vaadin.appfoundation.example.authentication.AuthIntro;
import org.vaadin.appfoundation.example.authentication.ConfiguringAuth;
import org.vaadin.appfoundation.example.authentication.GettingUserInstance;
import org.vaadin.appfoundation.example.authentication.UserAuth;
import org.vaadin.appfoundation.example.components.MainArea;
import org.vaadin.appfoundation.i18n.Lang;
import org.vaadin.appfoundation.view.AbstractView;
import org.vaadin.appfoundation.view.ViewContainer;
import org.vaadin.appfoundation.view.ViewHandler;

import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.SplitPanel;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Window;
import com.vaadin.ui.TabSheet.Tab;

public class MainWindow extends Window implements ViewContainer,
        ValueChangeListener {

    private static final long serialVersionUID = -7336305153060921847L;

    private SplitPanel splitPanel;

    private MainArea mainArea;

    private Accordion menu;

    private Tree authModuleTree;

    public MainWindow() {
        buildMainLayout();
        buildAuthenticationModule();
    }

    private void buildMainLayout() {
        splitPanel = new SplitPanel();

        menu = new Accordion();
        mainArea = new MainArea();

        splitPanel.addComponent(menu);
        splitPanel.addComponent(mainArea);
        splitPanel.setOrientation(SplitPanel.ORIENTATION_HORIZONTAL);
        splitPanel.setSizeFull();
        splitPanel.setSplitPosition(250, SplitPanel.UNITS_PIXELS);
        setContent(splitPanel);
    }

    private void buildAuthenticationModule() {
        initAuthTree();
        addAuthViews();

        Tab tab = menu.addTab(authModuleTree);
        tab.setCaption(Lang.getMessage("auth module"));
    }

    private void initAuthTree() {
        authModuleTree = new Tree();
        authModuleTree.addContainerProperty("name", String.class, null);
        authModuleTree.setItemCaptionPropertyId("name");
        authModuleTree.addListener(this);
        authModuleTree.setImmediate(true);
    }

    private void addAuthViews() {
        addViewToAuthModule(AuthIntro.class, "auth intro", "auth-intro");
        addViewToAuthModule(ConfiguringAuth.class, "configuring auth",
                "auth-config");
        addViewToAuthModule(UserAuth.class, "auth a user", "auth-authenticate");
        addViewToAuthModule(GettingUserInstance.class,
                "getting user instance caption", "auth-get-user-instance");
    }

    private void addViewToAuthModule(Class<? extends AbstractView<?>> c,
            String captionTuid, String uri) {
        ViewHandler.addView(c, this);
        ViewHandler.addUri(uri, c);

        Item item = authModuleTree.addItem(c);
        item.getItemProperty("name").setValue(Lang.getMessage(captionTuid));
    }

    public void activate(AbstractView<?> view) {
        mainArea.activate(view);
    }

    public void deactivate(AbstractView<?> view) {
        mainArea.deactivate(view);
    }

    public void valueChange(ValueChangeEvent event) {
        if (event.getProperty().getValue() != null) {
            ViewHandler.activateView(event.getProperty().getValue(), true);
        }
    }

}
