package Gym_system.models;

import Gym_system.exceptions.GymException;

public class StrengthWorkout extends Workout {
    private double weightKg;
    private int sets;

    public StrengthWorkout(int id, String workoutName, int durationMinutes, int caloriesBurned, double weightKg, int sets) throws GymException {
        super(id, workoutName, durationMinutes, caloriesBurned);
        setWeightKg(weightKg);
        setSets(sets);
    }

    public void setWeightKg(double weightKg) throws GymException {
        if (weightKg < 0) throw new GymException("Weight cannot be negative.");
        this.weightKg = weightKg;
    }

    public void setSets(int sets) throws GymException {
        if (sets <= 0) throw new GymException("Sets must be greater than 0.");
        this.sets = sets;
    }

    @Override
    public void startSession() {
        System.out.println("Starting Strength Training: Lifting " + weightKg + "kg for " + sets + " sets.");
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " [Type: STRENGTH, Weight: " + weightKg + "kg]";
    }
}
