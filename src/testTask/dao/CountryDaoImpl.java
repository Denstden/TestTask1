package testTask.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import testTask.domain.Country;

import java.util.List;

@Repository
public class CountryDaoImpl implements CountryDao {
    @Autowired
    private SessionFactory sessionFactory;

    public CountryDaoImpl() {
    }

    public CountryDaoImpl(SessionFactory factory) {
        this.sessionFactory = factory;
    }

    public SessionFactory getFactory() {
        return sessionFactory;
    }

    public void setFactory(SessionFactory factory) {
        this.sessionFactory = factory;
    }

    @Override
    public Long create(Country country) {
        return (Long)sessionFactory.getCurrentSession().save(country);
    }

    @Override
    public Country read(Long id) {
        return (Country)sessionFactory.getCurrentSession().get(Country.class,id);
    }

    @Override
    public boolean update(Country country) {
        sessionFactory.getCurrentSession().update(country);
        return true;
    }

    @Override
    public boolean delete(Country country) {
        sessionFactory.getCurrentSession().delete(country);
        return true;
    }

    @Override
    public List findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Country ").list();
    }
}
