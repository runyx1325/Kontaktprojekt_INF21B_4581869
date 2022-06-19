package Projekt;

import java.time.LocalDateTime;

public class Visit {
    private LocalDateTime startToday;
    private LocalDateTime endToday;
    private int person_id;
    private int location_id;

    public Visit(LocalDateTime startToday, LocalDateTime endToday, int person_id, int location_id) {
        this.startToday = startToday;
        this.endToday = endToday;
        this.person_id = person_id;
        this.location_id = location_id;
    }

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
