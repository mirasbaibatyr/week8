package database;

import Gym_system.models.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {

    public void insertMember(Member member) {
        String sql =
                "INSERT INTO member (name, membership_type, monthly_fee) VALUES (?, ?, ?)";

        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, member.getName());
            statement.setString(2, member.getMembershipType());
            statement.setDouble(3, member.getMonthlyFee());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.close(connection);
        }
    }


    public void getAllMembers() {
        String sql = "SELECT * FROM member";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println(
                        resultSet.getInt("member_id") + " | " +
                                resultSet.getString("name") + " | " +
                                resultSet.getString("membership_type") + " | " +
                                resultSet.getDouble("monthly_fee")
                );
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.close(connection);
        }
    }
}
