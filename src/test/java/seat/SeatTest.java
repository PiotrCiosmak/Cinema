package seat;

import movie.Movie;
import movie.genre.Genre;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import seat.type.Type;

import java.math.BigDecimal;
import java.time.LocalDate;

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
}