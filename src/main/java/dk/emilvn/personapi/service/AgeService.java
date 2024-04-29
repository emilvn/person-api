package dk.emilvn.personapi.service;

import dk.emilvn.personapi.dto.AgeDTO;
import dk.emilvn.personapi.exception.HttpExceptionFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AgeService {

    private final RestClient restClient = RestClient.create("https://api.agify.io");

    public AgeDTO getAgeData(String name){
        return restClient.get()
                .uri("?name="+name)
                .retrieve()
                .onStatus(
                        status -> status.value() >= 400,
                        (request, response) -> {
                            throw HttpExceptionFactory
                                    .of(response
                                            .getStatusCode()
                                            .value(),
                                        response.getStatusText()
                                    );
                        }
                )
                .body(AgeDTO.class);
    }
}
