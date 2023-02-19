package movie;

import movie.genre.Genre;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Movie
{
    private String title;

    private String description;

    private LocalDate releaseDate;

    private Genre genre;

    private BigDecimal price;

    public Movie(String title, String description, LocalDate releaseDate, Genre genre, BigDecimal price)
    {
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.price = price;
    }

    public String getTitle()
    {
        return title;
    }

    public String getDescription()
    {
        return description;
    }

    public LocalDate getReleaseDate()
    {
        return releaseDate;
    }

    public Genre getGenre()
    {
        return genre;
    }

    public BigDecimal getPrice()
    {
        return price;
    }
}
