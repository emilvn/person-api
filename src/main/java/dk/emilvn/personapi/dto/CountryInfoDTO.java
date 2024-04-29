package dk.emilvn.personapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryInfoDTO {
    private String name;
    private Integer count;
    private List<CountryDTO> country;

    public CountryDTO getMostLikelyCountry(){
        if(country == null || country.isEmpty()){
            return null;
        }
        return country.get(0);
    }
}
