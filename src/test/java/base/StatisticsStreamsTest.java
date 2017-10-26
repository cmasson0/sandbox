package base;

import org.junit.jupiter.api.Test;

import java.util.*;

import static base.StatisticsStreams.Person;
import static base.StatisticsStreams.Person.GENDER.*;
import static org.junit.jupiter.api.Assertions.*;

class StatisticsStreamsTest {

    final private Collection<Person> persons = List.of(new Person("john", MALE, 72.3 ),
            new Person("Mike", MALE, 81.6 ),
            new Person("Alex", MALE, 68.2 ),
            new Person("Aline", FEMALE, 64.7 ),
            new Person("Alice", FEMALE, 59.1 ),
            new Person("Margo", FEMALE, 74.3 ),
            new Person("Daria", FEMALE, 65.6 )
    );

    @Test
    void analyze() {
        final Map<Person.GENDER, DoubleSummaryStatistics> reference = StatisticsStreams.analyze(persons);
        final Map<Person.GENDER, StatisticsStreams.DoubleStatistics> ownResult = StatisticsStreams.analyzeWithOwnImplementation(persons);

        assertIterableEquals(reference.keySet(),ownResult.keySet());
        Arrays.stream(Person.GENDER.values()).forEach(g -> {
            assertEquals(reference.get(g).getCount(), ownResult.get(g).getCount(), "incorrect count for gender " + g);
            assertEquals(reference.get(g).getSum(), ownResult.get(g).getSum().doubleValue(), "incorrect sum for gender " + g);
            assertEquals(reference.get(g).getMax(), ownResult.get(g).getMax(), "incorrect max for gender " + g);
            assertEquals(reference.get(g).getMin(), ownResult.get(g).getMin(), "incorrect min for gender " + g);
            assertEquals(reference.get(g).getAverage(), ownResult.get(g).getAverage(), "incorrect average for gender " + g);
        });

        System.out.println(reference.toString());
    }

}