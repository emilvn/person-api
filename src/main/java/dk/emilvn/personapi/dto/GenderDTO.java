package dk.emilvn.personapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenderDTO {
    private String name;
    private Integer count;
    private String gender;
    private Integer probability;
}
