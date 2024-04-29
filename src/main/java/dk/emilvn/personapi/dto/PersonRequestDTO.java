package dk.emilvn.personapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequestDTO {
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
        if(fullName == null){
            throw new IllegalArgumentException("Full name cannot be null");
        }
        fullName = fullName.trim();
        int firstSpace = fullName.indexOf(" ");
        int lastSpace = fullName.lastIndexOf(" ");

        if(firstSpace == -1){
            setFirstName(fullName);
            setMiddleName(null);
            setLastName(null);
        }
        else if(firstSpace == lastSpace){
            setFirstName(fullName.substring(0, firstSpace));
            setMiddleName(null);
            setLastName(fullName.substring(lastSpace+1));
        }
        else {
            setFirstName(fullName.substring(0, firstSpace));
            setMiddleName(fullName.substring(firstSpace+1, lastSpace));
            setLastName(fullName.substring(lastSpace+1));
        }
        return this;
    }
}
