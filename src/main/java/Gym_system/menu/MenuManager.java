package Gym_system.menu;

import database.MemberDAO;
import Gym_system.models.Member;

import java.util.List;
import java.util.Scanner;

public class MenuManager {

    private final Scanner scanner = new Scanner(System.in);
    private final MemberDAO dao = new MemberDAO();

    public void start() {
        while (true) {
            System.out.println("""
                    
                    ===== GYM MENU =====
                    1. Add Member
                    2. View All Members
                    3. Update Member
                    4. Delete Member
                    5. Search Member by Name
                    6. Search Member by ID
                    0. Exit
                    """);

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addMember();
                case 2 -> viewAll();
                case 3 -> updateMember();
                case 4 -> deleteMember();
                case 5 -> searchByName();
                case 6 -> viewById();
                case 0 -> System.exit(0);
                default -> System.out.println("Invalid option");
            }
        }
    }

    private void addMember() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Membership type: ");
        String type = scanner.nextLine();
        System.out.print("Monthly fee: ");
        double fee = scanner.nextDouble();
        scanner.nextLine();

        dao.addMember(new Member(name, type, fee));
    }

    private void viewAll() {
        dao.getAllMembers().forEach(System.out::println);
    }

    private void viewById() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println(dao.getMemberById(id));
    }

    private void updateMember() {
        System.out.print("Enter member ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Member old = dao.getMemberById(id);
        if (old == null) {
            System.out.println("Member not found");
            return;
        }

        System.out.println("Current: " + old);

        System.out.print("New name: ");
        String name = scanner.nextLine();
        System.out.print("New type: ");
        String type = scanner.nextLine();
        System.out.print("New fee: ");
        double fee = scanner.nextDouble();
        scanner.nextLine();

        dao.updateMember(new Member(id, name, type, fee));
    }

    private void deleteMember() {
        System.out.print("Enter member ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Member m = dao.getMemberById(id);
        if (m == null) {
            System.out.println("Not found");
            return;
        }

        System.out.println("Delete: " + m);
        System.out.print("Are you sure? (yes/no): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            dao.deleteMember(id);
            System.out.println("Deleted");
        } else {
            System.out.println("Cancelled");
        }
    }

    private void searchByName() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        List<Member> result = dao.searchByName(name);
        result.forEach(System.out::println);
    }
}

