import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

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
    void getSpeed() {
        double expectedSpeed = 50;
        double actualSpeed = new Horse("Pegasus", 50, 100).getSpeed();
        assertEquals(expectedSpeed, actualSpeed);
    }

    @Test
    void getDistanceWhichWasThirdInParameters() {
        double expectedDistance = 250;
        double actualDistance = new Horse("Pegasus", 50, 250).getDistance();
        assertEquals(expectedDistance, actualDistance);
    }

    @Test
    void getNullDistanceWhenUsedConstructorWithoutDistance() {
        double expectedDistance = 0;
        double actualDistance = (new Horse("Pegasus", 50)).getDistance();
        assertEquals(expectedDistance, actualDistance);
    }

    @Test
    void checkMoveCallsGetRandomDouble() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Pegasus", 50, 100);
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 0.2, 0.5, 0.9, 1.0})
    void checkMoveCalculateDistanceAccordingFormula(double value) {
        Horse horse = new Horse("Pegasus", 50, 100);
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(value);
            horse.move();
            double expected = 100 + 50 * value; // using formula distance + speed * getRandomDouble(0.2, 0.9)
            double actual = horse.getDistance();
            assertEquals(expected, actual);
        }
    }


}