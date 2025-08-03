package azaidi6.schedulingapp.dao;

import azaidi6.schedulingapp.helper.JDBC;
import azaidi6.schedulingapp.helper.TimeConversion;
import azaidi6.schedulingapp.model.Contact;
import azaidi6.schedulingapp.model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ContactDAO {

    /**
     * Used to grab all contacts in the database.
     * @return returns all contacts from the database in an ObservableList array of Contact objects
     */
    public static ObservableList<Contact> getContacts() {

        ObservableList<Contact> contacts = FXCollections.observableArrayList();

        try {
            JDBC.openConnection();
            String sql = "SELECT * FROM contacts";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Contact ct = new Contact(
                        rs.getInt("Contact_ID"),
                        rs.getString("Contact_Name"),
                        rs.getString("Email")
                );
                contacts.add(ct);
            }
            JDBC.closeConnection();
            return contacts;

        } catch (SQLException e) {
            JDBC.closeConnection();
            throw new RuntimeException(e);
        }

    }

}
