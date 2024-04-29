package dk.emilvn.personapi.util;

import dk.emilvn.personapi.dto.PersonResponseDTO;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class PersonCache{
    private final List<PersonResponseDTO> cache = new ArrayList<>();
    private int MAX_SIZE = 100;

    public PersonCache(int maxSize){
        this.MAX_SIZE = maxSize;
    }

    public PersonResponseDTO get(String firstName){
        for (PersonResponseDTO person : cache) {
            if(person.getFirstName().equals(firstName)){
                return person;
            }
        }
        return null;
    }

    public PersonResponseDTO get(int index){
        return cache.get(index);
    }

    public void add(PersonResponseDTO person){
        cache.add(person);
        if(cache.size() > MAX_SIZE){
            cache.remove(0);
        }
    }

    public boolean isEmpty(){
        return cache.isEmpty();
    }

    public int size(){
        return cache.size();
    }
}
