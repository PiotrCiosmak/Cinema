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
    private final LocalDateTime FIRST_DAY;
    private final LocalDateTime LAST_DAY;
    private final Long DURATION;

    private Long durationOfAllMovieWithBreaksThroughoutTheDay;

    private LocalDateTime possibleDateOfTheNextMovie;


    private Path repertoireDayPath;
    private Map<LocalDateTime, Movie> dateAndTimeToMovie;

    public Repertoire(LocalTime openingHours, LocalTime closingHours)
    {
        FIRST_DAY = LocalDate.now().atTime(openingHours);
        LAST_DAY = FIRST_DAY.plusDays(6).withHour(closingHours.getHour()).withMinute(closingHours.getMinute());
        DURATION = Duration.between(openingHours, closingHours).toMinutes();
        durationOfAllMovieWithBreaksThroughoutTheDay = 0L;

        possibleDateOfTheNextMovie = FIRST_DAY;

        this.dateAndTimeToMovie = new HashMap<>();
        repertoireDayPath = Path.of("files/repertoire/" + FIRST_DAY.toLocalDate() + ".txt");
    }

    public void generateForTheWholeWeek()
    {
        while (repertoireShouldBeCreated())
        {
            setRepertoireDayPath();
            if (!repertoireForThisDateIsCreated())
            {
                generateForOneDay();
                save();
            }
            else
            {
                load();
            }
            setTheDateOfTheNextPossibleMovieToTheNextDayAtTheOpeningTime();
            resetDurationOfAllMovieWithBreaksThroughoutTheDay();
        }
    }

    private boolean repertoireShouldBeCreated()
    {
        return possibleDateOfTheNextMovie.isBefore(LAST_DAY);
    }

    private void setRepertoireDayPath()
    {
        repertoireDayPath = Path.of("files/repertoire/" + possibleDateOfTheNextMovie.toLocalDate() + ".txt");
    }


    private boolean repertoireForThisDateIsCreated()
    {
        return Files.exists(repertoireDayPath);
    }

    private void generateForOneDay()
    {
        while (thereAreStillMinutesLeft())
        {
            generateOneMovie();
        }
    }

    private boolean thereAreStillMinutesLeft()
    {
        return durationOfAllMovieWithBreaksThroughoutTheDay < DURATION;
    }

    private void generateOneMovie()
    {
        Movie movie = MovieCollection.getRandomMovie();
        Long movieDurationWithBreak = getMovieDurationWithBreak(movie);//zmienic nazwe

        //to do osobnej funkcji
        if (durationOfAllMovieWithBreaksThroughoutTheDay > DURATION)
        {
            return;
        }
        increaseDurationCOS(movieDurationWithBreak);//zmienic nazwe


        moveTheDateOfTheNextPossibleMovie(movieDurationWithBreak);

        dateAndTimeToMovie.put(possibleDateOfTheNextMovie, movie);

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

    private void moveTheDateOfTheNextPossibleMovie(Long movieDurationWithBreak)
    {
        possibleDateOfTheNextMovie = possibleDateOfTheNextMovie.plusMinutes(movieDurationWithBreak);
    }

    private void increaseDurationCOS(Long movieDurationWithBreak)
    {
        durationOfAllMovieWithBreaksThroughoutTheDay += movieDurationWithBreak;
    }

    private void save()
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

    }

    private void setTheDateOfTheNextPossibleMovieToTheNextDayAtTheOpeningTime()
    {
        possibleDateOfTheNextMovie = possibleDateOfTheNextMovie.plusDays(1).withHour(FIRST_DAY.getHour()).withMinute(FIRST_DAY.getMinute());
    }

    private void resetDurationOfAllMovieWithBreaksThroughoutTheDay()
    {
        durationOfAllMovieWithBreaksThroughoutTheDay = 0L;
    }

}
