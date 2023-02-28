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

    private Long duration;

    public Movie(String title, String description, LocalDate releaseDate, Genre genre, BigDecimal price, Long duration)
    {
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.price = price;
        this.duration = duration;
    }

    public BigDecimal updatePrice(BigDecimal price)
    {
        BigDecimal oldPrice = this.price;
        this.price = price;
        return oldPrice;
    }

    public void show()
    {
        System.out.println("\n*********************");
        System.out.println("Movie details");
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Release date: " + releaseDate.toString());
        System.out.println("Genre: " + genre.toString());
        System.out.println("Price: " + price.toString());
        System.out.println("Duration: " + duration);
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

    public Long getDuration()
    {
        return duration;
    }

    @Override
    public String toString()
    {
        return title + '|' + description + '|' + releaseDate.toString() + '|' + genre.toString() + '|' + price.toString() + '|' + duration;
    }
}
