import database.MemberDAO;
import Gym_system.models.Member;

public class Main {

    public static void main(String[] args) {

        MemberDAO dao = new MemberDAO();

        Member member =
                new Member(0, "Alex", "Premium", 25000);

        dao.insertMember(member);
        dao.getAllMembers();
    }
}
