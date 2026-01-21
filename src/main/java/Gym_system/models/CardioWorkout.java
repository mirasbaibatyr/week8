package Gym_system.models;

import Gym_system.exceptions.GymException;

public class CardioWorkout extends Workout {
    private double speedKmH;
    private int inclineLevel;

    public CardioWorkout(int id, String workoutName, int durationMinutes, int caloriesBurned, double speedKmH, int inclineLevel) throws GymException {
        super(id, workoutName, durationMinutes, caloriesBurned);
        setSpeedKmH(speedKmH);
        setInclineLevel(inclineLevel);
    }

    public void setSpeedKmH(double speedKmH) throws GymException {
        if (speedKmH < 0) throw new GymException("Speed cannot be negative.");
        this.speedKmH = speedKmH;
    }

    public void setInclineLevel(int inclineLevel) throws GymException {
        if (inclineLevel < 0 || inclineLevel > 15) throw new GymException("Incline must be between 0 and 15.");
        this.inclineLevel = inclineLevel;
    }

    @Override
    public void startSession() {
        System.out.println("Running Cardio Session on Treadmill at " + speedKmH + " km/h.");
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " [Type: CARDIO, Incline: " + inclineLevel + "]";
    }
}