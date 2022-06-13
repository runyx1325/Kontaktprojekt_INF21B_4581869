import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DataImport {
    public static void main(String[] args) {
        dataImportFromFile(new File("D:\\Studium\\DHBW\\2. Theoriesemester\\Programmieren\\Projekt\\contacts2021.db.txt"));

    }

    private static boolean dataImportFromFile(File f) {
        String result = "";
        int counter = 0;
        try (BufferedReader bf = new BufferedReader(new FileReader(f))) {
            String line = null;
            int counterline = 0;
            while ((line = bf.readLine()) != null) {
                counterline++;
                result += line + System.lineSeparator(); // System.getProperty("line.separator");
                if(line.contains("New_Entity: ")){
                    counter++;
                }
                switch(counter){
                    case 1:
                        counter++;
                        System.out.println("New E1");
                        break;
                    case 2:
                        //create Person
                        String id = "";
                        String name = "";

                        //iterate over every character
                        for(int i = 0; i < line.length(); i++){
                            //ignore " and ,
                            if(line.charAt(i) == '"' || line.charAt(i) == ','){
                                break;
                            //numbers are in the ASCII-Table between 47 and 58
                            }else if(line.charAt(i) > 47 &&  line.charAt(i) < 58){
                                id += line.charAt(i);
                            //every other character is a letter from the name of the person
                            }else {
                                name += line.charAt(i);
                            }
                        }
                        //create Object Person with Constructor if not already exist
                        new Person(id, name);
                        break;
                    case 3:
                        System.out.println("New E2");
                        counter++;
                        break;
                    case 4:
                        //create location
                        String loc_id = "";
                        String location_name = "";
                        String in_door = "";

                        int counterSymbol = 0;
                        for(int i = 0; i < line.length(); i++){
                            //Count the Symbols we don't want to read
                            if(line.charAt(i) == '"' || line.charAt(i) == ','){
                                counterSymbol++;
                            }

                            //Save all infos from line in variables
                            switch(counterSymbol){
                                case 1:
                                    loc_id += line.charAt(i);
                                    break;
                                case 4:
                                    location_name += line.charAt(i);
                                    break;
                                case 7:
                                    in_door += line.charAt(i);
                                    break;
                                default:
                                    //skip
                                    break;
                            }
                        }
                        //create Object Person with Constructor if not already exist
                        new Ort(loc_id, location_name, in_door);
                        break;
                    case 5:
                        counter++;
                        System.out.println("New E3");
                        break;
                    case 6:
                        //variables for Date & Time
                        int year = 2021;
                        int month = 5;
                        int day = 15;
                        int hours = 0;
                        int minutes = 0;
                        int seconds = 0;

                        LocalDate startDate = LocalDate.of(year, month, day);
                        LocalTime startTime = LocalTime.of(hours, minutes, seconds);

                        LocalDate endDate = LocalDate.of(year, month, day);
                        LocalTime endTime = LocalTime.of(hours, minutes, seconds);

                        LocalDateTime startToday = startDate.atTime(startTime);
                        LocalDateTime endToday = endDate.atTime(endTime);

                        String person_id = "";
                        String location_id = "";

                        //Line 380 hat 48 länge
                        //Line 207 hat Länge 53

                        //40 hier steht das zweite Komma
                        //43


                        counterSymbol = 0;
                        for(int i = 0; i < line.length(); i++){
                            if(line.charAt(i) == ',') {
                               counterSymbol = i;
                               break;
                            }
                        }
                        String htext = "";
                        if(counterSymbol < 21){
                            for(int i = 13; line.charAt(i) != '"'; i++){
                                htext += line.charAt(i);
                            }
                            try{
                                hours = Integer.parseInt(htext);
                            }
                            catch (NumberFormatException ex){
                                ex.printStackTrace();
                                hours = 0;
                            }
                            System.out.println(hours);
                        }



                        //new Besuch(startToday, endToday, person_id, location_id);
                        break;
                    default:
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
