package azaidi6.schedulingapp.model;

import java.time.LocalDateTime;

public class Appointment {

    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private String start;
    private String end;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private Customer customer;
    private int userId;
    private Contact contact;

    /**
     * Constructor for Appointment object
     * @param appointmentId int appointment id
     * @param title string appointment title
     * @param description string appointment description
     * @param location string appointment location
     * @param type string appointment type
     * @param start string appointment start date/time
     * @param end string appointment end date/time
     * @param createDate LocalDateTime appointment creation date
     * @param createdBy string who created the appointment
     * @param lastUpdate LocalDateTime appointment last update date
     * @param lastUpdatedBy string who last updated the appointment
     * @param customer Customer customer associated with appointment
     * @param userId int user id of user associated with appointment
     * @param contact Contact contact associated with appointment
     */
    public Appointment(int appointmentId, String title, String description, String location, String type, String start, String end, LocalDateTime createDate,
                       String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy, Customer customer, int userId, Contact contact) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customer = customer;
        this.userId = userId;
        this.contact = contact;
    }

    /**
     * Used to get appointment id
     * @return returns int of appointment id
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * Used to set appointment id
     * @param appointmentId int of appointment id
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * Used to get appointment title
     * @return returns string of appointment title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Used to set appointment title
     * @param title string of appointment title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Used to get appointment description
     * @return returns string of appointment description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Used to set appointment description
     * @param description string of appointment description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Used to get appointment location
     * @return returns string of appointment location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Used to set appointment location
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Used to get appointment type
     * @return returns string of appointment type
     */
    public String getType() {
        return type;
    }

    /**
     * Used to set appointment type
     * @param type string of appointment type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Used to get start of appointment
     * @return returns string of appointment start date/time in 'MM-dd-yyyy h:mma' format
     */
    public String getStart() {
        return start;
    }

    /**
     * Used to set start of appointment
     * @param start string of appointment start date/time
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * Used to get end of appointment
     * @return returns string of appointment end date/time in 'MM-dd-yyyy h:mma' format
     */
    public String getEnd() {
        return end;
    }

    /**
     * Used to set end of appointment
     * @param end string of appointment end date/time
     */
    public void setEnd(String end) {
        this.end = end;
    }

    /**
     * Used to get appointment creation date
     * @return returns LocalDateTime of appointment creation date
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Used to set appointment creation date
     * @param createDate LocalDateTime of appointment creation date
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * Used to get who created the appointment
     * @return returns string of who created the appointment
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Used to set who created the appointment
     * @param createdBy string of who created the appointment
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Used to get when appointment was last updated
     * @return returns LocalDateTime of when appointment was last updated
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Used to set when appointment was last updated
     * @param lastUpdate LocalDateTime of when appointment was last updated
     */
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Used to get who last updated the appointment
     * @return returns string of who last updated the appointment
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Used to set who last updated the appointment
     * @param lastUpdatedBy string of who last updated the appointment
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Used to get customer associated with appointment
     * @return returns Customer object of customer associated with appointment
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Used to set customer associated with appointment
     * @param customer Customer object of customer associated wth appointment
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Used to get user id of user associated with appointment
     * @return returns int of user id associated with appointment
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Used to set user id of user associated with appointment
     * @param userId int of user id associated with appointment
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Used to get Contact object of contact associated with appointment
     * @return returns Contact object of contact associated with appointment
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * Used to set Contact object of contact associated with appointment
     * @param contact Contact object of contact associated with appointment
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
