package azaidi6.schedulingapp.model;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;

public class Country {

    private int countryId;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private final SimpleStringProperty countryName = new SimpleStringProperty();

    /**
     * Constructor of Country object
     * @param countryId int of country id
     * @param country string of country
     * @param createDate LocalDateTime of country creation date
     * @param createdBy string of who created country
     * @param lastUpdate LocalDateTime of country last update date
     * @param lastUpdatedBy string of who last updated country
     */
    public Country(int countryId, String country, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy) {
        this.countryId = countryId;
        this.countryName.set(country);
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy =lastUpdatedBy;
    }

    /**
     * Used to get country id
     * @return returns int of country id
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Used to set country id
     * @param countryId int of country id
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * Used to get country name
     * @return returns string of country name
     */
    public String getCountry() {
        return countryName.get();
    }

    /**
     * Used to set country name
     * @param country string of country name
     */
    public void setCountry(String country) {
        this.countryName.set(country);
    }

    /**
     * Used to get country creation date
     * @return returns LocalDateTime of country creation date
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Used to set country creation date
     * @param createDate LocalDateTime of country creation date
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * Used to get who created the country
     * @return returns string of who created the country
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Used to set who created the country
     * @param createdBy string of who created the country
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Used to get when country was last updated
     * @return returns LocalDateTime of when country was last updated
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Used to set when coutnry was last updated
     * @param lastUpdate LocalDateTime of when country was last updated
     */
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Used to get who last updated the country
     * @return returns string of who last updated the country
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Used to set who last updated the country
     * @param lastUpdatedBy string of who last updated the country
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Used to get string of country name for display on TableView
     * @return returns string of country name
     */
    public SimpleStringProperty getCountryName() {
        return countryName;
    }

    /**
     * Used to set default display of Country object
     * @return returns string of country name
     */
    public String toString() {
        return countryName.get();
    }
}
