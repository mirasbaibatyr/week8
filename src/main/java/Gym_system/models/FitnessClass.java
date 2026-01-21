package Gym_system.models;

import Gym_system.exceptions.GymException;

public class FitnessClass {
    private int id;
    private String name;
    private Trainers trainer;
    private int maxParticipants;

    public FitnessClass(int id, String name, Trainers trainer, int maxParticipants) throws GymException {
        this.id = id;
        setName(name);
        this.trainer = trainer;
        setMaxParticipants(maxParticipants);
    }

    public void setName(String name) throws GymException {
        if(name == null || name.isEmpty()) throw new GymException("Class name required.");
        this.name = name;
    }

    public void setMaxParticipants(int maxParticipants) throws GymException {
        if(maxParticipants <= 0) throw new GymException("Max participants must be positive.");
        this.maxParticipants = maxParticipants;
    }

    public String toString() {
        String trainerName = (trainer != null) ? trainer.getName() : "None";
        return "FitnessClass{id=" + id + ", name='" + name + "', trainer=" + trainerName + ", maxParticipants=" + maxParticipants + "}";
    }
}
