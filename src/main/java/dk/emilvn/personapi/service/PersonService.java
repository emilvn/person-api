package dk.emilvn.personapi.service;

import dk.emilvn.personapi.dto.PersonRequestDTO;
import dk.emilvn.personapi.dto.PersonResponseDTO;
import dk.emilvn.personapi.util.PersonCache;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Getter
    private final PersonCache cache = new PersonCache();
    private final AgeService ageService;
    private final GenderService genderService;
    private final CountryService countryService;
    public PersonService(AgeService ageService, GenderService genderService, CountryService countryService) {
        this.ageService = ageService;
        this.genderService = genderService;
        this.countryService = countryService;
    }

    public PersonResponseDTO getPersonInfo(PersonRequestDTO personRequest){
        if(personRequest.getFirstName() == null){
            throw new IllegalArgumentException("First name cannot be null");
        }
        if(!cache.isEmpty()){
            var person = cache.get(personRequest);
            if(person != null){
                System.out.println("Using cached person data");
                return person.fullName(personRequest.getFullName());
            }
        }

        System.out.println("Fetching new person data");
        var ageData = ageService.getAgeData(personRequest.getFullName());
        var genderData = genderService.getGenderData(personRequest.getFullName());
        var countryData = countryService.getCountryData(personRequest.getFullName());

        var person = PersonResponseDTO
                .create()
                .fullName(personRequest.getFullName())
                .ageData(ageData)
                .genderData(genderData)
                .countryData(countryData);

        cache.add(person);
        return person;
    }

}
