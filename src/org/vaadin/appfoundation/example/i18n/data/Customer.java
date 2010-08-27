package org.vaadin.appfoundation.example.i18n.data;

import org.vaadin.appfoundation.i18n.FieldTranslation;

public class Customer {

    @FieldTranslation(tuid = "name")
    private String name;

    @FieldTranslation(tuid = "email")
    private String email;

    @FieldTranslation(tuid = "phone")
    private String phone;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }
}
