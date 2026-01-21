package Gym_system.menu;

import Gym_system.interfaces.Menu;
import Gym_system.models.*;
import Gym_system.exceptions.GymException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuManager implements Menu {
    private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<Trainers> trainers = new ArrayList<>();
    private ArrayList<Equipment> equipmentList = new ArrayList<>();
    private ArrayList<FitnessClass> classes = new ArrayList<>();
    private ArrayList<Workout> workouts = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void displayMenu() {
        System.out.println("\n--- Gym Management System ---");
        System.out.println("1. Add Member");
        System.out.println("2. View All Members");
        System.out.println("3. Add Trainer");
        System.out.println("4. View All Trainers");
        System.out.println("5. Add Equipment");
        System.out.println("6. View All Equipment");
        System.out.println("7. Add Fitness Class");
        System.out.println("8. View All Classes");
        System.out.println("9. Add Cardio Workout");
        System.out.println("10. Add Strength Workout");
        System.out.println("11. View All Workouts");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    @Override
    public void run() {
        boolean running = true;
        while (running) {
            try {
                displayMenu();
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1: addMember(); break;
                    case 2: viewAllMembers(); break;
                    case 3: addTrainer(); break;
                    case 4: viewAllTrainers(); break;
                    case 5: addEquipment(); break;
                    case 6: viewAllEquipment(); break;
                    case 7: addFitnessClass(); break;
                    case 8: viewAllClasses(); break;
                    case 9: addCardioWorkout(); break;
                    case 10: addStrengthWorkout(); break;
                    case 11: viewAllWorkouts(); break;
                    case 0:
                        System.out.println("Exiting system...");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a number.");
                scanner.next(); // Clear buffer
            } catch (GymException e) {
                System.out.println("Validation Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected Error: " + e.getMessage());
            }
        }
    }

    private void addMember() throws GymException {
        System.out.print("Enter ID: "); int id = scanner.nextInt();
        System.out.print("Enter Name: "); String name = scanner.next();
        System.out.print("Enter Age: "); int age = scanner.nextInt();
        members.add(new Member(id, name, age, true));
        System.out.println("Member added!");
    }

    private void viewAllMembers() {
        for (Member m : members) System.out.println(m);
    }

    private void addTrainer() throws GymException {
        System.out.print("Enter ID: "); int id = scanner.nextInt();
        System.out.print("Enter Name: "); String name = scanner.next();
        System.out.print("Enter Specialization: "); String spec = scanner.next();
        System.out.print("Enter Experience (years): "); int exp = scanner.nextInt();
        trainers.add(new Trainers(id, name, spec, exp));
        System.out.println("Trainer added!");
    }

    private void viewAllTrainers() {
        for (Trainers t : trainers) System.out.println(t);
    }

    private void addEquipment() throws GymException {
        System.out.print("Enter ID: "); int id = scanner.nextInt();
        System.out.print("Enter Name: "); String name = scanner.next();
        System.out.print("Enter Condition: "); String cond = scanner.next();
        equipmentList.add(new Equipment(id, name, cond, true));
        System.out.println("Equipment added!");
    }

    private void viewAllEquipment() {
        for (Equipment e : equipmentList) {
            System.out.println(e);
            if (e.needsMaintenance()) {
                System.out.println("-> WARNING: Needs maintenance!");
                e.performMaintenance();
            }
        }
    }

    private void addFitnessClass() throws GymException {
        if (trainers.isEmpty()) throw new GymException("No trainers available to assign.");
        System.out.print("Enter ID: "); int id = scanner.nextInt();
        System.out.print("Enter Class Name: "); String name = scanner.next();

        System.out.println("Select Trainer ID:");
        viewAllTrainers();
        int tId = scanner.nextInt();
        Trainers selected = null;
        for (Trainers t : trainers) if (t.getId() == tId) selected = t;

        if (selected == null) throw new GymException("Trainer not found.");

        System.out.print("Enter Max Participants: "); int max = scanner.nextInt();
        classes.add(new FitnessClass(id, name, selected, max));
        System.out.println("Class added!");
    }

    private void viewAllClasses() {
        for (FitnessClass f : classes) System.out.println(f);
    }

    private void addCardioWorkout() throws GymException {
        System.out.print("Enter ID: "); int id = scanner.nextInt();
        System.out.print("Name: "); String name = scanner.next();
        System.out.print("Duration (mins): "); int dur = scanner.nextInt();
        System.out.print("Calories: "); int cal = scanner.nextInt();
        System.out.print("Speed (km/h): "); double speed = scanner.nextDouble();
        System.out.print("Incline (0-15): "); int inc = scanner.nextInt();

        CardioWorkout cw = new CardioWorkout(id, name, dur, cal, speed, inc);
        workouts.add(cw);
        cw.startSession();
    }

    private void addStrengthWorkout() throws GymException {
        System.out.print("Enter ID: "); int id = scanner.nextInt();
        System.out.print("Name: "); String name = scanner.next();
        System.out.print("Duration (mins): "); int dur = scanner.nextInt();
        System.out.print("Calories: "); int cal = scanner.nextInt();
        System.out.print("Weight (kg): "); double weight = scanner.nextDouble();
        System.out.print("Sets: "); int sets = scanner.nextInt();

        StrengthWorkout sw = new StrengthWorkout(id, name, dur, cal, weight, sets);
        workouts.add(sw);
        sw.startSession();
    }

    private void viewAllWorkouts() {
        for (Workout w : workouts) System.out.println(w.getDetails());
    }
}
