package org.vaadin.appfoundation.example;

import org.vaadin.appfoundation.example.authentication.AuthIntro;
import org.vaadin.appfoundation.example.authentication.ChangePassword;
import org.vaadin.appfoundation.example.authentication.ConfiguringAuth;
import org.vaadin.appfoundation.example.authentication.FetchUser;
import org.vaadin.appfoundation.example.authentication.GettingUserInstance;
import org.vaadin.appfoundation.example.authentication.LogoutExample;
import org.vaadin.appfoundation.example.authentication.PasswordUtilityMethods;
import org.vaadin.appfoundation.example.authentication.RegisterUser;
import org.vaadin.appfoundation.example.authentication.StoreUser;
import org.vaadin.appfoundation.example.authentication.UserAuth;
import org.vaadin.appfoundation.example.authorization.AuthorizationIntro;
import org.vaadin.appfoundation.example.authorization.CheckingAccessRights;
import org.vaadin.appfoundation.example.authorization.DenyingAccess;
import org.vaadin.appfoundation.example.authorization.GrantingAccess;
import org.vaadin.appfoundation.example.authorization.InitAuthorization;
import org.vaadin.appfoundation.example.authorization.JPAPm;
import org.vaadin.appfoundation.example.authorization.MemPm;
import org.vaadin.appfoundation.example.authorization.PermissionManagers;
import org.vaadin.appfoundation.example.authorization.RemovingPermissions;
import org.vaadin.appfoundation.example.authorization.Resources;
import org.vaadin.appfoundation.example.authorization.Roles;
import org.vaadin.appfoundation.example.components.MainArea;
import org.vaadin.appfoundation.example.i18n.ConfigureAndIniI18n;
import org.vaadin.appfoundation.example.i18n.FieldTranslations;
import org.vaadin.appfoundation.example.i18n.GettingMessages;
import org.vaadin.appfoundation.example.i18n.I18nIntro;
import org.vaadin.appfoundation.example.i18n.TranslationsFile;
import org.vaadin.appfoundation.example.i18n.UpdatingTranslationsFile;
import org.vaadin.appfoundation.example.persistence.ConfiguringPersistence;
import org.vaadin.appfoundation.example.persistence.CreatingPojos;
import org.vaadin.appfoundation.example.persistence.FacadeFactoryExamples;
import org.vaadin.appfoundation.example.persistence.FetchingData;
import org.vaadin.appfoundation.example.persistence.PersistenceIntro;
import org.vaadin.appfoundation.example.persistence.RemovingData;
import org.vaadin.appfoundation.example.persistence.StoringData;
import org.vaadin.appfoundation.example.view.ConfiguringView;
import org.vaadin.appfoundation.example.view.ViewContainerExample;
import org.vaadin.appfoundation.example.view.ViewEvents;
import org.vaadin.appfoundation.example.view.ViewFactories;
import org.vaadin.appfoundation.example.view.ViewHandlerExample;
import org.vaadin.appfoundation.example.view.ViewIntro;
import org.vaadin.appfoundation.example.view.Views;
import org.vaadin.appfoundation.i18n.Lang;
import org.vaadin.appfoundation.view.View;
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
    private Tree authorizationModuleTree;
    private Tree i18nModuleTree;
    private Tree persistenceModuleTree;
    private Tree viewModuleTree;

    public MainWindow() {
        setCaption(Lang.getMessage("application foundation"));

        buildMainLayout();
        buildAuthenticationModule();
        buildAuthorizationModule();
        buildI18nModule();
        buildPersistenceModule();
        buildViewModule();

        ViewHandler.addView(MainView.class, this);
        ViewHandler.activateView(MainView.class);
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

    private void buildAuthorizationModule() {
        initAuthorizationTree();
        addAuthorizationViews();

        Tab tab = menu.addTab(authorizationModuleTree);
        tab.setCaption(Lang.getMessage("authorization"));
    }

    private void buildI18nModule() {
        initI18nTree();
        addI18nViews();

        Tab tab = menu.addTab(i18nModuleTree);
        tab.setCaption(Lang.getMessage("i18n"));
    }

    private void buildPersistenceModule() {
        initPersistenceTree();
        addPersistenceViews();

        Tab tab = menu.addTab(persistenceModuleTree);
        tab.setCaption(Lang.getMessage("persistence"));
    }

    private void buildViewModule() {
        initViewTree();
        addViewViews();

        Tab tab = menu.addTab(viewModuleTree);
        tab.setCaption(Lang.getMessage("view module"));
    }

    private void initAuthTree() {
        authModuleTree = new Tree();
        prepareTree(authModuleTree);
    }

    private void initAuthorizationTree() {
        authorizationModuleTree = new Tree();
        prepareTree(authorizationModuleTree);
    }

    private void initI18nTree() {
        i18nModuleTree = new Tree();
        prepareTree(i18nModuleTree);
    }

    private void initPersistenceTree() {
        persistenceModuleTree = new Tree();
        prepareTree(persistenceModuleTree);
    }

    private void initViewTree() {
        viewModuleTree = new Tree();
        prepareTree(viewModuleTree);
    }

    private void prepareTree(Tree tree) {
        tree.addContainerProperty("name", String.class, null);
        tree.setItemCaptionPropertyId("name");
        tree.addListener(this);
        tree.setImmediate(true);
    }

    private void addAuthViews() {
        addViewToModule(authModuleTree, AuthIntro.class, "auth intro",
                "auth-intro");
        addViewToModule(authModuleTree, ConfiguringAuth.class,
                "configuring auth", "auth-config");
        addViewToModule(authModuleTree, UserAuth.class, "auth a user",
                "auth-authenticate");
        addViewToModule(authModuleTree, GettingUserInstance.class,
                "getting user instance caption", "get-user-instance");
        addViewToModule(authModuleTree, LogoutExample.class,
                "logging out a user caption", "logout");
        addViewToModule(authModuleTree, ChangePassword.class,
                "change password", "change-password");
        addViewToModule(authModuleTree, RegisterUser.class, "register user",
                "register-user");
        addViewToModule(authModuleTree, FetchUser.class, "fetching users",
                "fetch-user");
        addViewToModule(authModuleTree, StoreUser.class, "storing users",
                "store-user");
        addViewToModule(authModuleTree, PasswordUtilityMethods.class,
                "password util", "password-util");
    }

    private void addAuthorizationViews() {
        addViewToModule(authorizationModuleTree, AuthorizationIntro.class,
                "intro to authorization", "authorization-intro");
        addViewToModule(authorizationModuleTree, Resources.class, "resources",
                "resources");
        addViewToModule(authorizationModuleTree, Roles.class, "roles", "roles");
        addViewToModule(authorizationModuleTree, PermissionManagers.class,
                "permission managers", "permission-managers");
        addViewToModule(authorizationModuleTree, JPAPm.class, "jpapm",
                "jpa-permission-managers");
        addViewToModule(authorizationModuleTree, MemPm.class, "mempm",
                "mem-permission-managers");
        addViewToModule(authorizationModuleTree, InitAuthorization.class,
                "init authorization", "init-authorization");
        addViewToModule(authorizationModuleTree, GrantingAccess.class,
                "granting access", "granting-access");
        addViewToModule(authorizationModuleTree, DenyingAccess.class,
                "denying access", "denying-access");
        addViewToModule(authorizationModuleTree, CheckingAccessRights.class,
                "checking for access rights", "checking-access");
        addViewToModule(authorizationModuleTree, RemovingPermissions.class,
                "removing permissions", "removing-permissions");
    }

    private void addI18nViews() {
        addViewToModule(i18nModuleTree, I18nIntro.class, "i18n intro",
                "i18n-intro");
        addViewToModule(i18nModuleTree, TranslationsFile.class,
                "translations file", "translations");
        addViewToModule(i18nModuleTree, UpdatingTranslationsFile.class,
                "updating translations file", "updating-translations");
        addViewToModule(i18nModuleTree, ConfigureAndIniI18n.class,
                "configure i18n", "i18n-config");
        addViewToModule(i18nModuleTree, GettingMessages.class,
                "getting messages", "getmsg");
        addViewToModule(i18nModuleTree, FieldTranslations.class,
                "field translations", "i18n-forms");
    }

    private void addPersistenceViews() {
        addViewToModule(persistenceModuleTree, PersistenceIntro.class,
                "persistence module intro", "persistence-intro");
        addViewToModule(persistenceModuleTree, ConfiguringPersistence.class,
                "configuring persistence module", "persistence-config");
        addViewToModule(persistenceModuleTree, FacadeFactoryExamples.class,
                "facade factory exmaple", "facadefactory");
        addViewToModule(persistenceModuleTree, CreatingPojos.class,
                "creating pojos", "creating-pojos");
        addViewToModule(persistenceModuleTree, FetchingData.class,
                "fetching data", "fetching-data");
        addViewToModule(persistenceModuleTree, StoringData.class,
                "storing data", "storing-data");
        addViewToModule(persistenceModuleTree, RemovingData.class,
                "removing data", "removing-data");
    }

    private void addViewViews() {
        addViewToModule(viewModuleTree, ViewIntro.class, "view module intro",
                "view-intro");
        addViewToModule(viewModuleTree, ConfiguringView.class,
                "configuring view module", "view-config");
        addViewToModule(viewModuleTree, Views.class, "views", "views");
        addViewToModule(viewModuleTree, ViewContainerExample.class,
                "view container", "view-container");
        addViewToModule(viewModuleTree, ViewEvents.class, "view events",
                "view-events");
        addViewToModule(viewModuleTree, ViewHandlerExample.class,
                "view handler example", "viewhandler");
        addViewToModule(viewModuleTree, ViewFactories.class, "view factories",
                "viewfactories");
    }

    private void addViewToModule(Tree tree, Class<? extends View> c,
            String captionTuid, String uri) {
        ViewHandler.addView(c, this);
        ViewHandler.addUri(uri, c);

        Item item = tree.addItem(c);
        item.getItemProperty("name").setValue(Lang.getMessage(captionTuid));
    }

    public void activate(View view) {
        mainArea.activate(view);
    }

    public void deactivate(View view) {
        mainArea.deactivate(view);
    }

    public void valueChange(ValueChangeEvent event) {
        if (event.getProperty().getValue() != null) {
            ViewHandler.activateView(event.getProperty().getValue(), true);
        }
    }

}
