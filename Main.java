import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main{
    public static void main(String[] args) {
        new DataImport();
        DataImport.dataImportFromFile(new File("D:\\Studium\\DHBW\\2. Theoriesemester\\Programmieren\\Projekt\\contacts2021.db.txt"));      //Home
        //DataImport.dataImportFromFile(new File("C:\\Studium\\ProgrammierenJava\\Projekt\\contacts2021.db.txt"));                            //Bosch
    }
}
