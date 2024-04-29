package dk.emilvn.personapi.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonResponseDTO implements PersonDTO{
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private double genderProbability;
    private int age;
    private String country;
    private double countryProbability;

    public static PersonResponseDTO create(){
        return new PersonResponseDTO();
    }

    public PersonResponseDTO ageData(AgeDTO ageData){
        this.age = ageData.getAge();
        return this;
    }

    public PersonResponseDTO genderData(GenderDTO genderData) {
        this.gender = genderData.getGender();
        this.genderProbability = genderData.getProbability();
        return this;
    }

    public PersonResponseDTO countryData(CountryInfoDTO countryData) {
        var country = countryData.getMostLikelyCountry();
        this.country = country.getCountry_id();
        this.countryProbability = country.getProbability();
        return this;
    }

    public PersonResponseDTO firstName(String firstName) {
        if(firstName == null){
            throw new IllegalArgumentException("First name cannot be null");
        }
        setFirstName(firstName);
        return this;
    }
    public PersonResponseDTO middleName(String middleName) {
        setMiddleName(middleName);
        return this;
    }
    public PersonResponseDTO lastName(String lastName) {
        setLastName(lastName);
        return this;
    }
    public PersonResponseDTO fullName(String fullName) {
        setFullName(fullName);
        return this;
    }

}
