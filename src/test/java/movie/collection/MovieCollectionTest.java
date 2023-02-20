package movie.collection;

import movie.Movie;
import movie.genre.Genre;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MovieCollectionTest
{
    @BeforeAll
    public static void initialize()
    {
        MovieCollection.load();
    }

    @Test
    public void newMovieShouldBeAdded()
    {
        MovieCollection.add(new Movie("Test", "test", LocalDate.now(), Genre.ACTION, BigDecimal.ZERO, 0));
    }

    @Test
    @DisplayName("After adding movie their number should increment")
    public void afterAddingMovieTheirNumberShouldIncrement()
    {
        int numberOfMovieBeforeAddingNew = MovieCollection.getNumberOfMovies();
        MovieCollection.add(new Movie("Test", "test", LocalDate.now(), Genre.ACTION, BigDecimal.ZERO, 0));
        int numberOfMovieAfterAddingNew = MovieCollection.getNumberOfMovies();
        assertEquals(numberOfMovieAfterAddingNew, numberOfMovieBeforeAddingNew + 1);
    }

    @Test
    @DisplayName("Attempting to delete a movie by title that doesn't exist should return false")
    public void attemptingToDeleteMovieByTitleThatDoesNotExistShouldReturnFalse()
    {
        boolean movieHasBeenDeleted = MovieCollection.remove("Non-existent movie title");
        assertFalse(movieHasBeenDeleted);
    }

    @Test
    @DisplayName("Attempting to delete a movie by title that exist should return true")
    public void attemptingToDeleteMovieByTitleThatExistShouldReturnTrue()
    {
        Movie movie = MovieCollection.get(0L).get();
        boolean movieHasBeenDeleted = MovieCollection.remove(movie.getTitle());
        assertTrue(movieHasBeenDeleted);
    }

    @Test
    @DisplayName("Attempting to delete a movie by id that doesn't exist should return false")
    public void attemptingToDeleteMovieByIdThatDoesNotExistShouldReturnFalse()
    {
        boolean movieHasBeenDeleted = MovieCollection.remove(Long.MAX_VALUE);
        assertFalse(movieHasBeenDeleted);
    }

    @Test
    @DisplayName("Attempting to delete a movie by id that exist should return true")
    public void attemptingToDeleteMovieByIdThatExistShouldReturnTrue()
    {
        boolean movieHasBeenDeleted = MovieCollection.remove(1L);
        assertTrue(movieHasBeenDeleted);
    }

    @Test
    @DisplayName("Attempting to get movie by id that doesn't exists should return empty optional object")
    public void attemptingToGetMovieByIdThatDoesNotExistShouldReturnEmptyOptionalObject()
    {
        Optional<Movie> movie = MovieCollection.get(Long.MAX_VALUE);
        assertTrue(movie.isEmpty());
    }

    @Test
    @DisplayName("Attempting to get movie by id that  exists should return movie optional object")
    public void attemptingToGetMovieByIdThatExistShouldReturnMovieOptionalObject()
    {
        Optional<Movie> movie = MovieCollection.get(5L);
        assertTrue(movie.isPresent());
    }

    @Test
    @DisplayName("Attempting to get movie by title that doesn't exists should return empty optional object")
    public void attemptingToGetMovieByTitleThatDoesNotExistShouldReturnEmptyOptionalObject()
    {
        Optional<Movie> movie = MovieCollection.get("Non-existent movie title");
        assertTrue(movie.isEmpty());
    }

    @Test
    @DisplayName("Attempting to get movie by title that  exists should return movie optional object")
    public void attemptingToGetMovieByTitleThatExistShouldReturnMovieOptionalObject()
    {
        Optional<Movie> movie = MovieCollection.get("The Godfather");
        assertTrue(movie.isPresent());
    }

    @Test
    @DisplayName("Save method should work without throwing errors")
    public void saveMethodShouldWorkWithoutThrowingErrors()
    {
        MovieCollection.load();//We need to load movies from file because we don't want save any changes in file done in previous tests
        MovieCollection.save();
    }

    @Test
    @DisplayName("Should generate random movie")
    public void shouldGenerateRandomMovie()
    {
        Movie movie = MovieCollection.getRandomMovie();
        assertNotNull(movie);
    }
}