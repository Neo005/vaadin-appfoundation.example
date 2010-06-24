package org.vaadin.appfoundation.example;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.vaadin.appfoundation.authentication.data.User;
import org.vaadin.appfoundation.persistence.data.AbstractPojo;
import org.vaadin.appfoundation.persistence.facade.IFacade;

public class ExampleMockFacade implements IFacade {

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

    @SuppressWarnings("unchecked")
    @Override
    public <A extends AbstractPojo> A find(Class<A> clazz, Long id) {
        if (clazz.equals(User.class)) {
            User user = ExampleData.getUser(id);

            return (A) user;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <A extends AbstractPojo> A find(String queryStr,
            Map<String, Object> parameters) {
        if (parameterEquals(parameters, "username", "demo")) {
            return (A) find(User.class, 1L);
        } else if (parameterEquals(parameters, "username", "demo2")) {
            return (A) find(User.class, 2L);
        }

        return null;
    }

    private boolean parameterEquals(Map<String, Object> parameters,
            String param, String value) {
        if (parameters == null) {
            return false;
        }

        if (!parameters.containsKey(param)) {
            return false;
        }

        Object paramValue = parameters.get(param);

        if (paramValue == null && value != null) {
            return false;
        }

        if (paramValue.equals(value)) {
            return true;
        }

        return false;

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
