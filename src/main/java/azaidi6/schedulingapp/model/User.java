package azaidi6.schedulingapp.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

public class User {

    private int userId;
    private String username;
    private String password;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;

    /**
     * Constructor for User object
     * @param userId int of user id
     * @param username string of username
     * @param password string of password
     * @param createDate LocalDateTime of user creation date
     * @param createdBy string of user that created new user
     * @param lastUpdate LocalDateTime of user last updated
     * @param lastUpdatedBy string of user that last updated the user
     */
    public User(int userId, String username, String password, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Constructor for User object
     * @param userId int of user id
     * @param username string of username
     */
    public User(int userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    /**
     * Used to get the user id
     * @return returns the int of the user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Used to set the user id
     * @param userId int of user id to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Used to get username
     * @return returns string of the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Used to set username
     * @param username string of username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Used to get the user password
     * @return returns string of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Used to set the user password
     * @param password string of password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Used to get the creation date of user
     * @return returns LocalDateTime of user creation date
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Used to set the creation date of user
     * @param createDate LocalDateTime of user creation date
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * Used to get who created the user
     * @return returns string of person that created the user
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Used to set who created the user
     * @param createdBy string of person that created the user
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Used to get the last time user was updated
     * @return returns LocalDateTime of last time user was updated
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Used to set the last time user was updated
     * @param lastUpdate LocalDateTime of when user was last updated
     */
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Used to get who last updated the user
     * @return returns string of who last updated the user
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Used to set who last updated the user
     * @param lastUpdatedBy string of who last updated the user
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

}
