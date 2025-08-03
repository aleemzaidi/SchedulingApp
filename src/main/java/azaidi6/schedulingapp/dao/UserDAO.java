package azaidi6.schedulingapp.dao;

import azaidi6.schedulingapp.helper.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    /**
     *
     * @param username string of username inputted on login screen.
     * @param password string of password inputted on login screen.
     * @return returns true if username and password match in database, otherwise returns false if no match.
     */
    public static boolean userLogin(String username, String password) {
        JDBC.openConnection();
        try {
            PreparedStatement ps = JDBC.connection.prepareStatement("SELECT * FROM users WHERE User_Name = ? AND Password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                JDBC.closeConnection();
                return true;
            }
        } catch (SQLException e) {
            JDBC.closeConnection();
            System.out.println("Error Logging in:" + e.getMessage());
        }
        JDBC.closeConnection();
        return false;
    }

    /**
     *
     * @param username string of username inputted on the login screen.
     * @return returns true if username is found in database, otherwise returns false if not found.
     */
    public static boolean checkUsername(String username) {
        JDBC.openConnection();
        try {
            PreparedStatement ps = JDBC.connection.prepareStatement("SELECT * FROM users WHERE User_Name = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                JDBC.closeConnection();
                return true;
            }
        } catch (SQLException e) {
            JDBC.closeConnection();
            System.out.println("Error Logging in:" + e.getMessage());
        }
        JDBC.closeConnection();
        return false;
    }

    /**
     *
     * @param username string of username inputted on the login screen.
     * @return returns the id of the username in the database.
     */
    public static int getUserId(String username) {
        JDBC.openConnection();
        int userId = -1;
        try {
            PreparedStatement ps = JDBC.connection.prepareStatement("SELECT * FROM users WHERE User_Name = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                userId =  rs.getInt("User_ID");
            }

        } catch (SQLException e) {
            JDBC.closeConnection();
            throw new RuntimeException(e);
        }
        JDBC.closeConnection();
        return userId;
    }

}