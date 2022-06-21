package services;

import model.Location;
import model.Person;
import model.Visit;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Service {
    List<Person> people = new ArrayList<>();                                                                            //List of all persons
    List<Location> locations = new ArrayList<>();                                                                       //List of all locations
    List<Visit> visits = new ArrayList<>();                                                                             //List of all visits

    public void addPersonToList(Person person) {                                                                        //only add person to list if person_id is not already existing
        if (people.contains(person)) {
        } else {
            people.add(person);
        }
    }

    public void addLocationToList(Location location) {                                                                  //only add location to list if location_id is not already existing
        if (locations.contains(location)) {
        } else {
            locations.add(location);
        }
    }

    public void addVisitToList(Visit visit) {
        visits.add(visit);
    }

    public String personSearch(String search) {
        String result = "";
        for (Person person : people) {
            if (person.getName().toLowerCase().contains(search.toLowerCase())) {
                result += person.getName();
                result += ", ";
            }
        }
        result = result.substring(0, result.length() - 2);
        return result;
    }

    public String locationSearch(String search) {
        String result = "";
        for (Location location : locations) {
            if (location.getName().toLowerCase().contains(search.toLowerCase())) {
                result += location.getName() + ", ";
            }
        }
        result = result.substring(0, result.length() - 2);
        return result;
    }

    public String showContactPersons(int id) {
        //Durch die übergebene ID sollen alle Kontaktpersonen berechnet werden und ausgegeben werden
        String erg = "";
        List<LocalDateTime> starts = new ArrayList<>();
        List<LocalDateTime> ends = new ArrayList<>();
        List<Integer> locationIdList = new ArrayList<>();
        List<Integer> contactPersonsID = new ArrayList<>();
        List<String> result = new ArrayList<>();

        //Alle Orte an denen die ID war
        for (Visit visit : visits) {
            if (visit.getPerson_id() == id && locationIn_door(visit.getLocation_id())) {
                locationIdList.add(visit.getLocation_id());
                starts.add(visit.getStartToday());
                ends.add(visit.getEndToday());
            }
        }

        for (int i = 0; i < locationIdList.size(); i++) {
            for(Visit visit:visits){
                if(visit.getPerson_id() != id){                                                                                                   //Besuche der gesuchten model. Person ignorieren
                    if(visit.getLocation_id() == locationIdList.get(i)){                                                                          //Nur Besuche der aktuellen model. Location vergleichen
                        if(
                                (( (visit.getStartToday().isAfter(starts.get(i))  || visit.getStartToday().isEqual(starts.get(i)))                //Ist der Startwert größer gleich Start
                                && (visit.getStartToday().isBefore(ends.get(i)))  || visit.getStartToday().isEqual(ends.get(i)))                  //und kleiner gleich Ende
                                || ((visit.getEndToday().isAfter(starts.get(i))   || visit.getEndToday().isEqual(starts.get(i)))                  //ODER ist der Endwert größer gleich Start
                                && (visit.getEndToday().isBefore(ends.get(i))     || visit.getEndToday().isEqual(starts.get(i)))))                //und kleiner gleich Ende
                                || (visit.getStartToday().isBefore(starts.get(i)) && visit.getEndToday().isAfter(ends.get(i)))                    //ODER Startwert kleiner Start und Endwert größer Ende

                        ){                                                                                                                        //Überlappen sich die beiden Besuche
                            contactPersonsID.add(visit.getPerson_id());
                        }
                    }
                }
            }
        }

        for (Integer pID : contactPersonsID) {
            for (Person person : people) {
                if (pID == person.getId() && !result.contains(person.getName())) {
                    result.add(person.getName());
                }
            }
        }

        //Output
        Collections.sort(result);
        for (String a : result) {
            erg += a + ", ";
        }
        if(erg.length() > 2){
            erg = erg.substring(0, erg.length() - 2);
        }
        return erg;
    }

    private boolean locationIn_door(int location_id) {
        for (Location location : locations) {
            if (location.getId() == location_id) {
                return location.isIn_door();
            }
        }
        return false;
    }

    public String visitor(int location_id, LocalDateTime time){
        String result = "";
        boolean indoor = locationIn_door(location_id);
        List<Integer> visitorListId = new ArrayList<>();
        List<String> visitorListName = new ArrayList<>();

        for(Visit visit:visits){
            if(visit.getLocation_id() == location_id && !(visitorListId.contains(visit.getPerson_id()))){
                if((visit.getStartToday().isBefore(time) || visit.getStartToday().isEqual(time))
                    && visit.getEndToday().isAfter(time) || visit.getEndToday().isEqual(time)){
                    visitorListId.add(visit.getPerson_id());
                }
            }
        }

        if(indoor){
            for(Integer pId: visitorListId){
                result += showContactPersons(pId) + ", ";
                String[] parts;
                parts = result.split(", ");
                for (String part : parts) {
                    if (!(visitorListName.contains(part))) {
                        visitorListName.add(part);
                    }
                }
            }
        }

        for (Integer pID : visitorListId) {
            for (Person person : people) {
                if (pID == person.getId() && !visitorListName.contains(person.getName())) {
                    visitorListName.add(person.getName());
                }
            }
        }
        Collections.sort(visitorListName);
        result = "";


        for(String a:visitorListName){
            result += a + ", ";
        }
        if(result.length() > 2){
            result = result.substring(0, result.length() - 2);
        }
        return result;
    }
}