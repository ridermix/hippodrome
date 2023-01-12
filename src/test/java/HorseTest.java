import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {
    @Test
    void whenHorseNameIsNull(){
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null,50,50));

        assertEquals("Name cannot be null.", exception.getMessage());
    }

}