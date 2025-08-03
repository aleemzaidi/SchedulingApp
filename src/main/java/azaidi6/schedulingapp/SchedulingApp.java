package azaidi6.schedulingapp;

import azaidi6.schedulingapp.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Scheduling Application
 * @author Aleem Zaidi
 *
 * Javadoc location: SchedulingApp/javadocs
 */
public class SchedulingApp extends Application {

    private static User user;
    private Stage stage;

    /**
     *
     * @param stage sets the main stage of the application which is used to switch to different views.
     * @throws IOException If an input or output exception occurs when initializing the application.
     */
    @Override
    public void start(Stage stage) throws IOException {
        ResourceBundle languageBundle = ResourceBundle.getBundle("azaidi6.schedulingapp/language/lang");
        this.stage = stage;
        stage.setTitle(languageBundle.getString("SchedulingApp"));

        initSchedulingApp();
    }

    /**
     *
     * @throws IOException If an input or output exception occurs when loading the login menu
     */
    public void initSchedulingApp() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SchedulingApp.class.getResource("loginMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 250);
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();

    }

    /**
     *
     * @return returns the User object logged into the application. Used to get person logged into the application.
     */
    public static User getUser() {
        return user;
    }

    /**
     *
     * @param user set a new User object which is later used to get person logged into the application.
     */
    public static void setUser(User user) {
        SchedulingApp.user = user;
    }

    /**
     *
     * Main method used to launch the application
     */
    public static void main(String[] args) {
        launch();
    }
}