import java.time.LocalDateTime;

public class Besuch {
    private LocalDateTime startToday;
    private LocalDateTime endToday;
    private String person_id;
    private String location_id;

    public Besuch(LocalDateTime startToday, LocalDateTime endToday, String person_id, String location_id) {
        this.startToday = startToday;
        this.endToday = endToday;
        this.person_id = person_id;
        this.location_id = location_id;
    }
}
