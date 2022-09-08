package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS users(" +
            "id Integer NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
            "name VARCHAR NOT NULL," +
            "lastName VARCHAR NOT NULL," +
            "age INTEGER NOT NULL)";

    private static final String DROP_USER_TABLE = "DROP TABLE IF EXISTS users";

    private static final String SAVE_USER_TABLE = "INSERT INTO users(name, lastName, age) VALUES(?, ?, ?)";

    private static final String REMOVE_USER_BY_ID = "DELETE FROM users WHERE id = ?";

    private static final String GET_ALL_USERS = "SELECT * FROM users";

    private static final String TRUNCATE_TABLE = "TRUNCATE TABLE users";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Connection connection = Util.getConnection()) {
            Statement statement = Util.getStatement(connection);
            statement.executeUpdate(CREATE_USER_TABLE);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection()) {

            Statement statement = Util.getStatement(connection);
            statement.executeUpdate(DROP_USER_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        try (Connection connection = Util.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER_TABLE);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {

        try (Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER_BY_ID);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = Util.getConnection()) {
            Statement statement = Util.getStatement(connection);
            ResultSet resultSet = statement.executeQuery(GET_ALL_USERS);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection()) {

            Statement statement = Util.getStatement(connection);
            statement.executeUpdate(TRUNCATE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
