import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println("\t Домашнее задание по теме: " + "\"Stream API. Потоки, повторные вызовы, основные методы.\"");
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        Stream<Person> stream1 = persons.stream();
        Stream<Person> stream2 = persons.stream();
        Stream<Person> stream3 = persons.stream();
        System.out.println("Количество несовершеннолетних: " + stream1.
                filter(person -> person.getAge() < 18).count());
        List<String> recruit = (List<String>) stream2.
                filter(person -> (person.getAge() >= 18) && (person.getAge() <= 27)).
                filter(person -> person.getSex() == Sex.MAN).
                map(person -> person.getFamily()).
                collect(Collectors.toList());
        System.out.println("Количество призывников: " + recruit.size());
        List<String> labourPeople = (List<String>) stream3.
                filter(person -> person.getEducation() == Education.HIGHER).
                filter(person -> (person.getSex() == Sex.MAN) && (person.getAge() >= 18) && (person.getAge() <= 65) || (person.getSex() == Sex.WOMAN) && (person.getAge() >= 18) && (person.getAge() <= 60)).
                map(person -> person.getFamily()).
                sorted(Comparator.comparing(String::toLowerCase)).collect(Collectors.toList());
        System.out.println("Количество потенциально работоспособных людей с высшим образованием: " + labourPeople.size());
        for (int i = 0; i < 5; i++) System.out.println(labourPeople.get(i));
        for (int i = labourPeople.size()/2-5; i < labourPeople.size()/2; i++) System.out.println(labourPeople.get(i));
        for (int i = labourPeople.size()-5; i < labourPeople.size(); i++) System.out.println(labourPeople.get(i));

    }
}