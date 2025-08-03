package azaidi6.schedulingapp.model;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;

public class FirstLvlDivision {

    private int divisionId;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private int countryId;
    private final SimpleStringProperty div = new SimpleStringProperty();

    /**
     * Constructor of FirstLvlDivision object
     * @param divisionId int of division id
     * @param division string of division
     * @param createDate LocalDateTime of division creation date
     * @param createdBy string of who created division
     * @param lastUpdate LocalDateTime of division last update date
     * @param lastUpdatedBy string of who last updated division
     * @param countryId int of country associated with division
     */
    public FirstLvlDivision(int divisionId, String division, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy, int countryId) {
        this.divisionId = divisionId;
        this.div.set(division);
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.countryId = countryId;
    }

    /**
     * Used to get division id
     * @return returns int of division id
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * Used to set division id
     * @param divisionId int of division id to set
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * Used to get division
     * @return string of division
     */
    public String getDivision() {
        return div.get();
    }

    /**
     * Used to set division
     * @param division string of division to set
     */
    public void setDivision(String division) {
        this.div.set(division);
    }

    /**
     * Used to get creation date of division
     * @return returns LocalDateTime of division creation date
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Used to set division creation date
     * @param createDate LocalDateTime of division creation date
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * Used to get who created the division
     * @return returns string of who created the division
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Used to set who created the division
     * @param createdBy string of who created the division
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Used to get when the division was last updated
     * @return returns LocalDateTime of when the division was last updated
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Used to set when the division was last updated
     * @param lastUpdate LocalDateTime of when the division was last updated
     */
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Used to get who last updated the division
     * @return returns string of who last updated the division
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Used to set who last updated the division
     * @param lastUpdatedBy string of who last updated the division
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Used to get the country id associated with division
     * @return returns int of country id associated with division
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Used to set the country id associated with division
     * @param countryId int of country id to set
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * Used to get the string of division in TableView
     * @return string of division
     */
    public SimpleStringProperty getDiv() {
        return div;
    }

    /**
     * The default display value of FirstLvlDivision object
     * @return string of division
     */
    public String toString() {
        return div.get();
    }
}
