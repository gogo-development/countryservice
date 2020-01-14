package com.gogodevelopment.countryservice.service;

import com.gogodevelopment.countryservice.dao.CountryDao;
import com.gogodevelopment.countryservice.entity.Country;
import com.gogodevelopment.countryservice.filter.CountryFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryDao countryDao;

    @Autowired
    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    @Transactional
    public void saveList(List<Country> countryList) {

        for (Country country: countryList)
            countryDao.saveOrUpdate(country);
    }

    @Override
    @Transactional
    public List<Country> getFilteredList(CountryFilter countryFilter) {
        return countryDao.getFilteredList(countryFilter);
    }

    @Override
    @Transactional
    public void clearTables() {
        countryDao.clearTables();
    }
}
