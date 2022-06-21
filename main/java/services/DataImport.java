package services;

import model.Location;
import model.Person;
import model.Visit;

import java.io.*;
import java.time.LocalDateTime;

public class DataImport {
    public static void dataImportFromFile(File f, Service service) {
        {
            int entityCounter = 0, person_id, location_id;
            String person_name, location_name, line;
            boolean in_door;
            String[] parts;

            try (BufferedReader bf = new BufferedReader(new FileReader(f))) {
                line = bf.readLine();                                                                                   //read next line and safe as String
                while (line != null) {                                                                                  //until end is reached
                    if (line.contains("New_Entity: ") && line.contains("person_name")) {
                        entityCounter = 1;
                        line = bf.readLine();
                    } else if (line.contains("New_Entity: ") && line.contains("location_name")) {
                        entityCounter = 2;
                        line = bf.readLine();
                    } else if (line.contains("New_Entity: ") && line.contains("start_date")) {
                        entityCounter = 3;
                        line = bf.readLine();
                    } else {
                        while (line != null && !(line.contains("New_Entity: "))) {

                            switch (entityCounter) {
                                case 1 -> {
                                    line = line.replace("\"", "");                                                      //delete all " with replace
                                    parts = line.split(",");                                                            //split String to ,
                                    person_id = Integer.parseInt(parts[0]);
                                    person_name = parts[1];
                                    service.addPersonToList(new Person(person_id, person_name));                        //add new Person to List and duplicates check
                                    line = bf.readLine();
                                }
                                case 2 -> {
                                    line = line.replace("\"", "");                                                      //delete all " with replace
                                    parts = line.split(",");                                                            //split String to ,
                                    location_id = Integer.parseInt(parts[0]);
                                    location_name = parts[1];
                                    in_door = parts[2].equalsIgnoreCase("in_door");
                                    service.addLocationToList(new Location(location_id, location_name, in_door));       //add new location to List and duplicates check
                                    line = bf.readLine();
                                }
                                case 3 -> {
                                    line = line.replace("\"", "");                                                      //delete all " with replace
                                    parts = line.split(",");                                                            //split String to ,
                                    person_id = Integer.parseInt(parts[2]);
                                    location_id = Integer.parseInt(parts[3]);
                                    LocalDateTime startToday = LocalDateTime.parse(parts[0]);
                                    LocalDateTime endToday = LocalDateTime.parse(parts[1]);

                                    service.addVisitToList(new Visit(startToday, endToday, person_id, location_id));    //add new Vistit to List
                                    line = bf.readLine();
                                }
                            }
                        }
                        entityCounter++;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
