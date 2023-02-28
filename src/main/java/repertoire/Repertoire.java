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
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class Repertoire
{
    private Long numberOfMinutesAvailable;

    private LocalDateTime startTimeOfTheNextMovie;

    private final Path repertoireFilePath;

    private Map<LocalDateTime, Movie> dateAndTimeToMovie;

    public Repertoire(LocalDate date, LocalTime openingHours, LocalTime closingHours)
    {
        this.numberOfMinutesAvailable = Duration.between(openingHours, closingHours).toMinutes();
        this.startTimeOfTheNextMovie = date.atTime(openingHours);
        this.repertoireFilePath = Path.of("files/repertoire/" + date + ".txt");
        this.dateAndTimeToMovie = new HashMap<>();
        generate();
    }

    public void show()
    {
        System.out.println("\n------------------------------");
        System.out.println("Repertoire of the day " + getDate().toString());
        for (Map.Entry<LocalDateTime, Movie> entry : dateAndTimeToMovie.entrySet())
        {
            System.out.println(entry.getValue().getTitle() + " will be shown on " + entry.getKey());
        }
    }

    public Optional<Movie> getMovie(LocalTime startTime)
    {
        for (Map.Entry<LocalDateTime, Movie> entry : dateAndTimeToMovie.entrySet())
        {
            LocalDateTime dateTime = entry.getKey();
            Movie movie = entry.getValue();
            if (dateTime.toLocalTime().equals(startTime))
            {
                return Optional.of(movie);
            }
        }
        return Optional.empty();
    }

    public Repertoire get()
    {
        return this;
    }

    public LocalDate getDate()
    {
        return dateAndTimeToMovie.keySet().stream().findFirst().orElseThrow().toLocalDate();
    }

    private void generate()
    {
        if (repertoireAlreadyExists())
        {
            load();
        }
        else
        {
            addMovies();
            save();
        }
    }

    private boolean repertoireAlreadyExists()
    {
        return Files.exists(repertoireFilePath);
    }

    private void load()
    {
        dateAndTimeToMovie.clear();
        Stream<String> lines = getStreamOfLines(repertoireFilePath);
        lines.forEach(line -> {
            String[] parts = line.split("\\|");
            LocalDateTime dateTime = LocalDateTime.parse(parts[0], DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
            Optional<Movie> movie = MovieCollection.get(Long.parseLong(parts[1]));
            if (movie.isEmpty())
            {
                throw new NoSuchElementException();
            }
            dateAndTimeToMovie.put(dateTime, movie.get());
        });
        System.out.println("Repertoire has been loaded");
    }

    private Stream<String> getStreamOfLines(Path path)
    {
        Stream<String> lines = null;
        try
        {
            lines = Files.lines(path);
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
            System.exit(1);
        }
        return lines;
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

        if (minutesAreAvailable(movieDurationIncludingBreak))
        {
            reduceTheNumberOfMinutesAvailable(movieDurationIncludingBreak);
            dateAndTimeToMovie.put(startTimeOfTheNextMovie, movie);
            moveTheStartTimeOfTheNextMovie(movieDurationIncludingBreak);
            return true;
        }
        return false;
    }

    private Long getMovieDurationWithBreak(Movie movie)
    {
        Long breakDuration = generateBreakDurationBetweenMovies();
        return movie.getDuration() + breakDuration;
    }

    private Long generateBreakDurationBetweenMovies()
    {
        return ThreadLocalRandom.current().nextLong(15) + 15;
    }

    private boolean minutesAreAvailable(Long movieDurationIncludingBreak)
    {
        return numberOfMinutesAvailable >= movieDurationIncludingBreak;
    }

    private void reduceTheNumberOfMinutesAvailable(Long movieDurationIncludingBreak)
    {
        numberOfMinutesAvailable -= movieDurationIncludingBreak;
    }

    private void moveTheStartTimeOfTheNextMovie(Long movieDurationIncludingBreak)
    {
        startTimeOfTheNextMovie = startTimeOfTheNextMovie.plusMinutes(movieDurationIncludingBreak);
    }

    private void save()
    {
        try (PrintWriter moviesFileWriter = new PrintWriter(Files.newBufferedWriter(repertoireFilePath)))
        {
            dateAndTimeToMovie.entrySet().stream().map(entry -> entry.getKey() + "|" + MovieCollection.getId(entry.getValue()).orElseThrow()).forEach(moviesFileWriter::println);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("Repertoire has been saved");
    }
}
