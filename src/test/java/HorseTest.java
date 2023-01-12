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

    @Test
    void whenHorseSpeedIsNegative() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Pegasus", -10, 50));

        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void whenHorseDistanceIsNegative() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Pegasus", 100, -50));

        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getName() {
        String expectedName = "Pegasus";
        String actualName = new Horse("Pegasus", 100, 100).getName();
        assertEquals(expectedName, actualName);
    }

    @Test
    void getSpeed(){
        double expectedSpeed = 50;
        double actualSpeed = new Horse("Pegasus", 50, 100).getSpeed();
        assertEquals(expectedSpeed, actualSpeed);
    }
}