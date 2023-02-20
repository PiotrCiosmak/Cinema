package repertoire;

import movie.collection.MovieCollection;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

class RepertoireTest
{
    @Test
    public void g()
    {
        MovieCollection.load();
        Repertoire r = new Repertoire(LocalTime.of(7, 0), LocalTime.of(23, 0));
        r.generateForTheWholeWeek();
    }
}