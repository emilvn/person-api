package dk.emilvn.personapi.util;

import dk.emilvn.personapi.dto.PersonResponseDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonCacheTest {

    @Test
    void add() {
        var cache = new PersonCache(3);
        var p1 = PersonResponseDTO.create().fullName("Person One");
        var p2 = PersonResponseDTO.create().fullName("Person Two");
        var p3 = PersonResponseDTO.create().fullName("Person Three");
        var p4 = PersonResponseDTO.create().fullName("Person Four");

        cache.add(p1);
        cache.add(p2);
        cache.add(p3);
        cache.add(p4);

        assertEquals(3, cache.size());
        assertEquals("Person Two", cache.get(0).getFullName());
        assertEquals("Person Three", cache.get(1).getFullName());
        assertEquals("Person Four", cache.get(2).getFullName());
    }
}