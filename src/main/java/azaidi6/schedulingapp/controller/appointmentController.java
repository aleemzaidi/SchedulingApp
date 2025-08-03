package azaidi6.schedulingapp.controller;

import azaidi6.schedulingapp.SchedulingApp;
import azaidi6.schedulingapp.dao.AppointmentDAO;
import azaidi6.schedulingapp.model.Appointment;
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
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class appointmentController implements Initializable {

    @FXML
    private Button addButton;

    @FXML
    private TableColumn<Appointment, String> appContactCol;

    @FXML
    private TableColumn<Appointment, Number> appCustomerIdCol;

    @FXML
    private TableColumn<Appointment, String> appDescriptionCol;

    @FXML
    private TableColumn<Appointment, LocalDateTime> appEndDateTimeCol;

    @FXML
    private TableColumn<Appointment, Integer> appIdCol;

    @FXML
    private TableColumn<Appointment, String> appLocationCol;

    @FXML
    private TableColumn<Appointment, LocalDateTime> appStartDateTimeCol;

    @FXML
    private TableColumn<Appointment, String> appTitleCol;

    @FXML
    private TableColumn<Appointment, String> appTypeCol;

    @FXML
    private TableColumn<Appointment, Integer> appUserIdCol;

    @FXML
    private Button backButton;

    @FXML
    private TableView<Appointment> appointmentsTable;

    @FXML
    private Button deleteButton;

    @FXML
    private RadioButton allAppointments;

    @FXML
    private RadioButton weekAppointments;

    @FXML
    private RadioButton monthAppointments;

    @FXML
    private ToggleGroup filter;

    @FXML
    private Button updateButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentsTable.setItems(AppointmentDAO.getAllAppointments());
        appIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        appDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        appLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        appTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        appStartDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        appEndDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        appContactCol.setCellValueFactory(cf -> cf.getValue().getContact().getContactName());
        appCustomerIdCol.setCellValueFactory(cf -> cf.getValue().getCustomer().getCustomerId());
        appUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));

    }

    /**
     * Updates view to add appointment view
     * @throws IOException throws input or output exception when error loading add appointments view occurs
     */
    public void onAddButtonClicked() throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(SchedulingApp.class.getResource("addAppointmentMenu.FXML")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) (addButton.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Used to update selected appointment in the appointments TableView from the database
     * @throws IOException throws input or output exception when error loading modify appointment view occurs
     */
    public void onUpdateButtonClicked() throws IOException {
        if(appointmentsTable.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        FXMLLoader modifyAppointmentsFXML = new FXMLLoader(Objects.requireNonNull(SchedulingApp.class.getResource("modifyAppointmentMenu.FXML")));
        Scene scene = new Scene(modifyAppointmentsFXML.load());
        Stage stage = (Stage) (updateButton.getScene().getWindow());
        stage.setScene(scene);
        stage.show();

        modifyAppointmentController mac = modifyAppointmentsFXML.getController();
        mac.sendAppointment(appointmentsTable.getSelectionModel().getSelectedItem());

    }

    /**
     * Used to delete selected appointment in the appointments TableView from the databse
     */
    public void onDeleteButtonClicked() {
        if(appointmentsTable.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete selected appointment?");
        alert.setTitle("Delete Appointment");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            Appointment app = appointmentsTable.getSelectionModel().getSelectedItem();
            alert = new Alert(Alert.AlertType.INFORMATION, "Appointment ID: " + app.getAppointmentId() + "\nAppointment Type: " + app.getType() +
                    "\n\nAppointment has been deleted.");
            AppointmentDAO.deleteAppointments(app.getAppointmentId());
            alert.show();
        }

        appointmentsTable.setItems(AppointmentDAO.getAllAppointments());

    }

    /**
     * Used to update the appointments TableView with the corresponding appointments
     */
    public void filterChange() {
        if(filter.getSelectedToggle() == allAppointments) {
            appointmentsTable.setItems(AppointmentDAO.getAllAppointments());
        } else if(filter.getSelectedToggle() == weekAppointments) {
            appointmentsTable.setItems(AppointmentDAO.getWeekAppointments());
        } else if(filter.getSelectedToggle() == monthAppointments) {
            appointmentsTable.setItems(AppointmentDAO.getMonthAppointments());
        }
    }

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
}
