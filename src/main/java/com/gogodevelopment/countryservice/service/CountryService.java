package com.gogodevelopment.countryservice.service;

import com.gogodevelopment.countryservice.entity.Country;
import com.gogodevelopment.countryservice.filter.CountryFilter;

import java.util.List;

public interface CountryService {

    void saveList(List<Country> countryList);

    List<Country> getFilteredList(CountryFilter countryFilter);

    void clearTables();
}
