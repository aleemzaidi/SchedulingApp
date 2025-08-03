package azaidi6.schedulingapp.controller;

import azaidi6.schedulingapp.SchedulingApp;
import azaidi6.schedulingapp.dao.AppointmentDAO;
import azaidi6.schedulingapp.dao.ContactDAO;
import azaidi6.schedulingapp.model.Appointment;
import azaidi6.schedulingapp.model.Contact;
import azaidi6.schedulingapp.model.Report;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class reportsController implements Initializable {

    @FXML
    private TableColumn<Appointment, Number> appCustomerIdCol;

    @FXML
    private TableColumn<Appointment, String> appDescriptionCol;

    @FXML
    private TableColumn<Appointment, String> appEndDateTimeCol;

    @FXML
    private TableColumn<Appointment, String> appIdCol;

    @FXML
    private TableColumn<Report, String> appLocCol;

    @FXML
    private TableColumn<Report, Integer> appLocQuantityCol;

    @FXML
    private TableView<Report> customersTable;

    @FXML
    private TableColumn<Report, String> appMonthCol;

    @FXML
    private TableColumn<Report, Integer> appMonthQtyCol;

    @FXML
    private TableView<Report> appMonthTable;

    @FXML
    private TableColumn<Appointment, String> appStartDateTimeCol;

    @FXML
    private TableColumn<Appointment, String> appTitleCol;

    @FXML
    private TableColumn<Report, String> appTypeCol;

    @FXML
    private TableColumn<Report, Integer> appTypeQtyCol;

    @FXML
    private TableView<Report> appTypeTable;

    @FXML
    private ComboBox<Contact> contactComboBox;

    @FXML
    private TableView<Appointment> contactScheduleTable;

    @FXML
    private TableColumn<Appointment, String> csAppTypeCol;

    /**
     * Updates contactScheduleTable with all of selected contact's appointments in the database
     */
    public void contactSelect() {
        contactScheduleTable.setItems(AppointmentDAO.getAppointmentsByContact(contactComboBox.getValue().getContactId()));
        contactScheduleTable.setPlaceholder(new Label("No data found for selected contact."));
    }

    /**
     * Used to go back to the main menu view
     * @param e grabs the button that triggered the event
     * @throws IOException throws input or output exception when error loading main menu view occurs
     */
    public void onBackClicked(MouseEvent e) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(SchedulingApp.class.getResource("mainMenu.FXML")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Button)e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appTypeTable.setItems(AppointmentDAO.getAppointmentsByType());
        appTypeCol.setCellValueFactory(new PropertyValueFactory<>("reportValue"));
        appTypeQtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        appMonthTable.setItems(AppointmentDAO.getAppointmentsByMonth());
        appMonthCol.setCellValueFactory(new PropertyValueFactory<>("reportValue"));
        appMonthQtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        customersTable.setItems(AppointmentDAO.getCustomersByLoc());
        appLocCol.setCellValueFactory(new PropertyValueFactory<>("reportValue"));
        appLocQuantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        contactComboBox.setItems(ContactDAO.getContacts());
        contactScheduleTable.setPlaceholder(new Label("Please select a contact"));
        appIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        csAppTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        appDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        appStartDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        appEndDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        appCustomerIdCol.setCellValueFactory(cf -> cf.getValue().getCustomer().getCustomerId());

    }
}
