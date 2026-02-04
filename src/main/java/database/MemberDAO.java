package database;

import Gym_system.models.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    public void addMember(Member member) {
        String sql = "INSERT INTO member(name, membership_type, monthly_fee) VALUES (?, ?, ?)";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return;

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, member.getName());
            ps.setString(2, member.getMembershipType());
            ps.setDouble(3, member.getMonthlyFee());
            ps.executeUpdate();
            ps.close();
            System.out.println(" Member added");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
    }

    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM member ORDER BY member_id";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return members;

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                members.add(new Member(
                        rs.getInt("member_id"),
                        rs.getString("name"),
                        rs.getString("membership_type"),
                        rs.getDouble("monthly_fee")
                ));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return members;
    }

    public Member getMemberById(int id) {
        String sql = "SELECT * FROM member WHERE member_id = ?";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return null;

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Member(
                        rs.getInt("member_id"),
                        rs.getString("name"),
                        rs.getString("membership_type"),
                        rs.getDouble("monthly_fee")
                );
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return null;
    }

    public boolean updateMember(Member member) {
        String sql = "UPDATE member SET name = ?, membership_type = ?, monthly_fee = ? WHERE member_id = ?";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, member.getName());
            ps.setString(2, member.getMembershipType());
            ps.setDouble(3, member.getMonthlyFee());
            ps.setInt(4, member.getMemberId());

            int rows = ps.executeUpdate();
            ps.close();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return false;
    }

    public boolean deleteMember(int id) {
        String sql = "DELETE FROM member WHERE member_id = ?";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            ps.close();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return false;
    }

    public List<Member> searchByName(String name) {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM member WHERE name ILIKE ? ORDER BY name";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return members;

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                members.add(new Member(
                        rs.getInt("member_id"),
                        rs.getString("name"),
                        rs.getString("membership_type"),
                        rs.getDouble("monthly_fee")
                ));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return members;
    }
}

