package movie;

import movie.genre.Genre;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovieTest
{
    private static Movie movie;

    @BeforeAll
    public static void initialize()
    {
        movie = new Movie("Test title", "Test description", LocalDate.now(), Genre.ACTION, BigDecimal.valueOf(9.99), 125L);
    }

    @Test
    @DisplayName("Should update price to 10.00")
    public void shouldUpdatePriceToTenPointZero()
    {
        movie.updatePrice(BigDecimal.valueOf(10.0));
        assertEquals(movie.getPrice(), BigDecimal.valueOf(10.0));
    }
}