package Gym_system.models;

public class Member {

    private int memberId;
    private String name;
    private String membershipType;
    private double monthlyFee;

    public Member(int memberId, String name, String membershipType, double monthlyFee) {
        this.memberId = memberId;
        this.name = name;
        this.membershipType = membershipType;
        this.monthlyFee = monthlyFee;
    }

    public String getName() {
        return name;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public double getMonthlyFee() {
        return monthlyFee;
    }
}



