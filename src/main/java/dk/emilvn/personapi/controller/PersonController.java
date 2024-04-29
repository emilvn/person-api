package dk.emilvn.personapi.controller;

import dk.emilvn.personapi.dto.PersonRequestDTO;
import dk.emilvn.personapi.dto.PersonResponseDTO;
import dk.emilvn.personapi.service.AgeService;
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

    @PostMapping
    public ResponseEntity<PersonResponseDTO> getPersonInfo(@RequestBody PersonRequestDTO personRequest) {
        return ResponseEntity.ok(personService.getPersonInfo(personRequest));
    }
}
