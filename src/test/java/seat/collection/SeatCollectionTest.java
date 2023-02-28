package seat.collection;

import org.junit.jupiter.api.*;
import seat.Seat;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class SeatCollectionTest
{
    private static SeatCollection seatCollection;

    @BeforeAll
    public static void initialize()
    {
        seatCollection = new SeatCollection();
    }

    @Test
    @DisplayName("Should show seats when seat collection is created")
    public void shouldShowSeatsWhenSeatCollectionIsCreated()
    {
        seatCollection.show();
    }

    @Test
    @DisplayName("Should get a SeatCollection object when object is created")
    public void shouldGetSeatCollectionObjectWhenObjectIsCreated()
    {
        SeatCollection testSeatCollection = seatCollection.get();
        testSeatCollection.show();
    }

    @Test
    @DisplayName("Should get present seat object when number of seats is zero and number of row is zero")
    public void shouldGetPresentSeatObjectWhenNumberOfSeatIsZeroAndNumberOfRowIsZero()
    {
        Optional<Seat> seat = seatCollection.getSeatByRowNumberAndSeatNumber(0, 0);
        assertTrue(seat.isPresent());
    }

    @RepeatedTest(25)
    @DisplayName("Should get present seat object when number of seat is zero and number of row is random")
    public void shouldGetPresentSeatObjectWhenNumberOfSeatIsZeroAndNumberOfRowIsRandom()
    {
        Optional<Seat> seat = seatCollection.getSeatByRowNumberAndSeatNumber(generateRandomRowNumber(), 0);
        assertTrue(seat.isPresent());
    }

    private Integer generateRandomRowNumber()
    {
        int numberOfRows = seatCollection.getRowNumberToTheNumberOfSeats().keySet().size();
        return ThreadLocalRandom.current().nextInt(numberOfRows);

    }

    @Test
    @DisplayName("Should get present seat object when number of seat is zero and number of row is last")
    public void shouldGetPresentSeatObjectWhenNumberOfSeatIsZeroAndNumberOfRowIsLast()
    {
        Optional<Seat> seat = seatCollection.getSeatByRowNumberAndSeatNumber(getLastRowNumber(), 0);
        assertTrue(seat.isPresent());
    }

    private Integer getLastRowNumber()
    {
        return seatCollection.getRowNumberToTheNumberOfSeats().keySet().size()-1;
    }

    @Test
    @DisplayName("Should get present seat object when number of seat is last and number of row is zero")
    public void shouldGetPresentSeatObjectWhenNumberOfSeatIsLastAndNumberOfRowIsZero()
    {

    }

    @RepeatedTest(10)
    @DisplayName("Should get present seat object when number of seat is last and number of row is random")
    public void shouldGetPresentSeatObjectWhenNumberOfSeatIsLastAndNumberOfRowIsRandom()
    {

    }


    @Test
    @DisplayName("Should get present seat object when number of seat is last and number of row is last")
    public void shouldGetPresentSeatObjectWhenNumberOfSeatIsLastAndNumberOfRowIsLast()
    {

    }

    @RepeatedTest(20)
    @DisplayName("Should get present seat object when number of seat is random and number of row is random")
    public void shouldGetPresentSeatObjectWhenNumberOfSeatIsRandomAndNumberOfRowIsRandom()
    {

    }

    @Test
    @DisplayName("Should get present seat object when number of seat is wrong and number of row is find")
    public void shouldGetPresentSeatObjectWhenNumberOfSeatIsWrongAndNumberOfRowIsFind()
    {

    }
    @Test
    @DisplayName("Should get present seat object when number of seat is find and number of row is wrong")
    public void shouldGetPresentSeatObjectWhenNumberOfSeatIsFindAndNumberOfRowIsWrong()
    {

    }

    @Test
    @DisplayName("Should get present seat object when number of seat is wrong and number of row is wrong")
    public void shouldGetPresentSeatObjectWhenNumberOfSeatIsWrongAndNumberOfRowIsWrong()
    {

    }

    /*

    private Integer generateRandomRowNumber()
    {
        int numberOfRows = seatCollection.getRowNumberToTheNumberOfSeats().keySet().size();
        return ThreadLocalRandom.current().nextInt(numberOfRows);
    }

    private Integer generateRandomSeatNumber(Integer rowNumber)
    {
        int numberOfSeats = seatCollection.getRowNumberToTheNumberOfSeats().get(rowNumber);
        return ThreadLocalRandom.current().nextInt(numberOfSeats);
    }*/
}