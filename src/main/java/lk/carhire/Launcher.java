package lk.carhire;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
       // launchDashboard(stage);
        launchLogin(stage);
    }

    public void launchLogin(Stage stage) throws IOException {

        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view-layer/login_form.fxml"));

        Scene scene = new Scene(rootNode);

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("CarHire-Login");

        stage.show();

    }

    public void launchDashboard(Stage stage) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view-layer/dashboard.fxml"));

        Scene scene = new Scene(rootNode);

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("CarHire-Dashboard");

        stage.show();
    }


}
