package services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ServiceTest {
    private final Service service = new Service();
    @BeforeAll
    public void setup(){
        DataImport.dataImportFromFile(new File("D:\\Studium\\DHBW\\2. Theoriesemester\\Programmieren\\Projekt\\testsfile2022.txt"), service);
    }
    @Test
    void personSearch() {
        String expected = "Finanzminister, Gott, Kathy";
        String result = service.personSearch("t");
        assertEquals(expected,result);
    }

    @Test
    void locationSearch() {
        String expected = "Bundeskanzleramt";
        String result = service.locationSearch("ndes");
        assertEquals(expected,result);
    }

    @Test
    void showContactPersons() {
        String expected = "Amira";
        String result = service.showContactPersons(1);
        assertEquals(expected,result);
    }

    @Test
    void visitorOfLocation() {
        String expected = "Gott, Kathy";
        String result = service.visitorOfLocation(2, LocalDateTime.parse("2026-01-01T04:00:00"));
        assertEquals(expected,result);
    }
}