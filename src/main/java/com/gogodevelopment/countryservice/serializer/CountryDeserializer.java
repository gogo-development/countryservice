package com.gogodevelopment.countryservice.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.gogodevelopment.countryservice.entity.Country;
import com.gogodevelopment.countryservice.entity.Currency;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

public class CountryDeserializer extends StdDeserializer<Country> {

    public CountryDeserializer() {
        this(null);
    }

    public CountryDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Country deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        Country result = new Country();
        JsonNode node = jp.getCodec().readTree(jp);
        ObjectMapper mapper = new ObjectMapper();

        // primitives
        result.setName(node.get("name").asText());
        result.setPopulation(new BigInteger(node.get("population").asText()));
        result.setFlag(node.get("flag").asText());

        // fully parsed objects
        ObjectReader currencyReader = mapper.readerFor(new TypeReference<List<Currency>>() {});
        List<Currency> currencyList = currencyReader.readValue(node.get("currencies"));
        result.setCurrencyList(currencyList);

        // persisted as plain JSON
        result.setTopLevelDomain(node.findPath("topLevelDomain").toString());
        result.setLatlng(node.findPath("latlng").toString());
        result.setLanguages(node.findPath("languages").toString());
        result.setTranslations(node.findPath("translations").toString());
        result.setRegionalBlocs(node.findPath("regionalBlocs").toString());
//TODO
        System.out.println("CountryDeserializer.deserialize(): " + result);

        return result;
    }
}
