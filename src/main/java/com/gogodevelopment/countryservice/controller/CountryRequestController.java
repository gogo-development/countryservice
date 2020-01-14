package com.gogodevelopment.countryservice.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogodevelopment.countryservice.entity.Country;
import com.gogodevelopment.countryservice.filter.CountryFilter;
import com.gogodevelopment.countryservice.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CountryRequestController {

    private final CountryService countryService;

    @Autowired
    public CountryRequestController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostConstruct
    private void init() {
        refreshDataSource();
    }

    @GetMapping("/filter")
    public String showCountryRequestForm(Model model) {

        model.addAttribute("countryForm", new CountryFilter());

        return "countryRequestForm";
    }

    @GetMapping("/fireRefreshDataSource")
    public String fireRefreshDataSource () {

        refreshDataSource();

        return "redirect:/filter";
    }

    @PostMapping("/getByFilter")
    public List<Country> getFilteredList (@ModelAttribute CountryFilter countryFilter) {

        return countryService.getFilteredList(countryFilter);
    }

    private void refreshDataSource() {
        //TODO: drop table

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.disable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);

        URL url = null;

        try {
            url = new URL("https://restcountries.eu/rest/v2/all");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        List<Country> countryList = new ArrayList<>();

        // JsonParser used to prevent loading whole JSON source in memory
        try (InputStream inputStream = url.openStream();
                JsonParser jsonParser = objectMapper.getFactory().createParser(inputStream)) {

            if (jsonParser.nextToken() != JsonToken.START_ARRAY) {
                throw new IllegalStateException("Unexpected start of array");
            }

            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {

                Country country = objectMapper.readValue(jsonParser, Country.class);
                countryList.add(country);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // TODO
        System.out.println("countryList.size(): " + countryList.size());
        countryService.saveList(countryList);
    }

}
