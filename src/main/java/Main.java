import Gym_system.menu.MenuManager;
import database.MemberDAO;
import Gym_system.models.Member;
public class Main {
    public static void main(String[] args) {
            MenuManager menu = new MenuManager();
            menu.start();
        MemberDAO dao = new MemberDAO();
    }
}