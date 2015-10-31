package testTask.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import testTask.domain.City;

import java.util.List;

@Repository
public class CityDaoImpl implements CityDao {
    @Autowired
    private SessionFactory sessionFactory;

    public CityDaoImpl() {
    }

    public CityDaoImpl(SessionFactory factory) {
        this.sessionFactory = factory;
    }

    public SessionFactory getFactory() {
        return sessionFactory;
    }

    public void setFactory(SessionFactory factory) {
        this.sessionFactory = factory;
    }

    @Override
    public Long create(City city) {
        return (Long)sessionFactory.getCurrentSession().save(city);
    }

    @Override
    public City read(Long id) {
        return (City)sessionFactory.getCurrentSession().get(City.class, id);
    }

    @Override
    public boolean update(City city) {
        sessionFactory.getCurrentSession().update(city);
        return true;
    }

    @Override
    public boolean delete(City city) {
        sessionFactory.getCurrentSession().delete(city);
        return true;
    }

    @Override
    public List findAll() {
        return sessionFactory.getCurrentSession().createQuery("from City").list();
    }
}
