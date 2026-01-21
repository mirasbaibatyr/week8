package Gym_system.models;

import Gym_system.exceptions.GymException;

public class Member {
    private int id;
    private String name;
    private int age;
    private boolean active;

    public Member(int id, String name, int age, boolean active) throws GymException {
        this.id = id;
        setName(name);
        setAge(age);
        this.active = active;
    }

    public int getId() { return id; }

    public void setName(String name) throws GymException {
        if (name == null || name.isEmpty()) throw new GymException("Name cannot be empty.");
        this.name = name;
    }

    public void setAge(int age) throws GymException {
        if (age < 14) throw new GymException("Member must be at least 14 years old.");
        this.age = age;
    }

    public String toString() {
        return "Member{id=" + id + ", name='" + name + "', age=" + age + ", active=" + active + "}";
    }
}
