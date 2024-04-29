package dk.emilvn.personapi.controller;

import dk.emilvn.personapi.dto.PersonResponseDTO;
import dk.emilvn.personapi.service.AgeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/people")
public class PersonController {

    private final AgeService ageService;
    public PersonController(AgeService ageService) {
        this.ageService = ageService;
    }

    @GetMapping
    public ResponseEntity<?> getPersonInfo(@RequestParam String name) {
        //TODO: Implement this
        return ResponseEntity.ok(ageService.getAgeData(name));
    }
}
