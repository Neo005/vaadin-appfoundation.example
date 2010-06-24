package org.vaadin.appfoundation.example.authentication;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.vaadin.appfoundation.authentication.data.User;
import org.vaadin.appfoundation.persistence.data.AbstractPojo;
import org.vaadin.appfoundation.persistence.facade.IFacade;

public class AuthenticationFacade implements IFacade {

    @Override
    public void close() {
        // TODO Auto-generated method stub

    }

    @Override
    public Long count(Class<? extends AbstractPojo> c) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long count(Class<? extends AbstractPojo> c, String whereClause,
            Map<String, Object> parameters) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(AbstractPojo pojo) {
        // TODO Auto-generated method stub

    }

    @Override
    public <A extends AbstractPojo> void deleteAll(Collection<A> pojos) {
        // TODO Auto-generated method stub

    }

    @Override
    public <A extends AbstractPojo> A find(Class<A> clazz, Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <A extends AbstractPojo> A find(String queryStr,
            Map<String, Object> parameters) {
        if (parameters != null && parameters.containsKey("username")
                && parameters.get("username") != null
                && parameters.get("username").equals("demo")) {
            return (A) new User();
        }
        return null;
    }

    @Override
    public void init(String name) {
        // TODO Auto-generated method stub

    }

    @Override
    public void kill() {
        // TODO Auto-generated method stub

    }

    @Override
    public <A extends AbstractPojo> List<A> list(Class<A> clazz) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <A extends AbstractPojo> List<A> list(String queryStr,
            Map<String, Object> parameters) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <A extends AbstractPojo> void refresh(A pojo) {
        // TODO Auto-generated method stub

    }

    @Override
    public void store(AbstractPojo pojo) {
        // TODO Auto-generated method stub

    }

    @Override
    public <A extends AbstractPojo> void storeAll(Collection<A> pojos) {
        // TODO Auto-generated method stub

    }

}
