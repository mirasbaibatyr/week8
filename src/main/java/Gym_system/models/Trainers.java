package Gym_system.models;

import Gym_system.exceptions.GymException;

public class Trainers {
    private int id;
    private String name;
    private String specialization;
    private int experienceYears;

    public Trainers(int id, String name, String specialization, int experienceYears) throws GymException {
        this.id = id;
        setName(name);
        this.specialization = specialization;
        setExperienceYears(experienceYears);
    }

    public int getId() { return id; }
    public String getName() { return name; }

    public void setName(String name) throws GymException {
        if(name == null || name.isEmpty()) throw new GymException("Name required.");
        this.name = name;
    }

    public void setExperienceYears(int experienceYears) throws GymException {
        if(experienceYears < 0) throw new GymException("Experience cannot be negative.");
        this.experienceYears = experienceYears;
    }

    public String toString() {
        return "Trainer{id=" + id + ", name='" + name + "', specialization='" + specialization + "', exp=" + experienceYears + "}";
    }
}
