package model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Visit {
    private final LocalDateTime startToday;
    private final LocalDateTime endToday;
    private final int person_id;
    private final int location_id;

    //Constructor
    public Visit(LocalDateTime startToday, LocalDateTime endToday, int person_id, int location_id) {
        this.startToday = startToday;
        this.endToday = endToday;
        this.person_id = person_id;
        this.location_id = location_id;
    }

    //new equals-method to check if list contains same object
    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        //normal equals is obj this object
        if(this == obj){
            return true;
        }
        if(this.getClass() != obj.getClass()){
            return false;
        }
        Visit visit = (Visit) obj;
        //Checks if all the Data is the same
        return Objects.equals(person_id, visit.person_id) && Objects.equals(location_id, visit.location_id) && Objects.equals(startToday, visit.startToday) && Objects.equals(endToday, visit.endToday);
    }

    //Getter
    public LocalDateTime getStartToday() {
        return startToday;
    }
    public LocalDateTime getEndToday() {
        return endToday;
    }
    public int getPerson_id() {
        return person_id;
    }
    public int getLocation_id() {
        return location_id;
    }
}
