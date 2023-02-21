package repertoire.collection;

import movie.Movie;
import repertoire.Repertoire;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RepertoireCollection
{
    private final LocalDate FIRST_DAY;
    private final LocalDate LAST_DAY;
    private LocalDate currentDay;

    private LocalTime openingHours;
    private LocalTime closingHours;

    private List<Map<LocalDateTime, Movie>> repertoires;

    public RepertoireCollection(LocalTime openingHours, LocalTime closingHours)
    {
        this.FIRST_DAY = LocalDate.now();
        this.LAST_DAY = FIRST_DAY.plusDays(7);
        this.currentDay = FIRST_DAY;

        this.openingHours = openingHours;
        this.closingHours = closingHours;
        this.repertoires = new ArrayList<>();
    }

    public RepertoireCollection(LocalTime openingHours, LocalTime closingHours, LocalDate firstDay)
    {
        this.FIRST_DAY = firstDay;
        this.LAST_DAY = FIRST_DAY.plusDays(7);
        this.currentDay = FIRST_DAY;

        this.openingHours = openingHours;
        this.closingHours = closingHours;
        this.repertoires = new ArrayList<>();
    }

    public List<Map<LocalDateTime, Movie>> generate()
    {
        Repertoire repertoire;
        while (LAST_DAY.isAfter(currentDay))
        {
            repertoire = new Repertoire(currentDay, openingHours, closingHours);
            repertoires.add(repertoire.generate());
            currentDay = currentDay.plusDays(1);
        }
        return repertoires;
    }
}
