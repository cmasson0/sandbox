package base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;

import static base.Streams.Person;
import static base.Streams.Person.GENDER.*;
import static org.junit.jupiter.api.Assertions.*;

class StreamsTest {

    private Collection<Person> persons;

    @BeforeEach
    void setUp() {
        persons = List.of(new Person("john", MALE, 72.3 ),
                new Person("Mike", MALE, 81.6 ),
                new Person("Alex", MALE, 68.2 ),
                new Person("Aline", FEMALE, 64.7 ),
                new Person("Alice", FEMALE, 59.1 ),
                new Person("Margo", FEMALE, 74.3 ),
                new Person("Daria", FEMALE, 65.6 )
                );
    }

    @AfterEach
    void tearDown() {
        persons = null;
    }

    @Test
    void analyze() {
        final Map<Person.GENDER, DoubleSummaryStatistics> result = Streams.analyze(persons);
        assertEquals(2, result.keySet().size(), "Invalid number of genders found");
        assertEquals(3, result.get(MALE).getCount(), "Invalid number of Male found");
        assertEquals(4, result.get(FEMALE).getCount(), "Invalid number of Male found");
        System.out.println(result.toString());
    }

}