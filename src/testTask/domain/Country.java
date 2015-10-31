package testTask.domain;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Component
@Entity
@Table(name="COUNTRIES")
public class Country implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String countryName;
    private String countryISOCode;
    @OneToMany(mappedBy = "country",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<City> cities = new HashSet<>();

    public Country() {
    }

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

    public Country(String countryName, String countryISOCode) {
        this.countryName = countryName;
        this.countryISOCode = countryISOCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryISOCode() {
        return countryISOCode;
    }

    public void setCountryISOCode(String countryISOCode) {
        this.countryISOCode = countryISOCode;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", countryName='" + countryName + '\'' +
                ", countryISOCode='" + countryISOCode + '\'' +
                '}';
    }
}
