package dk.emilvn.personapi.service;

import dk.emilvn.personapi.dto.GenderDTO;
import dk.emilvn.personapi.exceptionhandling.exception.HttpException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class GenderService {

    private final RestClient restClient = RestClient.create("https://api.genderize.io");

    public GenderDTO getGenderData(String name){
        return restClient.get()
                .uri("?name="+name)
                .retrieve()
                .onStatus(
                        status -> status.value() >= 400,
                        (request, response) -> {
                            throw HttpException
                                    .of(response
                                            .getStatusCode()
                                            .value(),
                                        response.getStatusText()
                                    );
                        }
                )
                .body(GenderDTO.class);
    }
}
