import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {
    @Test
    void whenHorseNameIsNull() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 50, 50));

        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"\n", "\t", "\r"})
    void whenHorseNameIsBlank(String name) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(name, 50, 50));

        assertEquals("Name cannot be blank.", exception.getMessage());
    }
}