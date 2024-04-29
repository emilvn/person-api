package dk.emilvn.personapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonResponseDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    private String fullName;
    private String gender;
    private double genderProbability;
    private int age;
    private double ageProbability;
    private String country;
    private double countryProbability;
}
