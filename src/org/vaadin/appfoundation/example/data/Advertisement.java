package org.vaadin.appfoundation.example.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.vaadin.appfoundation.authentication.SessionHandler;
import org.vaadin.appfoundation.authentication.data.User;
import org.vaadin.appfoundation.i18n.FieldTranslation;
import org.vaadin.appfoundation.persistence.data.AbstractPojo;

/**
 * Entity class for storing Advertisements
 * 
 * @author Kim
 * 
 */
@Entity
public class Advertisement extends AbstractPojo {

    private static final long serialVersionUID = 6096596529412013317L;

    @FieldTranslation(tuid = "SUBJECT")
    @Column(length = 4096)
    private String subject;

    @FieldTranslation(tuid = "AD_TEXT")
    @Column(length = 4096)
    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expires;

    @ManyToOne
    private User sender;

    public Advertisement() {

    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    @PrePersist
    public void updateDate() {
        setDate(new Date());
        setSender(SessionHandler.get());
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }

    public Date getExpires() {
        return expires;
    }

}
