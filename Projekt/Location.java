package Projekt;

import java.util.Objects;

public class Location {
    private int id;
    private String name;
    private boolean in_door;

    public Location(int id, String name, boolean in_door) {
        this.id = id;
        this.name = name;
        this.in_door = in_door;
    }

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
        return Objects.equals(id, other.id) && Objects.equals(name, other.name)? true: Objects.equals(id, other.id);
    }

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
