package seat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import seat.type.Type;

import static org.junit.jupiter.api.Assertions.*;

class SeatTest
{
    private static Seat seat;

    @BeforeAll
    public static void initialize()
    {
        seat = new Seat(Type.VIP, 1, 1);
    }

    @Test
    @DisplayName("Should be able to book a seat when the seat is free")
    public void shouldBeAbleToBookSeatWhenTheSeatIsFree()
    {
        assertTrue(seat.isFree());
        seat.book();
        assertFalse(seat.isFree());
    }

    @Test
    @DisplayName("Should not be able to book a seat when the seat isn't free")
    public void shouldNotBeAbleToBookSeatWhenTheSeatIsNotFree()
    {
        assertFalse(seat.isFree());
        seat.book();
        assertFalse(seat.isFree());
    }

    @Test
    @DisplayName("Should show seat details when the seat is created")
    public void shouldShowSeatDetailsWhenTheSeatIsCreated()
    {
        seat.show();
    }

    @Test
    @DisplayName("Should get type of the seat when the seat is created")
    public void shouldGetTypeOfTheSeatWhenTheSeatIsCreated()
    {
        Type type = seat.getType();
        assertNotEquals(type, null);
    }

    @Test
    @DisplayName("Should get row number of the seat when the seat is created")
    public void shouldGetRowNumberOfTheSeatWhenTheSeatIsCreated()
    {
        Integer rowNumber = seat.getRow();
        assertNotEquals(rowNumber, null);

    }

    @Test
    @DisplayName("Should get seat number  when the seat is created")
    public void shouldGeSeatNumberWhenTheSeatIsCreated()
    {
        Integer seatNumber = seat.getNumber();
        assertNotEquals(seatNumber,null);
    }
}