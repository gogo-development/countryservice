package com.gogodevelopment.countryservice.dao;

import com.gogodevelopment.countryservice.entity.Country;
import com.gogodevelopment.countryservice.filter.CountryFilter;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CountryDaoImpl implements CountryDao {

    private final EntityManager entityManager;

    @Autowired
    public CountryDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void saveOrUpdate(Country country) {

        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(country);
    }

    @Override
    public List<Country> getFilteredList(CountryFilter countryFilter) {

        Session session = entityManager.unwrap(Session.class);
        Query<Country> query = session.createQuery(
                "FROM Country WHERE REGEXP_LIKE(name, :name, 'c')" +
                "LOWER(name) LIKE :name AND" +
                " topLevelDomain LIKE :domain", Country.class);
        query.setParameter("name", "'.*" + countryFilter.getName() + ".*'");
        query.setParameter("domain", countryFilter.getDomain());
        //TODO

        return query.getResultList();
    }
}
