package repertoire;

import movie.Movie;
import movie.collection.MovieCollection;
import org.junit.jupiter.api.*;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RepertoireTest
{
    private LocalDate date;
    private String repertoireFilePath;
    private Repertoire repertoire;

    @BeforeEach
    public void initialize()
    {
        MovieCollection.load();
        date = LocalDate.of(2000,01,01);
        repertoireFilePath = "files/repertoire/" + date + ".txt";
        repertoire = new Repertoire(date, LocalTime.of(6, 0), LocalTime.of(22, 0));
    }

    @Test
    @DisplayName("Should get present optional movie object when movie is starting at this hours")
    public void shouldGetPresentOptionalMovieObjectWhenMovieIsStartingAtThisHour()
    {
        Optional<Movie> movie = repertoire.getMovie(LocalTime.of(6,0));
        assertTrue(movie.isPresent());
    }

    @Test
    @DisplayName("Should get empty optional movie object when movie isn't starting at this hours")
    public void shouldGetEmptyOptionalMovieObjectWhenMovieIsNotStartingAtThisHour()
    {
        Optional<Movie> movie = repertoire.getMovie(LocalTime.of(6,1));
        assertTrue(movie.isEmpty());
    }

    @Test
    @DisplayName("Should show the entire repertoire without throwing any exceptions when repertoire is properly loaded")
    public void shouldShowTheEntireRepertoireWithoutThrowingAnyExceptionsWhenRepertoireIsProperlyLoaded()
    {
        repertoire.show();
    }

    @Test
    @DisplayName("Should return Repertoire object when repertoire is properly loaded")
    public void  shouldReturnRepertoireObjectWhenRepertoireIsProperlyLoaded()
    {
        Repertoire testRepertoire = repertoire.get();
        testRepertoire.show();
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