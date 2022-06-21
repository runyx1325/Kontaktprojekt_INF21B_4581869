package services;

import model.Location;
import model.Person;
import model.Visit;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Service {
    List<Person> people = new ArrayList<>();                                                                            //List of all persons
    List<Location> locations = new ArrayList<>();                                                                       //List of all locations
    List<Visit> visits = new ArrayList<>();                                                                             //List of all visits

    public void addPersonToList(Person person) {                                                                        //only add person to list if person_id is not already existing

        if (!people.contains(person)) {
            people.add(person);
        }
    }

    public void addLocationToList(Location location) {                                                                  //only add location to list if location_id is not already existing
        if (!locations.contains(location)) {
            locations.add(location);
        }
    }

    public void addVisitToList(Visit visit) {                                                                           //add visit to List
        visits.add(visit);
    }

    public String personSearch(String search) {                                                                         //method can find person by name
        StringBuilder result = new StringBuilder();
        people.sort(Comparator.comparing(Person::getName));                                                             //sort list of people by name
        for (Person person : people) {                                                                                  //iterate over every person in List
            if (person.getName().toLowerCase().contains(search.toLowerCase())) {                                        //compare search with names of persons in lower case
                result.append(person.getName());                                                                        //adding person names to String
                result.append(", ");
            }
        }
        result = new StringBuilder(result.substring(0, result.length() - 2));                                           //delete last comma and Space
        return result.toString();
    }

    public String locationSearch(String search) {
        StringBuilder result = new StringBuilder();
        for (Location location : locations) {                                                                           //iterate over every person in List
            if (location.getName().toLowerCase().contains(search.toLowerCase())) {                                      //compare search with names of locations in lower case
                result.append(location.getName()).append(", ");                                                         //adding location names to String
            }
        }
        result = new StringBuilder(result.substring(0, result.length() - 2));                                           //delete last comma and Space
        return result.toString();
    }

    public String showContactPersons(int id) {                                                                          //find contact persons by ID
        StringBuilder erg = new StringBuilder();
        List<LocalDateTime> starts = new ArrayList<>();                                                                 //creating lists for start time, end time, location ids, person ids and result
        List<LocalDateTime> ends = new ArrayList<>();
        List<Integer> locationIdList = new ArrayList<>();
        List<Integer> contactPersonsID = new ArrayList<>();
        List<String> result = new ArrayList<>();

        for (Visit visit : visits) {
            if (visit.getPerson_id() == id && locationIn_door(visit.getLocation_id())) {
                locationIdList.add(visit.getLocation_id());                                                             //save visited location_ids of person_id in list
                starts.add(visit.getStartToday());                                                                      //save starting time of visits in list
                ends.add(visit.getEndToday());                                                                          //save ending time of visits in list
            }
        }

        //find all persons that are at the same time at the same location as person_id
        for (int i = 0; i < locationIdList.size(); i++) {
            for(Visit visit:visits){
                if(visit.getPerson_id() != id){
                    if(visit.getLocation_id() == locationIdList.get(i)){
                        if(
                                //Overlap the time periods?
                                (( (visit.getStartToday().isAfter(starts.get(i))  || visit.getStartToday().isEqual(starts.get(i)))
                                && (visit.getStartToday().isBefore(ends.get(i)))  || visit.getStartToday().isEqual(ends.get(i)))
                                || ((visit.getEndToday().isAfter(starts.get(i))   || visit.getEndToday().isEqual(starts.get(i)))
                                && (visit.getEndToday().isBefore(ends.get(i))     || visit.getEndToday().isEqual(starts.get(i)))))
                                || (visit.getStartToday().isBefore(starts.get(i)) && visit.getEndToday().isAfter(ends.get(i)))

                        ){
                            //add person_id of contact person to list
                            contactPersonsID.add(visit.getPerson_id());
                        }
                    }
                }
            }
        }

        //transfer contact id list to contact name list
        for (Integer pID : contactPersonsID) {
            for (Person person : people) {
                if (pID == person.getId() && !result.contains(person.getName())) {
                    result.add(person.getName());
                }
            }
        }

        Collections.sort(result);                                                                                       //sort list of names by alphabet

        for (String a : result) {                                                                                       //transfer list with names to a string with commas
            erg.append(a).append(", ");
        }
        if(erg.length() > 2){
            erg = new StringBuilder(erg.substring(0, erg.length() - 2));                                                //delete last 2 character (last comma and space)
        }
        return erg.toString();
    }

    private boolean locationIn_door(int location_id) {                                                                  //check if location is in_door
        for (Location location : locations) {
            if (location.getId() == location_id) {
                return location.isIn_door();
            }
        }
        return false;
    }

    public String visitorOfLocation(int location_id, LocalDateTime time){                                               //find all visitors of location at the same time
        StringBuilder result = new StringBuilder();
        boolean indoor = locationIn_door(location_id);
        List<Integer> visitorListId   = new ArrayList<>();
        List<String>  visitorListName = new ArrayList<>();

        for(Visit visit:visits){                                                                                        //find all visitors of location
            if(visit.getLocation_id() == location_id && !(visitorListId.contains(visit.getPerson_id()))){
                if((visit.getStartToday().isBefore(time) || visit.getStartToday().isEqual(time))
                    && visit.getEndToday().isAfter(time) || visit.getEndToday().isEqual(time)){
                    visitorListId.add(visit.getPerson_id());                                                            //add person ids of visitors to list
                }
            }
        }

        if(indoor){                                                                                                     //check if location is indoor
            for(Integer pId: visitorListId){
                result.append(showContactPersons(pId)).append(", ");
                String[] parts;
                parts = result.toString().split(", ");
                for (String part : parts) {
                    if (!(visitorListName.contains(part))) {
                        visitorListName.add(part);                                                                      //add contact person names to list if location is indoor
                    }
                }
            }
        }

        for (Integer pID : visitorListId) {
            for (Person person : people) {
                if (pID == person.getId() && !visitorListName.contains(person.getName())) {
                    visitorListName.add(person.getName());                                                              //add visitor names to list of names
                }
            }
        }

        Collections.sort(visitorListName);                                                                              //sort list of names by alphabet

        result = new StringBuilder();
        for(String a:visitorListName){                                                                                  //transfer list with names to a string with commas
            result.append(a).append(", ");
        }
            if(result.length() > 2){
                result = new StringBuilder(result.substring(0, result.length() - 2));                                   //delete last 2 character (last comma and space)
        }
        return result.toString();
    }
}