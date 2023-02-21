package repertoire;

import movie.Movie;
import movie.collection.MovieCollection;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Repertoire
{

    private Long numberOfAvailableMinutes;

    private LocalDateTime startTimeOfTheNextMovie;

    private LocalDate repertoireValidityDate;

    private Path repertoireDayPath;

    private Map<LocalDateTime, Movie> dateAndTimeToMovie;

    public Repertoire(LocalDate date, LocalTime openingHours, LocalTime closingHours)
    {
        numberOfAvailableMinutes = Duration.between(openingHours, closingHours).toMinutes();
        startTimeOfTheNextMovie = date.atTime(openingHours);
        repertoireValidityDate = date;
        repertoireDayPath = Path.of("files/repertoire/" + date + ".txt");
        this.dateAndTimeToMovie = new HashMap<>();
    }

    public void generate()
    {
        if (repertoireAlreadyExists())
        {
            //load();
        }
        else
        {
            addMovies();
           // save();
        }
    }


    private boolean repertoireAlreadyExists()
    {
        return Files.exists(repertoireDayPath);
    }

    private void addMovies()
    {
        boolean movieHasBeenAdded;
        do
        {
            movieHasBeenAdded = addOneMovie();
        } while (movieHasBeenAdded);

    }


    private boolean addOneMovie()
    {
        Movie movie = MovieCollection.getRandomMovie();
        Long movieDurationIncludingBreak = getMovieDurationWithBreak(movie);

        if (minutesAreAvailable(movieDurationIncludingBreak) == true)
        {
            reduceTheNumberOfMinutesAvailable(movieDurationIncludingBreak);
            moveTheStartTimeOfTheNextMovie(movieDurationIncludingBreak);
            dateAndTimeToMovie.put(startTimeOfTheNextMovie, movie);
            return true;
        }
        else
        {
            return false;
        }


    }

    private boolean minutesAreAvailable(Long movieDurationIncludingBreak)
    {
        if (numberOfAvailableMinutes > 0)
        {
            return true;
        }
        return false;
    }

    private Long getMovieDurationWithBreak(Movie movie)
    {
        Long breakDuration = generateBreakDurationBetweenMovies();
        Long movieDurationWithBreak = movie.getDuration() + breakDuration;
        return movieDurationWithBreak;
    }

    private Long generateBreakDurationBetweenMovies()
    {
        return ThreadLocalRandom.current().nextLong(15) + 15;
    }

    private void reduceTheNumberOfMinutesAvailable(Long movieDurationIncludingBreak)
    {
        numberOfAvailableMinutes -= movieDurationIncludingBreak;
    }

    private void moveTheStartTimeOfTheNextMovie(Long movieDurationIncludingBreak)
    {
        startTimeOfTheNextMovie.plusMinutes(movieDurationIncludingBreak);
    }


   /* private void save()
    {
        try (PrintWriter repertoireFileWriter = new PrintWriter(Files.newBufferedWriter(repertoireDayPath)))
        {
            dateAndTimeToMovie
                    .entrySet()
                    .stream()
                    .filter(entry -> entry.getKey().toLocalDate().equals(possibleDateOfTheNextMovie.toLocalDate()))
                    .map(entry -> entry.getKey() + "|" + MovieCollection.getId(entry.getValue()).get())
                    .forEach(repertoireFileWriter::println);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("Movies has been saved");
    }

    private void load()
    {

    }*/
}
