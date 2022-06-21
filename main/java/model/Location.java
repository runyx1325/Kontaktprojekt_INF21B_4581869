package model;

import java.util.Objects;

public class Location {
    private final int id;
    private final String name;
    private final boolean in_door;

    //Constructor
    public Location(int id, String name, boolean in_door) {
        this.id = id;
        this.name = name;
        this.in_door = in_door;
    }

    //new equals-method to check if list contains object with this id
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Location other = (Location) obj;
        return Objects.equals(id, other.id) && Objects.equals(name, other.name) || Objects.equals(id, other.id);
    }

    //Getter
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public boolean isIn_door() {
        return in_door;
    }
}
