import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    void whenHorseListIsNull() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null));

        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void whenHorseListIsEmpty() {
        List<Horse> horses = new ArrayList<>();
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(horses));

        assertEquals("Horses cannot be empty.", exception.getMessage());
    }
}