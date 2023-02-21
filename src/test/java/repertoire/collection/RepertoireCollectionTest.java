package repertoire.collection;

import movie.Movie;
import movie.collection.MovieCollection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import javax.swing.plaf.PanelUI;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class RepertoireCollectionTest
{
    private RepertoireCollection repertoireCollection;

    @BeforeAll
    public static void initialize()
    {
        MovieCollection.load();
    }

    @RepeatedTest(10)
    @DisplayName("Should generate repertoire for whole week")
    public void shouldGenerateRepertoireForWholeWeek()
    {
        List<LocalTime> workingHours = generateWorkingHours();
        repertoireCollection = new RepertoireCollection(workingHours.get(0), workingHours.get(1));
        List<Map<LocalDateTime, Movie>> repertoires = repertoireCollection.generate();
        assertFalse(repertoires.isEmpty());
    }

    static private List<LocalTime> generateWorkingHours()
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
}