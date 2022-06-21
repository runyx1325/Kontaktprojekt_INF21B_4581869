package services;

import model.Location;
import model.Person;
import model.Visit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DataImportTest {

    private final Service service = new Service();
    @BeforeAll
    public void setup(){
        DataImport.dataImportFromFile(new File("D:\\Studium\\DHBW\\2. Theoriesemester\\Programmieren\\Projekt\\testsfile2022.txt"), service);
    }
    @Test
    void readPeopleTest() {
        List<Person> expected = List.of(new Person(1, ""), new Person(2, ""), new Person(3, ""), new Person(4, ""), new Person(5, ""), new Person(6, ""));
        assertEquals(expected, service.people);
    }

    @Test
    void readLocationTest() {
        List<Location> expected = List.of(new Location(1, "", true), new Location(2, "", true), new Location(3, "", false), new Location(4, "", false), new Location(5, "", true));
        assertEquals(expected, service.locations);
    }

    @Test
    void readVisitTest() {
        List<Visit> expected = List.of
                (
                new Visit(LocalDateTime.parse("2022-01-01T00:00"),LocalDateTime.parse("2026-01-01T11:59:03"),1,1),
                new Visit(LocalDateTime.parse("2022-01-01T00:00:18"),LocalDateTime.parse("2026-01-01T14:00:00"),2,1),
                new Visit(LocalDateTime.parse("2022-01-01T00:00"),LocalDateTime.parse("2026-01-01T05:00:00"),5,2),
                new Visit(LocalDateTime.parse("2022-01-01T04:05:44"),LocalDateTime.parse("2026-01-02T05:00:00"),3,2)
                );
        assertEquals(expected,service.visits);
    }
}