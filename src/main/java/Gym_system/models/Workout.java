package Gym_system.models;

import Gym_system.exceptions.GymException;

public abstract class Workout {
    protected int id;
    protected String workoutName;
    protected int durationMinutes;
    protected int caloriesBurned;

    public Workout(int id, String workoutName, int durationMinutes, int caloriesBurned) throws GymException {
        setId(id);
        setWorkoutName(workoutName);
        setDurationMinutes(durationMinutes);
        setCaloriesBurned(caloriesBurned);
    }

    public abstract void startSession();

    public String getDetails() {
        return "ID: " + id + " | " + workoutName + " (" + durationMinutes + " mins)";
    }

    public void setId(int id) { this.id = id; }

    public void setWorkoutName(String workoutName) throws GymException {
        if (workoutName == null || workoutName.trim().isEmpty()) {
            throw new GymException("Workout name cannot be empty.");
        }
        this.workoutName = workoutName;
    }

    public void setDurationMinutes(int durationMinutes) throws GymException {
        if (durationMinutes <= 0) {
            throw new GymException("Duration must be positive.");
        }
        this.durationMinutes = durationMinutes;
    }

    public void setCaloriesBurned(int caloriesBurned) throws GymException {
        if (caloriesBurned < 0) {
            throw new GymException("Calories cannot be negative.");
        }
        this.caloriesBurned = caloriesBurned;
    }

    @Override
    public String toString() {
        return "Workout{id=" + id + ", name='" + workoutName + "'}";
    }
}
