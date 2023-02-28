package repertoire.collection;

import repertoire.Repertoire;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepertoireCollection
{
    private final LocalDate FIRST_DAY;
    private final LocalDate LAST_DAY;
    private LocalDate currentDay;

    private LocalTime openingHours;
    private LocalTime closingHours;

    private List<Repertoire> repertoires;

    public RepertoireCollection(LocalTime openingHours, LocalTime closingHours)
    {
        this.FIRST_DAY = LocalDate.now();
        this.LAST_DAY = FIRST_DAY.plusDays(7);
        this.currentDay = FIRST_DAY;

        this.openingHours = openingHours;
        this.closingHours = closingHours;
        this.repertoires = new ArrayList<>();
        generate();
    }

    public RepertoireCollection(LocalTime openingHours, LocalTime closingHours, LocalDate firstDay)
    {
        this.FIRST_DAY = firstDay;
        this.LAST_DAY = FIRST_DAY.plusDays(7);
        this.currentDay = FIRST_DAY;

        this.openingHours = openingHours;
        this.closingHours = closingHours;
        this.repertoires = new ArrayList<>();
        generate();
    }

    public void show()
    {
        for (Repertoire repertoire : repertoires)
        {
            repertoire.show();
        }
    }

    public Optional<Repertoire> getRepertoireOfTheDay(LocalDate date)
    {
        for (Repertoire repertoire : repertoires)
        {
            if (repertoire.getDate().equals(date))
            {
                return Optional.of(repertoire);
            }
        }
        return Optional.empty();
    }

    public RepertoireCollection get()
    {
        return this;
    }

    private void generate()
    {
        Repertoire repertoire;
        while (LAST_DAY.isAfter(currentDay))
        {
            repertoire = new Repertoire(currentDay, openingHours, closingHours);
            repertoires.add(repertoire.getRepertoire());
            currentDay = currentDay.plusDays(1);
        }
    }

}
