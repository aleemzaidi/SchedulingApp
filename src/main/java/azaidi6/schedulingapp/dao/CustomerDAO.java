package azaidi6.schedulingapp.dao;

import azaidi6.schedulingapp.SchedulingApp;
import azaidi6.schedulingapp.helper.JDBC;
import azaidi6.schedulingapp.helper.TimeConversion;
import azaidi6.schedulingapp.model.Country;
import azaidi6.schedulingapp.model.Customer;
import azaidi6.schedulingapp.model.FirstLvlDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CustomerDAO {

    /**
     * Used to grab all customers in the database.
     * @return returns all customers from the database into an ObservableList of Customer objects
     */
    public static ObservableList<Customer> getCustomers() {

        ObservableList<Customer> customers = FXCollections.observableArrayList();

        try {
            JDBC.openConnection();
            String sqlQuery = "SELECT * FROM customers AS c JOIN first_level_divisions AS fd ON c.Division_ID = fd.Division_ID JOIN countries AS ct ON fd.COUNTRY_ID = ct.Country_ID";
            PreparedStatement ps = JDBC.connection.prepareStatement(sqlQuery);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int customerId = rs.getInt("c.Customer_ID");
                String customerName = rs.getString("c.Customer_Name");
                String address = rs.getString("c.Address");
                String postalCode = rs.getString("c.Postal_Code");
                String phoneNumber = rs.getString("c.Phone");
                LocalDateTime createDate = rs.getObject("c.Create_Date", LocalDateTime.class);
                String createdBy = rs.getString("c.Created_By");
                LocalDateTime lastUpdate = rs.getObject("c.Last_Update", LocalDateTime.class);
                String lastUpdatedBy = rs.getString("c.Last_Updated_By");
                int divisionId = rs.getInt("fd.Division_ID");
                String divisionName = rs.getString("fd.Division");
                LocalDateTime fdCreateDate = rs.getObject("fd.Create_Date", LocalDateTime.class);
                String fdCreatedBy = rs.getString("fd.Created_By");
                LocalDateTime fdLastUpdate = rs.getObject("fd.Last_Update", LocalDateTime.class);
                String fdLastUpdatedBy = rs.getString("fd.Last_Updated_By");
                int fdCountryId = rs.getInt("fd.COUNTRY_ID");
                int countryId = rs.getInt("ct.Country_ID");
                String country = rs.getString("ct.Country");
                LocalDateTime ctCreateDate = rs.getObject("ct.Create_Date", LocalDateTime.class);
                String ctCreatedBy = rs.getString("ct.Created_By");
                LocalDateTime ctLastUpdate = rs.getObject("ct.Last_Update", LocalDateTime.class);
                String ctLastUpdatedBy = rs.getString("ct.Last_Updated_By");

                Customer c = new Customer(customerId, customerName, address, postalCode, phoneNumber, TimeConversion.convertFromUTC(createDate),
                        createdBy, TimeConversion.convertFromUTC(lastUpdate), lastUpdatedBy, new FirstLvlDivision(divisionId, divisionName,
                        TimeConversion.convertFromUTC(fdCreateDate), fdCreatedBy, TimeConversion.convertFromUTC(fdLastUpdate),
                        fdLastUpdatedBy, fdCountryId), new Country(countryId, country, TimeConversion.convertFromUTC(ctCreateDate), ctCreatedBy,
                        TimeConversion.convertFromUTC(ctLastUpdate), ctLastUpdatedBy));
                customers.add(c);
            }
            JDBC.closeConnection();
        } catch (SQLException e) {
            JDBC.closeConnection();
            throw new RuntimeException(e);
        }

        return customers;

    }

    /**
     * Used to add new customers into the database.
     * @param customerName string of customer name
     * @param address string of customer's address
     * @param postalCode string of customer's postal code
     * @param phoneNumber string of customer's phone number
     * @param divisionId int of customers division (city)
     */
    public static void addCustomer(String customerName, String address, String postalCode, String phoneNumber, int divisionId) {
        LocalDateTime currTimeUTC = TimeConversion.convertToUTC(LocalDateTime.now());
        try {
            JDBC.openConnection();
            String sqlQuery = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = JDBC.connection.prepareStatement(sqlQuery);
            ps.setString(1, customerName);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phoneNumber);
            ps.setObject(5, currTimeUTC);
            ps.setString(6, SchedulingApp.getUser().getUsername());
            ps.setObject(7, currTimeUTC);
            ps.setString(8, SchedulingApp.getUser().getUsername());
            ps.setInt(9, divisionId);
            ps.executeUpdate();
        } catch (SQLException e) {
            JDBC.closeConnection();
            throw new RuntimeException(e);
        }

    }

    /**
     * Used to make updates to an existing customer's information in the database.
     * @param customerId int of customer id in database
     * @param customerName string of customer's name
     * @param address string of customer's address
     * @param postalCode string of customer's postal code
     * @param phoneNumber string of customer's phone number
     * @param divisionId int of customer's division (city)
     */
    public static void updateCustomer(int customerId, String customerName, String address, String postalCode, String phoneNumber, int divisionId) {
        LocalDateTime currTimeUTC = TimeConversion.convertToUTC(LocalDateTime.now());
        try {
            JDBC.openConnection();
            String sqlQuery = "UPDATE customers " +
                    "SET Customer_Name= ? , " +
                    "Address = ? , " +
                    "Postal_Code = ? , " +
                    "Phone = ? , " +
                    "Last_Update = ? , " +
                    "Last_Updated_By = ? , " +
                    "Division_ID = ? " +
                    "WHERE Customer_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sqlQuery);
            ps.setString(1, customerName);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phoneNumber);
            ps.setObject(5, currTimeUTC);
            ps.setString(6, SchedulingApp.getUser().getUsername());
            ps.setInt(7, divisionId);
            ps.setInt(8, customerId);
            ps.executeUpdate();
        } catch (SQLException e) {
            JDBC.closeConnection();
            throw new RuntimeException(e);
        }

    }

    /**
     * Used to delete customers from the database.
     * @param customerId int of customer id in the database
     */
    public static void deleteCustomer(int customerId) {
        try {
            JDBC.openConnection();
            String sqlQuery = "DELETE FROM customers " +
                    "WHERE Customer_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sqlQuery);
            ps.setInt(1, customerId);
            ps.executeUpdate();
        } catch (SQLException e) {
            JDBC.closeConnection();
            throw new RuntimeException(e);
        }

    }

}
