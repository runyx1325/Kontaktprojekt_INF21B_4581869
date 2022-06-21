package model;

import java.time.LocalDateTime;

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
