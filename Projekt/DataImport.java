package Projekt;

import java.io.*;
import java.time.LocalDateTime;

public class DataImport {
    public static boolean dataImportFromFile(File f, Service service) {
        {
            int entityCounter = 0;
            String person_name, location_name, start_date, end_date;
            boolean in_door;
            int person_id, location_id;
            String[] parts;
            int year, month, day, hours, minutes, seconds;

            try (BufferedReader bf = new BufferedReader(new FileReader(f))) {
                String line = null;
                line = bf.readLine();
                while (line != null) {
                    if (line.contains("New_Entity: ") && line.contains("person_name")) {
                        //Personen
                        entityCounter = 1;
                        line = bf.readLine();
                    } else if (line.contains("New_Entity: ") && line.contains("location_name")) {
                        //Orte
                        entityCounter = 2;
                        line = bf.readLine();
                    } else if (line.contains("New_Entity: ") && line.contains("start_date")) {
                        //Besuche
                        entityCounter = 3;
                        line = bf.readLine();
                    } else {
                        while (line != null && !(line.contains("New_Entity: "))) {

                            switch (entityCounter) {
                                case 1:
                                    line = line.replace("\"", "");                       //replace all "
                                    parts = line.split(",");                            //split string to ,
                                    person_id = Integer.parseInt(parts[0]);
                                    person_name = parts[1];
                                    service.addPersonToList(new Person(person_id, person_name));

                                    line = bf.readLine();
                                    break;
                                case 2:
                                    line = line.replace("\"", "");                       //replace all "
                                    parts = line.split(",");                            //split string to ,
                                    location_id = Integer.parseInt(parts[0]);
                                    location_name = parts[1];
                                    in_door = parts[2].equalsIgnoreCase("in_door");

                                    service.addLocationToList(new Location(location_id, location_name, in_door));
                                    line = bf.readLine();
                                    break;
                                case 3:
                                    line = line.replace("\"", "");                       //replace all "
                                    parts = line.split(",");                            //split string to ,

                                    person_id = Integer.parseInt(parts[2]);
                                    location_id = Integer.parseInt(parts[3]);
                                    LocalDateTime startToday = LocalDateTime.parse(parts[0]);
                                    LocalDateTime endToday = LocalDateTime.parse(parts[1]);

                                    // Vergleiche Zeiten mit: startToday.isBefore...
                                    service.addVisitToList(new Visit(startToday, endToday, person_id, location_id));
                                    line = bf.readLine();
                                    break;
                            }
                        }
                        entityCounter++;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
    }
}
