package dk.emilvn.personapi.service;

import dk.emilvn.personapi.dto.PersonRequestDTO;
import dk.emilvn.personapi.dto.PersonResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    private final List<PersonResponseDTO> cache = new ArrayList<>();
    private final AgeService ageService;
    private final GenderService genderService;
    private final CountryService countryService;
    public PersonService(AgeService ageService, GenderService genderService, CountryService countryService) {
        this.ageService = ageService;
        this.genderService = genderService;
        this.countryService = countryService;
    }

    public PersonResponseDTO getPersonInfo(PersonRequestDTO personRequest){
        if(!cache.isEmpty()){
            for (PersonResponseDTO person : cache) {
                if(person.getFirstName().equals(personRequest.getFirstName())){
                    System.out.println("Using cached person data");
                    return person
                            .firstName(personRequest.getFirstName())
                            .middleName(personRequest.getMiddleName())
                            .lastName(personRequest.getLastName());
                }
            }
        }
        System.out.println("Fetching new person data");
        var person = PersonResponseDTO
                .create()
                .firstName(personRequest.getFirstName())
                .middleName(personRequest.getMiddleName())
                .lastName(personRequest.getLastName());

        var ageData = ageService.getAgeData(personRequest.getFirstName());
        person.setAge(ageData.getAge());

        var genderData = genderService.getGenderData(personRequest.getFirstName());
        person.setGender(
                genderData.getGender()
        );
        person.setGenderProbability(
                genderData.getProbability()
        );

        var countryData = countryService.getCountryData(personRequest.getFirstName());
        person.setCountry(countryData
                .getMostLikelyCountry()
                .getCountry_id()
        );
        person.setCountryProbability(countryData
                .getMostLikelyCountry()
                .getProbability()
        );
        cache.add(person);
        return person;
    }
}
