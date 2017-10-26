package base;

import java.util.Collection;
import java.util.DoubleSummaryStatistics;
import java.util.Map;
import java.util.stream.Collectors;

class Streams {

    static class Person {
        public enum GENDER {
            MALE,
            FEMALE
        }

        Person(String name, GENDER gender, double weight) {
            this.name = name;
            this.gender = gender;
            this.weight = weight;
        }

        final String name;
        final GENDER gender;
        final double weight;

    }

    static Map<Person.GENDER, DoubleSummaryStatistics> analyze(Collection<Person> persons) {
        return persons.stream()
                .collect(Collectors.groupingBy(p -> p.gender,
                        Collectors.summarizingDouble(p -> p.weight)));
    }


}
