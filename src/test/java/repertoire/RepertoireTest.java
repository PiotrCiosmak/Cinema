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
        date = LocalDate.now();
        repertoireFilePath = "files/repertoire/" + date + ".txt";
        repertoire = new Repertoire(date, LocalTime.of(6, 0), LocalTime.of(22, 0));
    }

    @Test
    @DisplayName("Should generate and save repertoire when repertoire isn't created for this date")
    public void shouldGenerateAndSaveRepertoireWhenRepertoireIsNotCreatedForThisDate()
    {
        MovieCollection.load();
        Map<LocalDateTime, Movie> dateAndTimeToMovie = repertoire.generate();
        assertFalse(dateAndTimeToMovie.isEmpty());
    }

    @Test
    @DisplayName("Should load repertoire when repertoire is created for this date")
    public void shouldLoadRepertoireWhenRepertoireIsCreatedForThisDate()
    {
        MovieCollection.load();
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