package dk.emilvn.personapi.controller;

import dk.emilvn.personapi.dto.PersonRequestDTO;
import dk.emilvn.personapi.dto.PersonResponseDTO;
import dk.emilvn.personapi.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/people")
public class PersonController {

    private final PersonService personService;
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<PersonResponseDTO> getPersonInfo(@RequestParam(required = false) String name, @RequestParam(required = false) String firstName, @RequestParam(required = false) String middleName, @RequestParam(required = false) String lastName) {
        if(name == null && firstName == null){
            throw new IllegalArgumentException("At least one of the parameters 'name' or 'firstName' must be provided");
        }
        if(name != null && !name.isBlank()){
            return ResponseEntity.ok(personService.getPersonInfo(PersonRequestDTO.create()
                    .fullName(name)
            ));
        }
        return ResponseEntity.ok(personService.getPersonInfo(PersonRequestDTO.create()
                .firstName(firstName)
                .middleName(middleName)
                .lastName(lastName)
        ));
    }

    @PostMapping
    public ResponseEntity<PersonResponseDTO> getPersonInfo(@RequestBody PersonRequestDTO personRequest) {
        return ResponseEntity.ok(personService.getPersonInfo(personRequest));
    }
}
