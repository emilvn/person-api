package dk.emilvn.personapi.controller;

import dk.emilvn.personapi.dto.*;
import dk.emilvn.personapi.service.AgeService;
import dk.emilvn.personapi.service.CountryService;
import dk.emilvn.personapi.service.GenderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    AgeService ageService;
    @MockBean
    GenderService genderService;
    @MockBean
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
    void getPersonInfoRequestParamFullNameWithMiddleName() {
        webTestClient
                .get().uri("/api/people?name=Firstname Middlename Lastname")
                .exchange()
                .expectStatus().isOk()
                .expectBody(PersonResponseDTO.class)
                .value(response -> {
                    assertEquals("Firstname", response.getFirstName());
                    assertEquals("Middlename", response.getMiddleName());
                    assertEquals("Lastname", response.getLastName());
                    assertEquals(50, response.getAge());
                    assertEquals("male", response.getGender());
                    assertEquals(0.5, response.getGenderProbability());
                    assertEquals("DK", response.getCountry());
                    assertEquals(0.5, response.getCountryProbability());
                });
    }

    @Test
    void getPersonInfoRequestParamFullNameWithoutMiddleName() {
        webTestClient
                .get().uri("/api/people?name=Firstname Lastname")
                .exchange()
                .expectStatus().isOk()
                .expectBody(PersonResponseDTO.class)
                .value(response -> {
                    assertEquals("Firstname", response.getFirstName());
                    assertNull(response.getMiddleName());
                    assertEquals("Lastname", response.getLastName());
                    assertEquals(50, response.getAge());
                    assertEquals("male", response.getGender());
                    assertEquals(0.5, response.getGenderProbability());
                    assertEquals("DK", response.getCountry());
                    assertEquals(0.5, response.getCountryProbability());
                });
    }

    @Test
    void getPersonInfoRequestParamNameParts() {
        webTestClient
                .get().uri("/api/people?firstName=Firstname&lastName=Lastname")
                .exchange()
                .expectStatus().isOk()
                .expectBody(PersonResponseDTO.class)
                .value(response -> {
                    assertEquals("Firstname", response.getFirstName());
                    assertNull(response.getMiddleName());
                    assertEquals("Lastname", response.getLastName());
                    assertEquals(50, response.getAge());
                    assertEquals("male", response.getGender());
                    assertEquals(0.5, response.getGenderProbability());
                    assertEquals("DK", response.getCountry());
                    assertEquals(0.5, response.getCountryProbability());
                });
    }

    @Test
    void getPersonInfoRequestParamNamePartsWithoutMiddleName() {
        webTestClient
                .get().uri("/api/people?firstName=Firstname&lastName=Lastname")
                .exchange()
                .expectStatus().isOk()
                .expectBody(PersonResponseDTO.class)
                .value(response -> {
                    assertEquals("Firstname", response.getFirstName());
                    assertNull(response.getMiddleName());
                    assertEquals("Lastname", response.getLastName());
                    assertEquals(50, response.getAge());
                    assertEquals("male", response.getGender());
                    assertEquals(0.5, response.getGenderProbability());
                    assertEquals("DK", response.getCountry());
                    assertEquals(0.5, response.getCountryProbability());
                });
    }

    @Test
    void getPersonInfoRequestParamOnlyFirstName() {
        webTestClient
                .get().uri("/api/people?firstName=Firstname")
                .exchange()
                .expectStatus().isOk()
                .expectBody(PersonResponseDTO.class)
                .value(response -> {
                    assertEquals("Firstname", response.getFirstName());
                    assertNull(response.getMiddleName());
                    assertNull(response.getLastName());
                    assertEquals(50, response.getAge());
                    assertEquals("male", response.getGender());
                    assertEquals(0.5, response.getGenderProbability());
                    assertEquals("DK", response.getCountry());
                    assertEquals(0.5, response.getCountryProbability());
                });
    }

    @Test
    void getPersonInfoNoRequestParams(){
        webTestClient
                .get().uri("/api/people")
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void postPersonInfoCompleteRequest() {
        webTestClient
                .post().uri("/api/people")
                .header("Content-Type", "application/json")
                .bodyValue(
                        """
                        {
                            "firstName": "Firstname",
                            "middleName": "Middlename",
                            "lastName": "Lastname"
                        }
                        """
                )
                .exchange()
                .expectStatus().isOk()
                .expectBody(PersonResponseDTO.class)
                .value(response -> {
                    assertEquals("Firstname", response.getFirstName());
                    assertEquals("Middlename", response.getMiddleName());
                    assertEquals("Lastname", response.getLastName());
                    assertEquals(50, response.getAge());
                    assertEquals("male", response.getGender());
                    assertEquals(0.5, response.getGenderProbability());
                    assertEquals("DK", response.getCountry());
                    assertEquals(0.5, response.getCountryProbability());
                });
    }

    @Test
    void postPersonInfoWithOnlyFirstName() {
        webTestClient
                .post().uri("/api/people")
                .header("Content-Type", "application/json")
                .bodyValue(
                        """
                                {
                                    "firstName": "Firstname"
                                }
                                """
                )
                .exchange()
                .expectStatus().isOk()
                .expectBody(PersonResponseDTO.class)
                .value(response -> {
                    assertEquals("Firstname", response.getFirstName());
                    assertNull(response.getMiddleName());
                    assertNull(response.getLastName());
                    assertEquals(50, response.getAge());
                    assertEquals("male", response.getGender());
                    assertEquals(0.5, response.getGenderProbability());
                    assertEquals("DK", response.getCountry());
                    assertEquals(0.5, response.getCountryProbability());
                });
    }

    @Test
    void postPersonInfoNoFirstname(){
        webTestClient
                .post().uri("/api/people")
                .header("Content-Type", "application/json")
                .bodyValue(
                        """
                        {
                            "middleName": "Middlename",
                            "lastName": "Lastname"
                        }
                        """
                )
                .exchange()
                .expectStatus().isBadRequest();
    }
}