package com.gogodevelopment.countryservice.dao;

import com.gogodevelopment.countryservice.entity.Country;
import com.gogodevelopment.countryservice.filter.CountryFilter;

import java.util.List;

public interface CountryDao {

    void saveOrUpdate(Country country);

    List<Country> getFilteredList(CountryFilter countryFilter);

    void clearTables();
}
