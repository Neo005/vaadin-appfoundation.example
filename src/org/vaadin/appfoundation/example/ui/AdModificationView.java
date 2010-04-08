package org.vaadin.appfoundation.example.ui;

import javax.persistence.OptimisticLockException;

import org.vaadin.appfoundation.example.data.Advertisement;
import org.vaadin.appfoundation.example.i18n.SystemMsg;
import org.vaadin.appfoundation.i18n.TranslationUtil;
import org.vaadin.appfoundation.persistence.facade.FacadeFactory;
import org.vaadin.appfoundation.view.AbstractView;
import org.vaadin.appfoundation.view.ViewHandler;

import com.vaadin.Application;
import com.vaadin.data.Item;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window.Notification;

/**
 * View for modifying advertisements
 * 
 * @author Kim
 * 
 */
public class AdModificationView extends AbstractView<Panel> implements
        ClickListener {

    private static final long serialVersionUID = -8188276129521064380L;

    private final Form form = new Form();

    // Define the save and cancel buttons, note that the captions are translated
    // strings
    private final Button saveBtn = new Button(SystemMsg.GENERIC_SAVE.get(),
            this);
    private final Button cancelBtn = new Button(SystemMsg.GENERIC_CANCEL.get(),
            this);

    public AdModificationView() {
        super(new Panel());

        // Define a form factory and add the form to the layout
        form.setFormFieldFactory(new AdFieldFactory());
        getContent().addComponent(form);

        // Add the save and cancel buttons to the layout
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setSpacing(true);
        buttonLayout.addComponent(saveBtn);
        buttonLayout.addComponent(cancelBtn);

        getContent().addComponent(buttonLayout);
    }

    @Override
    public void activated(Object... params) {
        // When the view is activated, it should get an Advertisement object as
        // parameter. Use this object to create a BeanItem which will be put as
        // the form's item data source.
        form.setValidationVisible(false);
        if (params != null && params[0] instanceof Advertisement) {
            Advertisement ad = (Advertisement) params[0];
            BeanItem<Advertisement> item = new BeanItem<Advertisement>(ad);
            form.setItemDataSource(item);
            // Only show the subject and message fields
            form
                    .setVisibleItemProperties(new Object[] { "subject",
                            "message" });
        }
    }

    @SuppressWarnings("unchecked")
    public void buttonClick(ClickEvent event) {
        if (event.getButton().equals(saveBtn)) {
            // Save was clicked, validate the form and store the object.
            try {
                form.validate();
                form.commit();
                BeanItem<Advertisement> item = (BeanItem<Advertisement>) form
                        .getItemDataSource();
                FacadeFactory.getFacade().store(item.getBean());
                // Close this view and activate the ad list view
                ViewHandler.activateView(AdListingView.class);
            } catch (OptimisticLockException e) {
                // Get an instance of the application, as getApplication() will
                // return null after activating the other view
                Application application = getApplication();
                ViewHandler.activateView(AdListingView.class);
                application.getMainWindow().showNotification(
                        SystemMsg.OOPS.get(),
                        SystemMsg.ERROR_OPTIMISTIC_LOCKING.get(),
                        Notification.TYPE_ERROR_MESSAGE);
            } catch (InvalidValueException e) {
                form.setValidationVisible(true);
            }
        } else {
            // Cancel was clicked, go back to the ad listing view
            ViewHandler.activateView(AdListingView.class);
        }
    }

    /**
     * Internal form factory for creating the fields for our ad form.
     * 
     * @author Kim
     * 
     */
    class AdFieldFactory implements FormFieldFactory {

        private static final long serialVersionUID = 7983734476850858693L;

        public Field createField(Item item, Object propertyId,
                Component uiContext) {
            // Both our fields are TextFields
            TextField field = new TextField();

            // Get the caption of the field from the Advertisement class's
            // field's @FieldTranslation-annotation.
            field.setCaption(TranslationUtil.getFieldTranslation(
                    Advertisement.class, (String) propertyId));

            // The message field should be larger
            if (propertyId.equals("message")) {
                field.setRows(7);
            }
            field.setWidth("100%");
            field.setRequired(true);
            // Add a translated error message
            field
                    .setRequiredError(SystemMsg.ERROR_FIELD_MAY_NOT_BE_EMPTY
                            .get());
            field.setNullRepresentation("");
            return field;
        }
    }
}
