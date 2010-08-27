package org.vaadin.appfoundation.example.i18n;

import java.util.Locale;

import org.vaadin.appfoundation.example.Page;
import org.vaadin.appfoundation.example.ExampleLoader.Examples;
import org.vaadin.appfoundation.example.i18n.data.Customer;
import org.vaadin.appfoundation.i18n.I18nForm;
import org.vaadin.appfoundation.i18n.Lang;

import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Select;

public class FieldTranslations extends Page {

    private static final long serialVersionUID = 2329869742045540584L;

    public FieldTranslations() {
        super("field translations text");
        addCodeExample(Examples.I18N_CUSTOMER_POJO, "customer pojo");
        addCodeExample(Examples.I18N_CUSTOMER_POJO_TRANSLATIONS,
                "customer pojo translations");
        addWikiText("field translations text2");

        addForm();
        addCodeExample(Examples.I18N_FORM, "show code");

    }

    private void addForm() {
        Panel panel = new Panel();
        final Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setPhone("+123 456 7890");
        customer.setEmail("john@some.site");
        final I18nForm form = new I18nForm(Customer.class);

        Select select = new Select();
        select.addContainerProperty("name", String.class, null);
        select.setItemCaptionPropertyId("name");
        Item item = select.addItem("en");
        item.getItemProperty("name").setValue(Lang.getMessage("en language"));
        item = select.addItem("de");
        item.getItemProperty("name").setValue(Lang.getMessage("de language"));

        select.setImmediate(true);
        select.addListener(new ValueChangeListener() {
            private static final long serialVersionUID = -1667702475800410396L;

            @Override
            public void valueChange(ValueChangeEvent event) {
                Object value = event.getProperty().getValue();
                Lang.setLocale(value.equals("en") ? Locale.ENGLISH
                        : Locale.GERMAN);
                BeanItem<Customer> customerItem = new BeanItem<Customer>(
                        customer);
                form.setItemDataSource(customerItem);
                Lang.setLocale(Locale.ENGLISH);
            }
        });

        select.select("en");
        panel.addComponent(select);
        panel.addComponent(form);
        getContent().addComponent(panel);

    }
}
