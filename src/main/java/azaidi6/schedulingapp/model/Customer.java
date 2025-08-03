package azaidi6.schedulingapp.model;

import azaidi6.schedulingapp.dao.CustomerDAO;
import com.mysql.cj.conf.StringProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

public class Customer {

    private final SimpleIntegerProperty customerId = new SimpleIntegerProperty();
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private FirstLvlDivision fld;
    private Country country;

    //public ObservableList<Customer> customers = CustomerDAO.getCustomers();

    /**
     * Constructor of Customer object
     * @param customerId int of customer id
     * @param name string of customer name
     * @param address string of customer's address
     * @param postalCode string of customer's postal code
     * @param phone string of customer's phone number
     * @param createDate LocalDateTime of customer creation date
     * @param createdBy string of who created customer
     * @param lastUpdate LocalDateTime of customer last update date
     * @param lastUpdatedBy string of who last updated customer
     * @param fld FirstLvlDivision associated with customer
     * @param country Country associated with FirstLvlDivision associated with customer
     */
    public Customer(int customerId, String name, String address, String postalCode, String phone, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate,
                    String lastUpdatedBy, FirstLvlDivision fld, Country country) {
        this.customerId.set(customerId);
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.fld = fld;
        this.country = country;
    }

    /**
     * Used to get the int of customer to display in TableView
     * @return returns int of customer id
     */
    public SimpleIntegerProperty getCustomerId() {
        return customerId;
    }

    /**
     * Used to set customer id
     * @param customerId int of customer id to set
     */
    public void setCustomerId(int customerId) {
        this.customerId.set(customerId);
    }

    /**
     * Used to get customer name
     * @return returns string of customer name
     */
    public String getName() {
        return name;
    }

    /**
     * Used to set customer name
     * @param name string of customer name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Used to get customer's address
     * @return returns string of customer's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Used to set customer's address
     * @param address string of customer's address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Used to get customer's postal code
     * @return returns string of customer's postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Used to set customer's postal code
     * @param postalCode string of customer's postal code to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Used to get customer's phone number
     * @return returns string of customer's phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Used to set customer's phone number
     * @param phone string of customer's phone number to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Used to get customer creation date
     * @return returns LocalDateTime of customer creation date
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Used to set customer creation date
     * @param createDate LocalDateTime of customer creation date
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * Used to get who created the customer
     * @return returns string of who created the customer
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Used to set who created the customer
     * @param createdBy string of who created the customer
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Used to get when customer was last updated
     * @return returns LocalDateTime of when customer was last updated
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Used to set when customer was last updated
     * @param lastUpdate LocalDateTime of when customer was last updated
     */
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Used to get who last updated the customer
     * @return returns string of who last updated the customer
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Used to set who last updated the customer
     * @param lastUpdatedBy string of who last updated the customer
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Used to get the FirstLvlDivision associated with customer
     * @return returns FirstLvlDivision associated with customer
     */
    public FirstLvlDivision getFld() {
        return fld;
    }

    /**
     * Used to set the FirstLvlDivision associated with customer
     * @param fld FirstLvlDivision assocaited with customer
     */
    public void setFld(FirstLvlDivision fld) {
        this.fld = fld;
    }

    /**
     * Used to get Country assocaited with FirstLvlDivision associated with customer
     * @return returns Country associated with FirstLvlDivision associated with customer
     */
    public Country getCountry() {
        return country;
    }

    /**
     * Used to set Country associated with FirstLvlDivision associated with customer
     * @param country Country associated with FirstLvlDivision associated with customer
     */
    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     * Used to set default view of Customer object
     * @return returns string of customer name
     */
    public String toString() {
        return name;
    }
}
