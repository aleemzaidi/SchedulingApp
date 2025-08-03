package azaidi6.schedulingapp.model;

import javafx.beans.property.SimpleStringProperty;

public class Contact {

    private int contactId;
    private String email;
    private final SimpleStringProperty contactName = new SimpleStringProperty();

    /**
     * Constructor of Contact object
     * @param contactId int of contact id
     * @param name string of contact name
     * @param email string of contact email address
     */
    public Contact(int contactId, String name, String email) {
        this.contactId = contactId;
        this.contactName.set(name);
        this.email = email;
    }

    /**
     * Used to get contact id
     * @return returns int of contact id
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Used to set contact id
     * @param contactId int of contact id
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * Used to get contact name for display in TableView
     * @return returns string of contact name
     */
    public SimpleStringProperty getContactName() {
        return contactName;
    }

    /**
     * Used to set contact name
     * @param name string of contact name
     */
    public void setContactName(String name) {
        this.contactName.set(name);
    }

    /**
     * Used to get contact's email address
     * @return returns string of contact's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Used to set contact's email address
     * @param email string of contact's email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Used to set default display of Contact object
     * @return returns string of contact name
     */
    @Override
    public String toString() {
        return contactName.get();
    }
}
