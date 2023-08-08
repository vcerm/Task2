package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection c = Util.getConnection()) {
            String sql = "CREATE TABLE IF NOT EXISTS task1" +
                    "(    ID       bigint unsigned auto_increment" +
                    "        primary key," +
                    "    Name     varchar(255) null," +
                    "    LastName varchar(255) null," +
                    "    Age      int          null" +
                    ")";
            Statement s = c.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Connection c = Util.getConnection()) {
            String sql = "DROP TABLE IF EXISTS  `task1`";
            Statement s = c.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection c = Util.getConnection()) {
            String sql = "INSERT INTO task1 (Name, LastName, Age) VALUES (?, ?, ?)";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
            System.out.printf("User с именем – %s добавлен в базу данных", name);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Connection c = Util.getConnection()) {
            String sql = "DELETE FROM task1 WHERE ID=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        try (Connection c = Util.getConnection()) {
            String sql = "SELECT * FROM task1";
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            List<User> list = new ArrayList<>();

            while (rs.next()) {
                list.add(new User(
                        rs.getString("Name"),
                        rs.getString("LastName"),
                        rs.getByte("Age")
                ));
            }

            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void cleanUsersTable() {
        try (Connection c = Util.getConnection()) {
            String sql = "DELETE FROM task1";
            Statement s = c.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
