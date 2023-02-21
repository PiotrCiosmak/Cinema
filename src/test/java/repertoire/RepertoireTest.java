package repertoire;

import movie.Movie;
import movie.collection.MovieCollection;
import org.junit.jupiter.api.*;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;

class RepertoireTest
{
    private LocalDate date;
    private String repertoireFilePath;
    private Repertoire repertoire;

    @BeforeEach
    public void initialize()
    {
        date = LocalDate.of(2000,01,01);
        repertoireFilePath = "files/repertoire/" + date + ".txt";
        repertoire = new Repertoire(date, LocalTime.of(6, 0), LocalTime.of(22, 0));
        MovieCollection.load();
    }

    @Test
    @DisplayName("Should generate and save repertoire when repertoire isn't created for this date")
    public void shouldGenerateAndSaveRepertoireWhenRepertoireIsNotCreatedForThisDate()
    {
        Map<LocalDateTime, Movie> dateAndTimeToMovie = repertoire.generate();
        assertFalse(dateAndTimeToMovie.isEmpty());
    }

    @Test
    @DisplayName("Should load repertoire when repertoire is created for this date")
    public void shouldLoadRepertoireWhenRepertoireIsCreatedForThisDate()
    {
        Map<LocalDateTime, Movie> dateAndTimeToMovie = repertoire.generate();
        assertFalse(dateAndTimeToMovie.isEmpty());

        dateAndTimeToMovie.clear();

        dateAndTimeToMovie = repertoire.generate();
        assertFalse(dateAndTimeToMovie.isEmpty());

    }

    @AfterEach
    void cleanUp()
    {
        File repertoireFile = new File(repertoireFilePath);
        if (repertoireFile.exists())
        {
            repertoireFile.delete();
        }
    }
}