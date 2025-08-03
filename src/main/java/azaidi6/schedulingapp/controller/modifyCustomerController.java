package azaidi6.schedulingapp.controller;

import azaidi6.schedulingapp.SchedulingApp;
import azaidi6.schedulingapp.dao.CountryDAO;
import azaidi6.schedulingapp.dao.CustomerDAO;
import azaidi6.schedulingapp.dao.FirstLvlDivisionDAO;
import azaidi6.schedulingapp.model.Country;
import azaidi6.schedulingapp.model.Customer;
import azaidi6.schedulingapp.model.FirstLvlDivision;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class modifyCustomerController implements Initializable {

    @FXML
    private Label modifyCustomerTitle;

    @FXML
    private TextField addressField;

    @FXML
    private Label addressLabel;

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<Country> countryComboBox;

    @FXML
    private ComboBox<FirstLvlDivision> divisionComboBox;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private Label phoneNumberLabel;

    @FXML
    private TextField postalCodeField;

    @FXML
    private Label postalCodeLabel;

    @FXML
    private Button saveButton;

    ResourceBundle languageBundle = ResourceBundle.getBundle("azaidi6.schedulingapp/language/lang");

    /**
     * Saves customer data to the database
     * @throws IOException throws input or output exception when error loading customers menu view occurs
     */
    public void onSaveButtonClicked() throws IOException {

        if(nameField.getText().isEmpty() ||
                addressField.getText().isEmpty() ||
                postalCodeField.getText().isEmpty() ||
                phoneNumberField.getText().isEmpty() ||
                divisionComboBox.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill out all information.");
            alert.show();
            return;
        }

        //add customer
        CustomerDAO.updateCustomer(Integer.parseInt(idField.getPromptText()), nameField.getText(), addressField.getText(), postalCodeField.getText(), phoneNumberField.getText(), divisionComboBox.getValue().getDivisionId());

        //go back to customer screen
        Parent parent = FXMLLoader.load(Objects.requireNonNull(SchedulingApp.class.getResource("customersMenu.FXML")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) (saveButton.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Cancels any updates to customer data in database
     * @throws IOException throws exception when error loading customers menu view occurs
     */
    public void onCancelButtonClicked() throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(SchedulingApp.class.getResource("customersMenu.FXML")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) (cancelButton.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modifyCustomerTitle.setText(languageBundle.getString("modifyCustomer"));
        saveButton.setText(languageBundle.getString("Save"));
        cancelButton.setText(languageBundle.getString("Cancel"));
        nameLabel.setText(languageBundle.getString("Name"));
        addressLabel.setText(languageBundle.getString("Address"));
        postalCodeLabel.setText(languageBundle.getString("PostalCode"));
        phoneNumberLabel.setText(languageBundle.getString("PhoneNumber"));

    }

    /**
     * Used to send Customer data from customers menu view to modify customer view
     * @param customer Customer data to send to modify customer view
     */
    public void sendCustomer(Customer customer) {

        idField.setPromptText(String.valueOf(customer.getCustomerId().get()));
        nameField.setText(customer.getName());
        addressField.setText(customer.getAddress());
        postalCodeField.setText(customer.getPostalCode());
        phoneNumberField.setText(customer.getPhone());
        countryComboBox.setItems(CountryDAO.getCountries());
        countryComboBox.setValue(customer.getCountry());
        divisionComboBox.setValue(customer.getFld());

    }

    /**
     * Used to update division (city) combo box based on selected country
     */
    public void firstDivisionUpdate() {
        Country ct = countryComboBox.getValue();
        divisionComboBox.setItems(FirstLvlDivisionDAO.getDivisions(ct.getCountryId()));
    }

}
