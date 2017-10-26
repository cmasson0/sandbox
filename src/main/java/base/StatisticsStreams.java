package base;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class StatisticsStreams {

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

    static class DoubleStatistics {
        private BigDecimal sum;
        private double min;
        private double max;
        private int count;

        DoubleStatistics() {
            this.sum = BigDecimal.ZERO;
            this.count = 0;
            this.min = 0;
            this.max = 0;
        }

        DoubleStatistics merge(DoubleStatistics other) {
            sum = sum.add(other.getSum());
            count += other.getCount();
            min = count == 1 ? sum.doubleValue() : Math.min(min, other.getMin());
            max = count == 1 ? sum.doubleValue() : Math.max(max, other.getMax());
            return this;
        }

        void accept(double d) {
            sum = sum.add(BigDecimal.valueOf(d));
            count++;
            min = count == 1 ? d : Math.min(min, d);
            max = count == 1 ? d : Math.max(max, d);
        }

        BigDecimal getSum() {
            return sum;
        }

        int getCount() {
            return count;
        }

        public double getMin() {
            return min;
        }

        public double getMax() {
            return max;
        }

        public double getAverage() {
            return count > 0 ? sum.doubleValue()/count : 0;
        }
    }

    static Map<Person.GENDER, DoubleStatistics> analyzeWithOwnImplementation(Collection<Person> persons) {
        return persons.stream().collect(Collectors.groupingBy(p -> p.gender, Collectors.mapping(
                p -> p.weight,
                Collector.of(DoubleStatistics::new, DoubleStatistics::accept, DoubleStatistics::merge)
        )));
    }
}
