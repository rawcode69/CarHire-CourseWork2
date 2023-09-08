package lk.carhire.view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashBoardController {


    public AnchorPane formAnchorPane;

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

        Parent root = FXMLLoader.load(this.getClass().getResource("/view-layer/user.form_fxml"));

        this.formAnchorPane.getChildren().clear();
        this.formAnchorPane.getChildren().add(root);
    }
}
