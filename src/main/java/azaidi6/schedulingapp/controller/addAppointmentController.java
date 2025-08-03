package azaidi6.schedulingapp.controller;

import azaidi6.schedulingapp.SchedulingApp;
import azaidi6.schedulingapp.dao.AppointmentDAO;
import azaidi6.schedulingapp.dao.ContactDAO;
import azaidi6.schedulingapp.dao.CustomerDAO;
import azaidi6.schedulingapp.helper.TimeConversion;
import azaidi6.schedulingapp.model.Contact;
import azaidi6.schedulingapp.model.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.converter.LocalTimeStringConverter;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;

public class addAppointmentController implements Initializable {

    @FXML
    private Label addCustomerTitle;

    @FXML
    private ComboBox<Contact> appContactComboBox;

    @FXML
    private ComboBox<Customer> appCustomerComboBox;

    @FXML
    private Label appCustomerIdLabel;

    @FXML
    private TextField appDescriptionField;

    @FXML
    private Label appDescriptionLabel;

    @FXML
    private TextField appLocationField;

    @FXML
    private Label appLocationLabel;

    @FXML
    private TextField appTitleField;

    @FXML
    private Label appTitleLabel;

    @FXML
    private TextField appUserIdField;

    @FXML
    private Label appUserIdLabel;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField appTypeField;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private Spinner<LocalTime> endTimeSpinner;

    @FXML
    private Button saveButton;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private Spinner<LocalTime> startTimeSpinner;

    /**
     * Adds new appointment into the database and loads appointments menu view
     * @throws IOException throws input or output exception when error loading appointments menu view occurs
     */
    public void onSaveButtonClicked() throws IOException {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mma");
        //grab start and end time in localdatetime variable
        LocalDateTime start_date_time = LocalDateTime.of(startDatePicker.getValue(), startTimeSpinner.getValue());
        LocalDateTime end_date_time = LocalDateTime.of(endDatePicker.getValue(), endTimeSpinner.getValue());

        if(appTitleField.getText().isEmpty() ||
            appDescriptionField.getText().isEmpty() ||
            appLocationField.getText().isEmpty() ||
            appTypeField.getText().isEmpty() ||
            appContactComboBox.getValue() == null ||
            appCustomerComboBox.getValue() == null ||
            startDatePicker.getValue() == null ||
            startTimeSpinner.getValue() == null ||
            endDatePicker.getValue() == null ||
            endTimeSpinner.getValue() == null ||
            appUserIdField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill out all information!");
            alert.show();
            return;

        //check if contact already has schedule appointment during those times
        } else if (AppointmentDAO.checkContactBusy(start_date_time, end_date_time, appContactComboBox.getValue().getContactId())) {
            Alert alert = new Alert(Alert.AlertType.ERROR, appContactComboBox.getValue() + " is not available during requested appointment times.");
            alert.show();
            return;

        //check if customer already has scheduled appointment during time
        } else if(AppointmentDAO.checkCustomerBusy(start_date_time, end_date_time, appCustomerComboBox.getValue().getCustomerId().get())) {
            Alert alert = new Alert(Alert.AlertType.ERROR, appCustomerComboBox.getValue() + " is already scheduled during requested appointment times.");
            alert.show();
            return;

        //check for scheduling appointments in past
        } else if(start_date_time.isBefore(LocalDateTime.now())) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Cannot schedule appointments in the past.");
            alert.show();
            return;

        //add checks for business hours
        } else if(TimeConversion.convertToET(start_date_time).toLocalTime().isBefore(LocalTime.of(8, 0, 0)) ||
                TimeConversion.convertToET(start_date_time).toLocalTime().isAfter(LocalTime.of(22,0,0))) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Cannot schedule start of appointment outside of business hours: 8AM - 10PM ET. Mon-Fri\n\n" +
                    "You selected : " + df.format(TimeConversion.convertToET(start_date_time)));
            alert.show();
            return;
        } else if(TimeConversion.convertToET(end_date_time).toLocalTime().isBefore(LocalTime.of(8, 0, 0)) ||
                TimeConversion.convertToET(end_date_time).toLocalTime().isAfter(LocalTime.of(22,0,0))) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Cannot schedule end of appointment outside of business hours: 8AM - 10PM ET. Mon-Fri.\n\n" +
                    "You selected : " + df.format(TimeConversion.convertToET(end_date_time)));
            alert.show();
            return;

        //check if appointment date is on weekend.
        } else if (TimeConversion.convertToET(start_date_time).getDayOfWeek() == DayOfWeek.SATURDAY ||
                TimeConversion.convertToET(start_date_time).getDayOfWeek() == DayOfWeek.SUNDAY ||
                TimeConversion.convertToET(end_date_time).getDayOfWeek() == DayOfWeek.SATURDAY ||
                TimeConversion.convertToET(end_date_time).getDayOfWeek() == DayOfWeek.SUNDAY) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Cannot schedule appointment outside of business hours: 8AM - 10PM ET. Mon-Fri.");
            alert.show();
            return;

        //check for end datetime before start datetime
        } else if(start_date_time.isAfter(end_date_time)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Cannot schedule end of appointment before start of appointment.");
            alert.show();
            return;

        }

        //add appointment
        AppointmentDAO.addAppointment(
                appTitleField.getText(),
                appDescriptionField.getText(),
                appLocationField.getText(),
                appTypeField.getText(),
                start_date_time,
                end_date_time,
                appCustomerComboBox.getValue().getCustomerId().get(),
                Integer.parseInt(appUserIdField.getText()),
                appContactComboBox.getValue().getContactId()
        );

        //go back to customer screen
        Parent parent = FXMLLoader.load(Objects.requireNonNull(SchedulingApp.class.getResource("appointmentsMenu.FXML")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) (saveButton.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Cancels any appointment adds to database and loads appointments menu view
     * @throws IOException throws input or output exception when error loading appointments menu view occurs
     */
    public void onCancelButtonClicked() throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(SchedulingApp.class.getResource("appointmentsMenu.FXML")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) (cancelButton.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * LAMBDA EXPRESSION: LINES 203-255 to set behavior of spinner control for start time and end time
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        appContactComboBox.setItems(ContactDAO.getContacts());
        appCustomerComboBox.setItems(CustomerDAO.getCustomers());
        DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm");
        SpinnerValueFactory<LocalTime> startTime = new SpinnerValueFactory<>() {
            {
                setConverter(new LocalTimeStringConverter(df,null));
            }
            @Override
            public void decrement(int steps) {
                int mod = LocalTime.now().getMinute() % 15;
                if (getValue() == null)
                    setValue(LocalTime.now().plusMinutes(mod < 8 ? -mod : (15-mod)));
                else {
                    LocalTime time = (LocalTime) getValue();
                    setValue(time.minusMinutes(steps+14));
                }
            }

            @Override
            public void increment(int steps) {
                int mod = LocalTime.now().getMinute() % 15;
                if (this.getValue() == null)
                    setValue(LocalTime.now().plusMinutes(mod < 8 ? -mod : (15-mod)));
                else {
                    LocalTime time = (LocalTime) getValue();
                    setValue(time.plusMinutes(steps+14));
                }
            }
        };

        SpinnerValueFactory<LocalTime> endTime = new SpinnerValueFactory<>() {
            {
                setConverter(new LocalTimeStringConverter(df,null));
            }
            @Override
            public void decrement(int steps) {
                int mod = LocalTime.now().getMinute() % 15;
                if (getValue() == null)
                    setValue(LocalTime.now().plusMinutes(mod < 8 ? -mod : (15-mod)));
                else {
                    LocalTime time = (LocalTime) getValue();
                    setValue(time.minusMinutes(steps+14));
                }
            }

            @Override
            public void increment(int steps) {
                int mod = LocalTime.now().getMinute() % 15;
                if (this.getValue() == null)
                    setValue(LocalTime.now().plusMinutes(mod < 8 ? -mod : (15-mod)));
                else {
                    LocalTime time = (LocalTime) getValue();
                    setValue(time.plusMinutes(steps+14));
                }
            }
        };
        startTimeSpinner.setValueFactory(startTime);
        endTimeSpinner.setValueFactory(endTime);

    }
}
