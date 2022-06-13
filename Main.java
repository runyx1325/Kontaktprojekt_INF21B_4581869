import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        System.out.println(LocalDate.now());
        int years = 2021;
        int months = 5;
        int days = 15;
        LocalDate date = LocalDate.of(years , months, days);
        System.out.println(date);
        int hours = 15;
        int minutes = 0;
        int seconds = 0;
        LocalTime startTime = LocalTime.of(hours, minutes, seconds);
        System.out.println(startTime);
        hours = 16;
        minutes = 0;
        seconds = 0;
        LocalTime endTime = LocalTime.of(hours, minutes, seconds);
        System.out.println(endTime);
        System.out.println("---");
        LocalDateTime startToday = date.atTime(startTime);
        LocalDateTime endToday = date.atTime(endTime);

        //Data Import: "D:\Studium\DHBW\2. Theoriesemester\Programmieren\Projekt\contacts2021.db.txt"
    }
}
