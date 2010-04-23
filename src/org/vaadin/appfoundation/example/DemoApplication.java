package org.vaadin.appfoundation.example;

import org.vaadin.appfoundation.view.SimpleViewContainer;

import com.vaadin.Application;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.SplitPanel;
import com.vaadin.ui.Window;

public class DemoApplication extends Application {

    private static final long serialVersionUID = 1L;

    private SplitPanel splitPanel;

    private SimpleViewContainer mainArea;

    private Accordion menu;

    @Override
    public void init() {
        Window mainWindow = new Window();
        mainWindow.setSizeFull();
        setMainWindow(mainWindow);

        splitPanel = new SplitPanel();

        menu = new Accordion();
        mainArea = new SimpleViewContainer();

        splitPanel.addComponent(menu);
        splitPanel.addComponent(mainArea);
        splitPanel.setOrientation(SplitPanel.ORIENTATION_HORIZONTAL);
        splitPanel.setSizeFull();
        splitPanel.setSplitPosition(250, SplitPanel.UNITS_PIXELS);
        mainWindow.setContent(splitPanel);
    }

}
