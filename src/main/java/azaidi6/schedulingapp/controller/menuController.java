package azaidi6.schedulingapp.controller;

import azaidi6.schedulingapp.SchedulingApp;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class menuController implements Initializable {

    @FXML
    private Button appointmentsButton;

    @FXML
    private Button customersButton;

    @FXML
    private Button reportsButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button exitButton;

    @FXML
    private Label mainMenuLabel;

    ResourceBundle languageBundle = ResourceBundle.getBundle("azaidi6.schedulingapp/language/lang");

    /**
     * Used to exit the application when exit button is clicked
     */
    public void onExitClicked() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, languageBundle.getString("exitConfirmation"));
        alert.setTitle(languageBundle.getString("exitTitle"));
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            Platform.exit();
        }

    }

    /**
     * Updates view to appointments menu view when appointments button is clicked
     * @throws IOException throws input or output exception when error loading appointments menu view occurs
     */
    public void onAppointmentClicked() throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(SchedulingApp.class.getResource("appointmentsMenu.FXML")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) (appointmentsButton.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Updates view to reports view when reports button is clicked
     * @throws IOException throws input or output exception when error loading reports view occurs
     */
    public void onReportsClicked() throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(SchedulingApp.class.getResource("reportsMenu.FXML")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) (reportsButton.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Updates view to customers menu view when customers button is clicked
     * @throws IOException throws input or output exception when error loading customers menu view occurs
     */
    public void onCustomerClicked() throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(SchedulingApp.class.getResource("customersMenu.FXML")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) (customersButton.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainMenuLabel.setText(languageBundle.getString("Welcome") + ", " + SchedulingApp.getUser().getUsername() + "!");
        appointmentsButton.setText(languageBundle.getString("Appointments"));
        customersButton.setText(languageBundle.getString("Customers"));
        logoutButton.setText(languageBundle.getString("Logout"));
        exitButton.setText(languageBundle.getString("Exit"));
    }

    /**
     * Logs user out and loads login menu view when logout button is clicked
     * @throws IOException throws input or output exception when error loading login menu view occurs
     */
    public void onLogoutClicked() throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, languageBundle.getString("logoutConfirmation"));
        alert.setTitle(languageBundle.getString("Logout"));
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(SchedulingApp.class.getResource("loginMenu.FXML")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) (customersButton.getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        }


    }
}
