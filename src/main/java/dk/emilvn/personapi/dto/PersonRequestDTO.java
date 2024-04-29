package dk.emilvn.personapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequestDTO implements PersonDTO{
    private String firstName;
    private String middleName;
    private String lastName;

    public static PersonRequestDTO create(){
        return new PersonRequestDTO();
    }

    public PersonRequestDTO firstName(String firstName) {
        if(firstName == null){
            throw new IllegalArgumentException("First name cannot be null");
        }
        this.firstName = firstName;
        return this;
    }

    public PersonRequestDTO middleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public PersonRequestDTO lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PersonRequestDTO fullName(String fullName) {
        setFullName(fullName);
        return this;
    }
}
