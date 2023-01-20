import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


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

    @Test
    void checkHorsesListInMethodGet() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Pegasus" + i, i + 1, i + 1));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        List<Horse> expected = horses;
        List<Horse> actual = hippodrome.getHorses();
        assertEquals(expected, actual);

    }

    @Test
    void checkMoveCallsForAllHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

    @Test
    void checkGetWinnerReturnHorseWithMaxDistance() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            horses.add(new Horse("Pegasus" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        Horse expected = horses.get(horses.size() - 1);
        Horse actual = hippodrome.getWinner();
        assertEquals(expected, actual);
    }
}