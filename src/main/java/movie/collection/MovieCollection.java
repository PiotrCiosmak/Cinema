package movie.collection;

import movie.Movie;
import movie.genre.Genre;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class MovieCollection
{
    private static Long key = 0L;

    private static Map<Long, Movie> idToMovie = new HashMap<>();

    private static final Path moviesFilePath = Path.of("files/movies.txt");

    public static void load()
    {
        idToMovie.clear();
        key = 0L;
        Stream<String> lines = getStreamOfLines();
        lines.forEach(line ->
        {
            String[] parts = line.split("\\|");
            Movie movie = new Movie(parts[0], parts[1], LocalDate.parse(parts[2], DateTimeFormatter.ofPattern("yyyy-MM-dd")), Genre.valueOf(parts[3].toUpperCase()), new BigDecimal(parts[4]), Long.parseLong(parts[5]));
            add(movie);
        });
        System.out.println("Movies has been loaded");
    }

    private static Stream<String> getStreamOfLines()
    {
        Stream<String> lines = null;
        try
        {
            lines = Files.lines(moviesFilePath);
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
            System.exit(1);
        }
        return lines;
    }

    public static void save()
    {
        try (PrintWriter moviesFileWriter = new PrintWriter(Files.newBufferedWriter(moviesFilePath)))
        {
            idToMovie
                    .values()
                    .stream()
                    .map(Movie::toString)
                    .forEach(moviesFileWriter::println);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("Movies has been saved");
    }

    public static Optional<Long> getId(Movie movie)
    {
        return idToMovie
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(movie))
                .map(Map.Entry::getKey)
                .findFirst();
    }

    public static Optional<Movie> get(Long id)
    {
        return Optional.ofNullable(idToMovie.get(id));
    }

    public static Optional<Movie> get(String title)
    {
        return findByTitle(title);
    }

    public static void add(Movie movie)
    {
        idToMovie.put(key, movie);
        key++;
        System.out.println("Added new movie \"" + movie.getTitle() + "\".");
    }

    public static Movie getRandomMovie()
    {
        return idToMovie.get(getRandomMovieId());
    }

    private static Long getRandomMovieId()
    {
        return ThreadLocalRandom.current().nextLong(getNumberOfMovies());
    }

    public static int getNumberOfMovies()
    {
        return idToMovie.size();
    }

    public static boolean remove(Long id)
    {
        Optional<Movie> removedMovie = Optional.ofNullable(idToMovie.remove(id));
        if (removedMovie.isPresent())
        {
            System.out.println("Removed movie \"" + removedMovie.get().getTitle() + "\".");
            return true;
        }
        System.out.println("There is no movie with this id.");
        return false;
    }

    public static boolean remove(String title)
    {
        Optional<Movie> removedMovie = findByTitle(title);
        if (removedMovie.isPresent())
        {
            System.out.println("Removed movie \"" + removedMovie.get().getTitle() + "\".");
            return true;
        }
        System.out.println("There is no movie with that title.");
        return false;
    }

    private static Optional<Movie> findByTitle(String title)
    {
        return idToMovie
                .values()
                .stream()
                .filter(movie -> titlesAreTheSame(movie, title))
                .findFirst();
    }

    private static boolean titlesAreTheSame(Movie movie, String title)
    {
        String lowercaseTitle = title.toLowerCase();
        return movie.getTitle().toLowerCase().equals(lowercaseTitle);
    }
}
