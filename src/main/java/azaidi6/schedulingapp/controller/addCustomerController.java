package azaidi6.schedulingapp.controller;

import azaidi6.schedulingapp.SchedulingApp;
import azaidi6.schedulingapp.dao.CountryDAO;
import azaidi6.schedulingapp.dao.CustomerDAO;
import azaidi6.schedulingapp.model.Country;
import azaidi6.schedulingapp.model.FirstLvlDivision;
import azaidi6.schedulingapp.dao.FirstLvlDivisionDAO;
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

public class addCustomerController implements Initializable {

    @FXML
    private Label addCustomerTitle;

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
     * adds new customer to the database and loads customer menu view
     * @throws IOException throws input or output exception when error loading customer menu view occurs
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
        CustomerDAO.addCustomer(nameField.getText(), addressField.getText(), postalCodeField.getText(), phoneNumberField.getText(), divisionComboBox.getValue().getDivisionId());

        //go back to customer screen
        Parent parent = FXMLLoader.load(Objects.requireNonNull(SchedulingApp.class.getResource("customersMenu.FXML")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) (saveButton.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Updates view to customers menu view
     * @throws IOException throws input or output exception when error loading customers menu view occurs
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
        addCustomerTitle.setText(languageBundle.getString("addCustomer"));
        saveButton.setText(languageBundle.getString("Save"));
        cancelButton.setText(languageBundle.getString("Cancel"));
        nameLabel.setText(languageBundle.getString("Name"));
        addressLabel.setText(languageBundle.getString("Address"));
        postalCodeLabel.setText(languageBundle.getString("PostalCode"));
        phoneNumberLabel.setText(languageBundle.getString("PhoneNumber"));

        countryComboBox.setItems(CountryDAO.getCountries());

    }

    /**
     * Used to update the division combo box based on selected country
     */
    public void firstDivisionUpdate() {
        Country ct = countryComboBox.getValue();
        divisionComboBox.setItems(FirstLvlDivisionDAO.getDivisions(ct.getCountryId()));
    }
}
