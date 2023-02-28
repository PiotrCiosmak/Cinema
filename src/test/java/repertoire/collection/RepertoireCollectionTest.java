package repertoire.collection;

import movie.collection.MovieCollection;
import org.junit.jupiter.api.*;
import repertoire.Repertoire;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class RepertoireCollectionTest
{
    private RepertoireCollection repertoireCollection;

    private static List<String> repertoireFilePaths;

    private final static LocalDate date = LocalDate.of(2000, 1, 1);

    @BeforeAll
    public static void initialize()
    {
        loadRepertoireFilePaths();
        MovieCollection.load();
    }

    private static void loadRepertoireFilePaths()
    {
        LocalDate currentDate = date;
        repertoireFilePaths = new ArrayList<>();
        for (int i = 0; i < 7; ++i)
        {
            repertoireFilePaths.add("files/repertoire/" + currentDate + ".txt");
            currentDate = currentDate.plusDays(1);
        }
    }

    @RepeatedTest(10)
    @DisplayName("Should generate repertoire for whole week")
    public void shouldGenerateRepertoireForWholeWeek()
    {
        List<LocalTime> workingHours = generateWorkingHours();
        repertoireCollection = new RepertoireCollection(workingHours.get(0), workingHours.get(1), date);
        repertoireCollection.show();
    }


    @Test
    @DisplayName("Should get present optional repertoire object when repertoire is prepared for that day")
    public void shouldGetPresentOptionalRepertoireObjectWhenRepertoireIsNPreparedForThatDay()
    {
        List<LocalTime> workingHours = generateWorkingHours();
        repertoireCollection = new RepertoireCollection(workingHours.get(0), workingHours.get(1), date);
        Optional<Repertoire> repertoire = repertoireCollection.getRepertoireOfTheDay(date);
        assertFalse(repertoire.isEmpty());
    }

    @Test
    @DisplayName("Should get empty optional repertoire object when repertoire isn't prepared for that day")
    public void shouldGetEmptyOptionalRepertoireObjectWhenRepertoireIsNotPreparedForThatDay()
    {
        List<LocalTime> workingHours = generateWorkingHours();
        repertoireCollection = new RepertoireCollection(workingHours.get(0), workingHours.get(1), date);
        Optional<Repertoire> repertoire = repertoireCollection.getRepertoireOfTheDay(LocalDate.MIN);
        assertTrue(repertoire.isEmpty());
    }

    @Test
    @DisplayName("Should show all repertoires without throwing any exceptions when repertoires are properly loaded")
    public void shouldShowTheEntireRepertoireWithoutThrowingAnyExceptionsWhenRepertoireIsProperlyLoaded()
    {
        List<LocalTime> workingHours = generateWorkingHours();
        repertoireCollection = new RepertoireCollection(workingHours.get(0), workingHours.get(1), date);

        repertoireCollection.show();
    }

    @Test
    @DisplayName("Should return RepertoireCollection object when repertoireCollection is properly loaded")
    public void shouldReturnRepertoireObjectWhenRepertoireIsProperlyLoaded()
    {
        List<LocalTime> workingHours = generateWorkingHours();
        repertoireCollection = new RepertoireCollection(workingHours.get(0), workingHours.get(1), date);

        RepertoireCollection testRepertoireCollection = repertoireCollection.get();
        testRepertoireCollection.show();
    }

    private static List<LocalTime> generateWorkingHours()
    {
        List<LocalTime> workingHours = new ArrayList<>(2);
        workingHours.add(generateOpeningOrClosingTime(6));
        workingHours.add(generateOpeningOrClosingTime(17));
        return workingHours;
    }

    static private LocalTime generateOpeningOrClosingTime(int earliestHour)
    {
        int openingHour = ThreadLocalRandom.current().nextInt(6) + earliestHour;
        int openingMinute = ThreadLocalRandom.current().nextInt(60);
        return LocalTime.of(openingHour, openingMinute);
    }

    @AfterEach
    void cleanUp()
    {
        for (String repertoireFilePath : repertoireFilePaths)
        {
            File repertoireFile = new File(repertoireFilePath);
            if (repertoireFile.exists())
            {
                repertoireFile.delete();
            }
        }
    }
}