package dk.emilvn.personapi.service;

import dk.emilvn.personapi.dto.CountryInfoDTO;
import dk.emilvn.personapi.exception.HttpExceptionFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class CountryService {

    private final RestClient restClient = RestClient.create("https://api.nationalize.io");

    public CountryInfoDTO getCountryData(String name){
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
                .body(CountryInfoDTO.class);
    }
}
