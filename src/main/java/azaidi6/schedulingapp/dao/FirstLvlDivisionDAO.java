package azaidi6.schedulingapp.dao;

import azaidi6.schedulingapp.helper.JDBC;
import azaidi6.schedulingapp.helper.TimeConversion;
import azaidi6.schedulingapp.model.FirstLvlDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class FirstLvlDivisionDAO {

    /**
     * Used to grab all divisions associated with the given country id.
     * @param countryId id of the country in the database, used to grab all divisions associated with that country id
     * @return returns all divisions associated with given country id from the database into an ObservableList array of FirstLvlDivision objects
     */
    public static ObservableList<FirstLvlDivision> getDivisions(int countryId) {
        ObservableList<FirstLvlDivision> fdList = FXCollections.observableArrayList();

        try {
            JDBC.openConnection();
            String sql = "SELECT * FROM first_level_divisions WHERE COUNTRY_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, countryId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                FirstLvlDivision fd = new FirstLvlDivision(
                        rs.getInt("Division_ID"),
                        rs.getString("Division"),
                        TimeConversion.convertFromUTC(rs.getObject("Create_Date", LocalDateTime.class)),
                        rs.getString("Created_By"),
                        TimeConversion.convertFromUTC(rs.getObject("Last_Update", LocalDateTime.class)),
                        rs.getString("Last_Updated_By"),
                        rs.getInt("COUNTRY_ID")
                );
                fdList.add(fd);
            }
            JDBC.closeConnection();
            return fdList;

        } catch (SQLException e) {
            JDBC.closeConnection();
            throw new RuntimeException(e);
        }

    }

}
