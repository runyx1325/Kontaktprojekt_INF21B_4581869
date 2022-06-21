package model;

import java.util.Objects;

public class Person {
    private final int id;
    private final String name;

    //Constructor
    public Person(int id, String name) {
        this.id = id;
        this.name = name;
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
        Person other = (Person) obj;
        return Objects.equals(id, other.id) && Objects.equals(name, other.name) || Objects.equals(id, other.id);
    }

    //Getter
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
