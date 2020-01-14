package com.gogodevelopment.countryservice.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.gogodevelopment.countryservice.entity.Country;

import java.io.IOException;

public class CountrySerializer extends StdSerializer<Country> {

    public CountrySerializer() {
        this(null);
    }

    public CountrySerializer(Class<Country> t) {
        super(t);
    }

    @Override
    public void serialize(
            Country value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();

        jgen.writeStringField("name", value.getName());

        jgen.writeFieldName("topLevelDomain");
        jgen.writeRawValue(value.getTopLevelDomain());

        jgen.writeNumberField("population", value.getPopulation());

        jgen.writeFieldName("latlng");
        jgen.writeRawValue(value.getLatlng());

        jgen.writeFieldName("currencies");
        jgen.writeRawValue(new ObjectMapper().writeValueAsString(value.getCurrencies()));

        jgen.writeFieldName("languages");
        jgen.writeRawValue(value.getLanguages());

        jgen.writeFieldName("translations");
        jgen.writeRawValue(value.getTranslations());

        jgen.writeStringField("flag", value.getFlag());

        jgen.writeFieldName("regionalBlocs");
        jgen.writeRawValue(value.getRegionalBlocs());

        jgen.writeEndObject();
    }
}