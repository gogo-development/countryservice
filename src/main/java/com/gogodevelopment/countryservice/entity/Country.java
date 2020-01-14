package com.gogodevelopment.countryservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gogodevelopment.countryservice.serializer.CountryDeserializer;
import com.gogodevelopment.countryservice.serializer.CountrySerializer;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "country")
@JsonDeserialize(using = CountryDeserializer.class)
@JsonSerialize(using = CountrySerializer.class)
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "top_level_domain")
    private String topLevelDomain;

    @Column(name = "population")
    private long population;

    @Column(name = "latlng")
    private String latlng;

    @Column(name = "languages")
    private String languages;

    @Column(name = "translations")
    private String translations;

    @Column(name = "regional_blocs")
    private String regionalBlocs;

    @Column(name = "flag")
    private String flag;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "country_currency",
            joinColumns = @JoinColumn(name = "country_id"),
            inverseJoinColumns = @JoinColumn(name = "currency_id"))
    private List<Currency> currencies;

    public Country() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopLevelDomain() {
        return topLevelDomain;
    }

    public void setTopLevelDomain(String topLevelDomain) {
        this.topLevelDomain = topLevelDomain;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public String getLatlng() {
        return latlng;
    }

    public void setLatlng(String latlng) {
        this.latlng = latlng;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getTranslations() {
        return translations;
    }

    public void setTranslations(String translations) {
        this.translations = translations;
    }

    public String getRegionalBlocs() {
        return regionalBlocs;
    }

    public void setRegionalBlocs(String regionalBlocs) {
        this.regionalBlocs = regionalBlocs;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", topLevelDomain='" + topLevelDomain + '\'' +
                ", population=" + population +
                ", latlng='" + latlng + '\'' +
                ", languages='" + languages + '\'' +
                ", translations='" + translations + '\'' +
                ", regionalBlocs='" + regionalBlocs + '\'' +
                ", flag='" + flag + '\'' +
                ", currencies=" + currencies.stream().map(Object::toString).collect(Collectors.joining(", ")) +
                '}';
    }
}
