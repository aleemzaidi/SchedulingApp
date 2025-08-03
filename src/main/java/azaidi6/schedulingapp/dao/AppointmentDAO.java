package azaidi6.schedulingapp.dao;

import azaidi6.schedulingapp.SchedulingApp;
import azaidi6.schedulingapp.helper.JDBC;
import azaidi6.schedulingapp.helper.TimeConversion;
import azaidi6.schedulingapp.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppointmentDAO {

    /**
     * Used to grab all appointments in the database.
     * @return returns all appointments from the database in an ObservableList array of Appointment objects
     */
    public static ObservableList<Appointment> getAllAppointments() {

        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        try {
            JDBC.openConnection();
            String sql = "SELECT * FROM appointments AS a JOIN contacts AS ct ON a.Contact_ID = ct.Contact_ID" +
                    " JOIN customers AS c ON a.Customer_ID = c.Customer_ID" +
                    " JOIN first_level_divisions AS fd ON c.Division_ID = fd.Division_ID" +
                    " JOIN countries As cc ON fd.COUNTRY_ID = cc.Country_ID";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy h:mma");

            while(rs.next()) {
                Appointment app = new Appointment(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Type"),
                        df.format(TimeConversion.convertFromUTC(rs.getObject("Start", LocalDateTime.class))),
                        df.format(TimeConversion.convertFromUTC(rs.getObject("End", LocalDateTime.class))),
                        TimeConversion.convertFromUTC(rs.getObject("Create_Date", LocalDateTime.class)),
                        rs.getString("Created_By"),
                        TimeConversion.convertFromUTC(rs.getObject("Last_Update", LocalDateTime.class)),
                        rs.getString("Last_Updated_By"),
                        new Customer(
                                rs.getInt("c.Customer_ID"),
                                rs.getString("c.Customer_Name"),
                                rs.getString("c.Address"),
                                rs.getString("c.Postal_Code"),
                                rs.getString("c.Phone"),
                                rs.getObject("c.Create_Date", LocalDateTime.class),
                                rs.getString("c.Created_By"),
                                rs.getObject("c.Last_Update", LocalDateTime.class),
                                rs.getString("c.Last_Updated_By"),
                                new FirstLvlDivision(
                                        rs.getInt("fd.Division_ID"),
                                        rs.getString("fd.Division"),
                                        rs.getObject("fd.Create_Date", LocalDateTime.class),
                                        rs.getString("fd.Created_By"),
                                        rs.getObject("fd.Last_Update", LocalDateTime.class),
                                        rs.getString("fd.Last_Updated_By"),
                                        rs.getInt("fd.COUNTRY_ID")
                                ),
                                new Country(
                                        rs.getInt("cc.Country_ID"),
                                        rs.getString("cc.Country"),
                                        rs.getObject("cc.Create_Date", LocalDateTime.class),
                                        rs.getString("cc.Created_By"),
                                        rs.getObject("cc.Last_Update", LocalDateTime.class),
                                        rs.getString("cc.Last_Updated_By")
                                )
                        ),
                        rs.getInt("User_ID"),
                        new Contact(
                                rs.getInt("ct.Contact_ID"),
                                rs.getString("ct.Contact_Name"),
                                rs.getString("ct.Email")
                        )
                );
                appointments.add(app);
            }

            JDBC.closeConnection();
            return appointments;

        } catch (SQLException e) {
            JDBC.closeConnection();
            throw new RuntimeException(e);
        }
    }

    /**
     * Used to grab all appointments in the current week
     * @return returns all appointments in the current week from the database in an ObservableList array of Appointment objects
     */
    public static ObservableList<Appointment> getWeekAppointments() {

        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        try {
            JDBC.openConnection();
            String sql = "SELECT * FROM appointments AS a JOIN contacts AS ct ON a.Contact_ID = ct.Contact_ID" +
                    " JOIN customers AS c ON a.Customer_ID = c.Customer_ID" +
                    " JOIN first_level_divisions AS fd ON c.Division_ID = fd.Division_ID" +
                    " JOIN countries AS cc ON fd.COUNTRY_ID = cc.Country_ID" +
                    " WHERE YEARWEEK(Start) = YEARWEEK(NOW())";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy h:mma");

            while(rs.next()) {
                Appointment app = new Appointment(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Type"),
                        df.format(TimeConversion.convertFromUTC(rs.getObject("Start", LocalDateTime.class))),
                        df.format(TimeConversion.convertFromUTC(rs.getObject("End", LocalDateTime.class))),
                        TimeConversion.convertFromUTC(rs.getObject("Create_Date", LocalDateTime.class)),
                        rs.getString("Created_By"),
                        TimeConversion.convertFromUTC(rs.getObject("Last_Update", LocalDateTime.class)),
                        rs.getString("Last_Updated_By"),
                        new Customer(
                                rs.getInt("c.Customer_ID"),
                                rs.getString("c.Customer_Name"),
                                rs.getString("c.Address"),
                                rs.getString("c.Postal_Code"),
                                rs.getString("c.Phone"),
                                rs.getObject("c.Create_Date", LocalDateTime.class),
                                rs.getString("c.Created_By"),
                                rs.getObject("c.Last_Update", LocalDateTime.class),
                                rs.getString("c.Last_Updated_By"),
                                new FirstLvlDivision(
                                        rs.getInt("fd.Division_ID"),
                                        rs.getString("fd.Division"),
                                        rs.getObject("fd.Create_Date", LocalDateTime.class),
                                        rs.getString("fd.Created_By"),
                                        rs.getObject("fd.Last_Update", LocalDateTime.class),
                                        rs.getString("fd.Last_Updated_By"),
                                        rs.getInt("fd.COUNTRY_ID")
                                ),
                                new Country(
                                        rs.getInt("cc.Country_ID"),
                                        rs.getString("cc.Country"),
                                        rs.getObject("cc.Create_Date", LocalDateTime.class),
                                        rs.getString("cc.Created_By"),
                                        rs.getObject("cc.Last_Update", LocalDateTime.class),
                                        rs.getString("cc.Last_Updated_By")
                                )
                        ),
                        rs.getInt("User_ID"),
                        new Contact(
                                rs.getInt("ct.Contact_ID"),
                                rs.getString("ct.Contact_Name"),
                                rs.getString("ct.Email")
                        )
                );
                appointments.add(app);
            }

            JDBC.closeConnection();
            return appointments;

        } catch (SQLException e) {
            JDBC.closeConnection();
            throw new RuntimeException(e);
        }
    }

    /**
     * Used to grab all appointments in the current month
     * @return returns all appointments in the current month from the database in an ObservableList array of Appointment objects.
     */
    public static ObservableList<Appointment> getMonthAppointments() {

        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        try {
            JDBC.openConnection();
            String sql = "SELECT * FROM appointments AS a JOIN contacts AS ct ON a.Contact_ID = ct.Contact_ID" +
                    " JOIN customers AS c ON a.Customer_ID = c.Customer_ID" +
                    " JOIN first_level_divisions AS fd ON c.Division_ID = fd.Division_ID" +
                    " JOIN countries As cc ON fd.COUNTRY_ID = cc.Country_ID" +
                    " WHERE MONTH(Start) = MONTH(NOW())";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy h:mma");

            while(rs.next()) {
                Appointment app = new Appointment(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Type"),
                        df.format(TimeConversion.convertFromUTC(rs.getObject("Start", LocalDateTime.class))),
                        df.format(TimeConversion.convertFromUTC(rs.getObject("End", LocalDateTime.class))),
                        TimeConversion.convertFromUTC(rs.getObject("Create_Date", LocalDateTime.class)),
                        rs.getString("Created_By"),
                        TimeConversion.convertFromUTC(rs.getObject("Last_Update", LocalDateTime.class)),
                        rs.getString("Last_Updated_By"),
                        new Customer(
                                rs.getInt("c.Customer_ID"),
                                rs.getString("c.Customer_Name"),
                                rs.getString("c.Address"),
                                rs.getString("c.Postal_Code"),
                                rs.getString("c.Phone"),
                                rs.getObject("c.Create_Date", LocalDateTime.class),
                                rs.getString("c.Created_By"),
                                rs.getObject("c.Last_Update", LocalDateTime.class),
                                rs.getString("c.Last_Updated_By"),
                                new FirstLvlDivision(
                                        rs.getInt("fd.Division_ID"),
                                        rs.getString("fd.Division"),
                                        rs.getObject("fd.Create_Date", LocalDateTime.class),
                                        rs.getString("fd.Created_By"),
                                        rs.getObject("fd.Last_Update", LocalDateTime.class),
                                        rs.getString("fd.Last_Updated_By"),
                                        rs.getInt("fd.COUNTRY_ID")
                                ),
                                new Country(
                                        rs.getInt("cc.Country_ID"),
                                        rs.getString("cc.Country"),
                                        rs.getObject("cc.Create_Date", LocalDateTime.class),
                                        rs.getString("cc.Created_By"),
                                        rs.getObject("cc.Last_Update", LocalDateTime.class),
                                        rs.getString("cc.Last_Updated_By")
                                )
                        ),
                        rs.getInt("User_ID"),
                        new Contact(
                                rs.getInt("ct.Contact_ID"),
                                rs.getString("ct.Contact_Name"),
                                rs.getString("ct.Email")
                        )
                );
                appointments.add(app);
            }

            JDBC.closeConnection();
            return appointments;

        } catch (SQLException e) {
            JDBC.closeConnection();
            throw new RuntimeException(e);
        }
    }

    /**
     * Used to add new appointments into the database
     * @param title string of appointment title
     * @param description string of appointment description
     * @param location string of appointment location
     * @param type string of appointment type
     * @param start LocalDateTime of appointment start date/time
     * @param end LocalDateTime of appointment end date/time
     * @param customerId int of customer's id attending the appointment
     * @param userId int of user's id that created the appointment
     * @param contactId int of contact's id that will conduct the appointment
     */
    public static void addAppointment(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, int contactId) {

        LocalDateTime now = LocalDateTime.now();

        try {
            JDBC.openConnection();
            String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4,type);
            ps.setObject(5, TimeConversion.convertToUTC(start));
            ps.setObject(6, TimeConversion.convertToUTC(end));
            ps.setObject(7, TimeConversion.convertToUTC(now));
            ps.setString(8, SchedulingApp.getUser().getUsername());
            ps.setObject(9, TimeConversion.convertToUTC(now));
            ps.setString(10, SchedulingApp.getUser().getUsername());
            ps.setInt(11, customerId);
            ps.setInt(12, userId);
            ps.setInt(13, contactId);
            ps.executeUpdate();
            JDBC.closeConnection();
        } catch (SQLException e) {
            JDBC.closeConnection();
            throw new RuntimeException(e);
        }

    }

    /**
     * Used to update existing appointments in the database
     * @param appId int of the appointment id
     * @param title string of appointment title
     * @param description string of appointment description
     * @param location string of appointment location
     * @param type string of appointment type
     * @param start LocalDateTime of appointment start date/time
     * @param end LocalDateTime of appointment end date/time
     * @param customerId int of customer's id attending the appointment
     * @param userId int of user's id that created the appointment
     * @param contactId int of contact's id that will conduct the appointment
     */
    public static void updateAppointment(int appId, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, int contactId) {

        LocalDateTime now = LocalDateTime.now();

        try {
            JDBC.openConnection();
            String sql = "UPDATE appointments SET " +
                    "Title = ?, " +
                    "Description = ?, " +
                    "Location = ?, " +
                    "Type = ?, " +
                    "Start = ?, " +
                    "End = ?, " +
                    "Last_Update = ?, " +
                    "Last_Updated_By = ?, " +
                    "Customer_ID = ?, " +
                    "User_ID = ?, " +
                    "Contact_ID = ? " +
                    "WHERE Appointment_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4,type);
            ps.setObject(5, TimeConversion.convertToUTC(start));
            ps.setObject(6, TimeConversion.convertToUTC(end));
            ps.setObject(7, TimeConversion.convertToUTC(now));
            ps.setString(8, SchedulingApp.getUser().getUsername());
            ps.setInt(9, customerId);
            ps.setInt(10, userId);
            ps.setInt(11, contactId);
            ps.setInt(12, appId);

            ps.executeUpdate();
            JDBC.closeConnection();
        } catch (SQLException e) {
            JDBC.closeConnection();
            throw new RuntimeException(e);
        }

    }

    /**
     * Used to delete the appointment from the database
     * @param appId int of the appointment user wishes to delete
     */
    public static void deleteAppointments(int appId) {
        try {
            JDBC.openConnection();
            String sqlQuery = "DELETE FROM appointments " +
                    "WHERE Customer_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sqlQuery);
            ps.setInt(1, appId);
            ps.executeUpdate();
        } catch (SQLException e) {
            JDBC.closeConnection();
            throw new RuntimeException(e);
        }

    }

    /**
     * Used to check if the contact already has scheduled appointment during selected times.
     * @param startDateTime LocalDateTime of the appointment start time entered
     * @param endDateTime LocalDateTime of the appointment end time entered
     * @param contactId int of the contact id that will conduct the appointment
     * @return returns true if the associated contact has an appointment scheduled during selected times,
     *          otherwise returns false if no appointments are found during selected times.
     */
    public static boolean checkContactBusy(LocalDateTime startDateTime, LocalDateTime endDateTime, int contactId) {
        try {
            JDBC.openConnection();
            String sql = "SELECT * FROM appointments" +
                    " WHERE (Start BETWEEN ? AND ? AND Contact_ID = ?) OR" +
                    " (End BETWEEN ? AND ? AND Contact_ID = ?)";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setObject(1, TimeConversion.convertToUTC(startDateTime).minusMinutes(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            ps.setObject(2, TimeConversion.convertToUTC(endDateTime).plusMinutes(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            ps.setInt(3, contactId);
            ps.setObject(4, TimeConversion.convertToUTC(startDateTime).minusMinutes(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            ps.setObject(5, TimeConversion.convertToUTC(endDateTime).plusMinutes(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            ps.setInt(6, contactId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                JDBC.closeConnection();
                return true;
            }
        } catch (SQLException e) {
            JDBC.closeConnection();
            throw new RuntimeException(e);
        }
        JDBC.closeConnection();
        return false;
    }

    /**
     * Used to check if the contact already has scheduled appointment during selected times not including the appointment id being updated.
     * @param startDateTime LocalDateTime of the appointment start time entered
     * @param endDateTime LocalDateTime of the appointment end time entered
     * @param contactId int of the contact id that will conduct the appointment
     * @param appId int of the appointment id being updated
     * @return returns true if the associated contact has an appointment scheduled during selected times,
     *          otherwise returns false if no appointments are found during selected times. Not including
     *          the current appointment id being updated.
     */
    public static boolean checkContactBusy(LocalDateTime startDateTime, LocalDateTime endDateTime, int contactId, int appId) {
        try {
            JDBC.openConnection();
            String sql = "SELECT * FROM appointments" +
                    " WHERE (Start BETWEEN ? AND ? AND Contact_ID = ? AND Appointment_ID != ?) OR" +
                    " (End BETWEEN ? AND ? AND Contact_ID = ? AND Appointment_ID != ?)";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setObject(1, TimeConversion.convertToUTC(startDateTime).minusMinutes(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            ps.setObject(2, TimeConversion.convertToUTC(endDateTime).plusMinutes(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            ps.setInt(3, contactId);
            ps.setInt(4, appId);
            ps.setObject(5, TimeConversion.convertToUTC(startDateTime).minusMinutes(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            ps.setObject(6, TimeConversion.convertToUTC(endDateTime).plusMinutes(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            ps.setInt(7, contactId);
            ps.setInt(8, appId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                JDBC.closeConnection();
                return true;
            }
        } catch (SQLException e) {
            JDBC.closeConnection();
            throw new RuntimeException(e);
        }
        JDBC.closeConnection();
        return false;
    }

    /**
     * Used to check if customer already has an appointment scheduled during selected times.
     * @param startDateTime LocalDateTime of appointment start time entered
     * @param endDateTime LocalDateTime of appointment end time entered
     * @param customerId int of customer id attending the appointment
     * @return returns true if the associated customer has an appointment scheduled during selected times,
     *         otherwise returns false if no appointments are found during selected times.
     */
    public static boolean checkCustomerBusy(LocalDateTime startDateTime, LocalDateTime endDateTime, int customerId) {
        try {
            JDBC.openConnection();
            String sql = "SELECT * FROM appointments" +
                    " WHERE (Start BETWEEN ? AND ? AND Customer_ID = ?) OR" +
                    " (End BETWEEN ? AND ? AND Customer_ID = ?)";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setObject(1, TimeConversion.convertToUTC(startDateTime).minusMinutes(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            ps.setObject(2, TimeConversion.convertToUTC(endDateTime).plusMinutes(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            ps.setInt(3, customerId);
            ps.setObject(4, TimeConversion.convertToUTC(startDateTime).minusMinutes(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            ps.setObject(5, TimeConversion.convertToUTC(endDateTime).plusMinutes(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            ps.setInt(6, customerId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                JDBC.closeConnection();
                return true;
            }
        } catch (SQLException e) {
            JDBC.closeConnection();
            throw new RuntimeException(e);
        }
        JDBC.closeConnection();
        return false;
    }

    /**
     * Used to check if the customer already has scheduled appointment during selected times not including the appointment id being updated.
     * @param startDateTime LocalDateTime of appointment start time entered
     * @param endDateTime LocalDateTime of appointment end time entered
     * @param customerId  int of the customer attending the appointment
     * @param appId int of the appointment id being updated
     * @return returns true if the associated customer has an appointment scheduled during selected times,
     *         otherwise returns false if no appointments are found during selected times. Not including
     *         the current appointment id being updated.
     */
    public static boolean checkCustomerBusy(LocalDateTime startDateTime, LocalDateTime endDateTime, int customerId, int appId) {
        try {
            JDBC.openConnection();
            String sql = "SELECT * FROM appointments" +
                    " WHERE (Start BETWEEN ? AND ? AND Customer_ID = ? AND Appointment_ID != ?) OR" +
                    " (End BETWEEN ? AND ? AND Customer_ID = ? AND Appointment_ID != ?)";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setObject(1, TimeConversion.convertToUTC(startDateTime).minusMinutes(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            ps.setObject(2, TimeConversion.convertToUTC(endDateTime).plusMinutes(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            ps.setInt(3, customerId);
            ps.setInt(4, appId);
            ps.setObject(5, TimeConversion.convertToUTC(startDateTime).minusMinutes(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            ps.setObject(6, TimeConversion.convertToUTC(endDateTime).plusMinutes(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            ps.setInt(7, customerId);
            ps.setInt(8, appId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                JDBC.closeConnection();
                return true;
            }
        } catch (SQLException e) {
            JDBC.closeConnection();
            throw new RuntimeException(e);
        }
        JDBC.closeConnection();
        return false;
    }

    /**
     * Used to grab any upcoming appointments associated with the user logged in, in the next 15 minutes.
     */
    public static void getUpcomingAppointments() {
        try {
            JDBC.openConnection();
            String sql = "SELECT * FROM appointments" +
                    " WHERE Start BETWEEN ? AND ? AND User_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setObject(1, TimeConversion.convertToUTC(LocalDateTime.now()).minusMinutes(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            ps.setObject(2, TimeConversion.convertToUTC(LocalDateTime.now()).plusMinutes(16).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            ps.setInt(3, SchedulingApp.getUser().getUserId());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Hello " + SchedulingApp.getUser().getUsername() + "!\n\n You have an upcoming appointment:\n" +
                        "Appointment ID: " + rs.getInt("Appointment_ID") + "\nStart: " + TimeConversion.convertFromUTC(rs.getObject("Start",
                        LocalDateTime.class)).format(DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mma")));
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Hello " + SchedulingApp.getUser().getUsername() + "!\n\n You have no upcoming appointments in the " +
                        "next 15 minutes.");
                alert.show();
            }
            JDBC.closeConnection();
        } catch (SQLException e) {
            JDBC.closeConnection();
            throw new RuntimeException(e);
        }
    }

    /**
     * Used to get all appointments associated with a contact
     * @param contactId int of contact id being searched for associated appointments
     * @return returns all contact's associated appointments from the database in an ObservableList array of Appointment objects
     */
    public static ObservableList<Appointment> getAppointmentsByContact(int contactId) {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        try {
            JDBC.openConnection();
            String sql = "SELECT * FROM appointments AS a JOIN contacts AS ct ON a.Contact_ID = ct.Contact_ID" +
                          " JOIN customers AS c ON a.Customer_ID = c.Customer_ID" +
                          " JOIN first_level_divisions AS fd ON c.Division_ID = fd.Division_ID" +
                          " JOIN countries As cc ON fd.COUNTRY_ID = cc.Country_ID" +
                          " WHERE a.Contact_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, contactId);
            ResultSet rs = ps.executeQuery();
            DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy h:mma");
            while(rs.next()) {
                Appointment app = new Appointment(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Type"),
                        df.format(TimeConversion.convertFromUTC(rs.getObject("Start", LocalDateTime.class))),
                        df.format(TimeConversion.convertFromUTC(rs.getObject("End", LocalDateTime.class))),
                        TimeConversion.convertFromUTC(rs.getObject("Create_Date", LocalDateTime.class)),
                        rs.getString("Created_By"),
                        TimeConversion.convertFromUTC(rs.getObject("Last_Update", LocalDateTime.class)),
                        rs.getString("Last_Updated_By"),
                        new Customer(
                                rs.getInt("c.Customer_ID"),
                                rs.getString("c.Customer_Name"),
                                rs.getString("c.Address"),
                                rs.getString("c.Postal_Code"),
                                rs.getString("c.Phone"),
                                rs.getObject("c.Create_Date", LocalDateTime.class),
                                rs.getString("c.Created_By"),
                                rs.getObject("c.Last_Update", LocalDateTime.class),
                                rs.getString("c.Last_Updated_By"),
                                new FirstLvlDivision(
                                        rs.getInt("fd.Division_ID"),
                                        rs.getString("fd.Division"),
                                        rs.getObject("fd.Create_Date", LocalDateTime.class),
                                        rs.getString("fd.Created_By"),
                                        rs.getObject("fd.Last_Update", LocalDateTime.class),
                                        rs.getString("fd.Last_Updated_By"),
                                        rs.getInt("fd.COUNTRY_ID")
                                ),
                                new Country(
                                        rs.getInt("cc.Country_ID"),
                                        rs.getString("cc.Country"),
                                        rs.getObject("cc.Create_Date", LocalDateTime.class),
                                        rs.getString("cc.Created_By"),
                                        rs.getObject("cc.Last_Update", LocalDateTime.class),
                                        rs.getString("cc.Last_Updated_By")
                                )
                        ),
                        rs.getInt("User_ID"),
                        new Contact(
                                rs.getInt("ct.Contact_ID"),
                                rs.getString("ct.Contact_Name"),
                                rs.getString("ct.Email")
                        )
                );
                appointments.add(app);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return appointments;
    }

    /**
     * Used to get a total of customers by division (city)
     * @return returns a list of total customers by division from the database in an ObservableList array of Report objects
     */
    public static ObservableList<Report> getCustomersByLoc() {
        ObservableList<Report> reportList = FXCollections.observableArrayList();
        try {
            JDBC.openConnection();
            String sql = "SELECT Division, COUNT(*) AS Quantity FROM customers AS c JOIN first_level_divisions AS fd ON c.Division_ID = fd.Division_ID GROUP BY 1;";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                reportList.add(new Report(rs.getString(1), rs.getInt(2)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        JDBC.closeConnection();
        return reportList;
    }

    /**
     * Used to get a list of total appointments by type
     * @return returns a list of total appointments from the database in an ObservableList array of Report objects
     */
    public static ObservableList<Report> getAppointmentsByType() {
        ObservableList<Report> reportList = FXCollections.observableArrayList();
        try {
            JDBC.openConnection();
            String sql = "SELECT Type, COUNT(*) AS Quantity FROM appointments GROUP BY 1";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                reportList.add(new Report(rs.getString(1), rs.getInt(2)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        JDBC.closeConnection();
        return reportList;
    }

    /**
     * Used to get a list of total appointments by month
     * @return returns a list of total appointments by month from the database in an ObservableList array of Report objects
     */
    public static ObservableList<Report> getAppointmentsByMonth() {
        ObservableList<Report> reportList = FXCollections.observableArrayList();
        try {
            JDBC.openConnection();
            String sql = """
                    SELECT
                    CASE
                        WHEN EXTRACT(MONTH FROM Start) = 1 THEN 'January'
                        WHEN EXTRACT(MONTH FROM Start) = 2 THEN 'February'
                        WHEN EXTRACT(MONTH FROM Start) = 3 THEN 'March'
                        WHEN EXTRACT(MONTH FROM Start) = 4 THEN 'April'
                        WHEN EXTRACT(MONTH FROM Start) = 5 THEN 'May'
                        WHEN EXTRACT(MONTH FROM Start) = 6 THEN 'June'
                        WHEN EXTRACT(MONTH FROM Start) = 7 THEN 'July'
                        WHEN EXTRACT(MONTH FROM Start) = 8 THEN 'August'
                        WHEN EXTRACT(MONTH FROM Start) = 9 THEN 'September'
                        WHEN EXTRACT(MONTH FROM Start) = 10 THEN 'October'
                        WHEN EXTRACT(MONTH FROM Start) = 11 THEN 'November'
                        WHEN EXTRACT(MONTH FROM Start) = 12 THEN 'December'
                    END AS 'Month',
                    COUNT('Month') AS Quantity
                    FROM appointments
                    GROUP BY 1""";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                reportList.add(new Report(rs.getString(1), rs.getInt(2)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        JDBC.closeConnection();
        return reportList;
    }
}
