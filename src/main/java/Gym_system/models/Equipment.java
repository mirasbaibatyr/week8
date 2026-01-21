package Gym_system.models;

import Gym_system.interfaces.Maintainable;
import Gym_system.exceptions.GymException;

public class Equipment implements Maintainable {
    private int id;
    private String name;
    private String condition;
    private boolean available;

    public Equipment(int id, String name, String condition, boolean available) throws GymException {
        this.id = id;
        setName(name);
        this.condition = condition;
        this.available = available;
    }

    public void setName(String name) throws GymException {
        if(name == null || name.isEmpty()) throw new GymException("Name required.");
        this.name = name;
    }

    // Реализация методов интерфейса Maintainable
    @Override
    public void performMaintenance() {
        System.out.println("Maintenance performed on " + name + ". Condition is now Excellent.");
        this.condition = "Excellent";
    }

    @Override
    public boolean needsMaintenance() {
        return "Poor".equalsIgnoreCase(condition) || "Broken".equalsIgnoreCase(condition);
    }

    public String toString() {
        return "Equipment{id=" + id + ", name='" + name + "', condition='" + condition + "', available=" + available + "}";
    }
}
