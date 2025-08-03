package azaidi6.schedulingapp.controller;

import azaidi6.schedulingapp.SchedulingApp;
import azaidi6.schedulingapp.dao.AppointmentDAO;
import azaidi6.schedulingapp.dao.CustomerDAO;
import azaidi6.schedulingapp.model.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class customerController implements Initializable {

    @FXML
    private Button addButton;

    @FXML
    private Button backButton;

    @FXML
    private TableView<Customer> customersTable;

    @FXML
    private TableColumn<Customer, Number> customerIdCol;

    @FXML
    private TableColumn<Customer, String> customerNameCol;

    @FXML
    private TableColumn<Customer, String> customerAddressCol;

    @FXML
    private TableColumn<Customer, String> customerPostalCodeCol;

    @FXML
    private TableColumn<Customer, String> customerPhoneCol;

    @FXML
    private TableColumn<Customer, String> customerDivisionCol;

    @FXML
    private TableColumn<Customer, String> customerCountryCol;

    @FXML
    private Button updateButton;

    private final ResourceBundle languageBundle = ResourceBundle.getBundle("azaidi6.schedulingapp/language/lang");

    /**
     * Updates view to main menu view
     * @throws IOException throws input or output exception when error loading main menu view occurs
     */
    public void onBackButtonClicked() throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(SchedulingApp.class.getResource("mainMenu.FXML")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) (backButton.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Deletes selected customer in the customers TableView from the database
     */
    public void onDeleteButtonClicked() {
        if(customersTable.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, languageBundle.getString("deleteConfirmation"));
        alert.setTitle(languageBundle.getString("deleteTitle"));
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            AppointmentDAO.deleteAppointments(customersTable.getSelectionModel().getSelectedItem().getCustomerId().get());
            CustomerDAO.deleteCustomer(customersTable.getSelectionModel().getSelectedItem().getCustomerId().get());
            alert = new Alert(Alert.AlertType.INFORMATION, languageBundle.getString("customerDeleted"));
            alert.show();
        }

        customersTable.setItems(CustomerDAO.getCustomers());
    }

    /**
     * Used to update selected customer in the customers TableView from the database
     * @throws IOException throws input or output exception when error loading modify customer view occurs
     */
    public void onModifyButtonClicked() throws IOException {
        if(customersTable.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        FXMLLoader modifyCustomerFXML = new FXMLLoader(Objects.requireNonNull(SchedulingApp.class.getResource("modifyCustomerMenu.FXML")));
        Scene scene = new Scene(modifyCustomerFXML.load());
        Stage stage = (Stage) (updateButton.getScene().getWindow());
        stage.setScene(scene);

        modifyCustomerController mcc = modifyCustomerFXML.getController();
        mcc.sendCustomer(customersTable.getSelectionModel().getSelectedItem());

        stage.show();
    }

    /**
     * Used to add new customers into the database
     * @throws IOException throws input or output exception when error loading add customer view occurs
     */
    public void onAddButtonClicked() throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(SchedulingApp.class.getResource("addCustomerMenu.FXML")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) (addButton.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customersTable.setItems(CustomerDAO.getCustomers());
        customerIdCol.setCellValueFactory(cf -> cf.getValue().getCustomerId());
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerPostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        customerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        customerDivisionCol.setCellValueFactory(cf -> cf.getValue().getFld().getDiv());
        customerCountryCol.setCellValueFactory(cf -> cf.getValue().getCountry().getCountryName());
    }
}
