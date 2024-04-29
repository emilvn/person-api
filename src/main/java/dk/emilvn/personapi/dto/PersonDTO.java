package dk.emilvn.personapi.dto;

import com.fasterxml.jackson.annotation.JsonGetter;

public interface PersonDTO {
    String getFirstName();
    void setFirstName(String firstName);
    String getMiddleName();
    void setMiddleName(String middleName);
    String getLastName();
    void setLastName(String lastName);
    @JsonGetter
    default String getFullName() {
        if(getLastName() == null){
            return getFirstName();
        }
        if(getMiddleName() == null){
            return getFirstName() + " " + getLastName();
        }
        return getFirstName() + " " + getMiddleName() + " " + getLastName();
    }

    default void setFullName(String fullName){
        if(fullName == null){
            return;
        }

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
    }
}
