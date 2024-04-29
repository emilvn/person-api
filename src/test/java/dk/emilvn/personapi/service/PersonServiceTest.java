package dk.emilvn.personapi.service;

import dk.emilvn.personapi.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @InjectMocks
    PersonService personService;

    @Mock
    AgeService ageService;

    @Mock
    GenderService genderService;

    @Mock
    CountryService countryService;

    @BeforeEach
    void setUp() {
        var ageData = new AgeDTO("Test", 10, 50);
        var genderData = new GenderDTO("Test", 10,"male", 0.5);
        var countryData = new CountryInfoDTO("Test", 10, List.of(new CountryDTO("DK", 0.5)));

        when(ageService.getAgeData(anyString())).thenReturn(ageData);
        when(genderService.getGenderData(anyString())).thenReturn(genderData);
        when(countryService.getCountryData(anyString())).thenReturn(countryData);
    }

    @Test
    void getPersonInfo() {
        var personRequest = new PersonRequestDTO("Test", "Test", "Test");

        var personInfo = personService.getPersonInfo(personRequest);

        assertEquals(personInfo.getFirstName(), "Test");
        assertEquals(personInfo.getMiddleName(), "Test");
        assertEquals(personInfo.getLastName(), "Test");
        assertEquals(personInfo.getAge(), 50);
        assertEquals(personInfo.getGender(), "male");
        assertEquals(personInfo.getGenderProbability(), 0.5);
        assertEquals(personInfo.getCountry(), "DK");
        assertEquals(personInfo.getCountryProbability(), 0.5);
    }
}