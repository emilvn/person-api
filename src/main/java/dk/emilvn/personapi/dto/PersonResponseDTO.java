package dk.emilvn.personapi.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonResponseDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private double genderProbability;
    private int age;
    private String country;
    private double countryProbability;

    @JsonGetter
    public String getFullName() {
        if(getLastName() == null){
            return firstName;
        }
        if(getMiddleName() == null){
            return firstName + " " + lastName;
        }
        return firstName + " " + middleName + " " + lastName;
    }

    public static PersonResponseDTO create(){
        return new PersonResponseDTO();
    }

    public PersonResponseDTO firstName(String firstName) {
        if(firstName == null){
            throw new IllegalArgumentException("First name cannot be null");
        }
        this.firstName = firstName;
        return this;
    }

    public PersonResponseDTO middleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public PersonResponseDTO lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
