package lk.carhire.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DashBoardController {


    public AnchorPane formAnchorPane;
    public Label dateLabel;

    public void initialize(){
        currentDate();
    }

    public void categoryButtonOnAction(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/view-layer/category-form.fxml"));

        this.formAnchorPane.getChildren().clear();
        this.formAnchorPane.getChildren().add(root);

    }

    public void carsButtonOnAction(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/view-layer/cars_form.fxml"));

        this.formAnchorPane.getChildren().clear();
        this.formAnchorPane.getChildren().add(root);
    }

    public void customerButtonOnAction(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/view-layer/customer_form.fxml"));

        this.formAnchorPane.getChildren().clear();
        this.formAnchorPane.getChildren().add(root);
    }

    public void rentButtonOnAction(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/view-layer/rent_form.fxml"));

        this.formAnchorPane.getChildren().clear();
        this.formAnchorPane.getChildren().add(root);
    }

    public void userButtonOnAction(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/view-layer/user_form.fxml"));

        this.formAnchorPane.getChildren().clear();
        this.formAnchorPane.getChildren().add(root);
    }

    public void lgoutButtonOnAction(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/view-layer/login_form.fxml"));

        Scene scene = new Scene(root);

        Stage stage = (Stage) formAnchorPane.getScene().getWindow();

        stage.setScene(scene);
        stage.centerOnScreen();
    }
    private void currentDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd - MMM - yyyy");
        String dateNow = sdf.format(new Date());
        dateLabel.setText(dateNow);
    }

    public void ongoingRentButtonOnAction(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/view-layer/ongoingRent_form.fxml"));

        this.formAnchorPane.getChildren().clear();
        this.formAnchorPane.getChildren().add(root);

    }
}
