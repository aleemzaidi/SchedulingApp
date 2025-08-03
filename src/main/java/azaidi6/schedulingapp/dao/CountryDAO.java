package azaidi6.schedulingapp.dao;

import azaidi6.schedulingapp.helper.JDBC;
import azaidi6.schedulingapp.helper.TimeConversion;
import azaidi6.schedulingapp.model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CountryDAO {

    /**
     * Used to grab all countries in the database.
     * @return returns all countries from the database into an ObservableList array of Country objects
     */
    public static ObservableList<Country> getCountries() {

        ObservableList<Country> countries = FXCollections.observableArrayList();

        try {
            JDBC.openConnection();
            String sql = "SELECT * FROM countries";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Country ct = new Country(
                        rs.getInt("Country_ID"),
                        rs.getString("Country"),
                        TimeConversion.convertFromUTC(rs.getObject("Create_Date", LocalDateTime.class)),
                        rs.getString("Created_By"),
                        TimeConversion.convertFromUTC(rs.getObject("Last_Update", LocalDateTime.class)),
                        rs.getString("Last_Updated_By")
                );
                countries.add(ct);
            }
            JDBC.closeConnection();
            return countries;

        } catch (SQLException e) {
            JDBC.closeConnection();
            throw new RuntimeException(e);
        }

    }
}
