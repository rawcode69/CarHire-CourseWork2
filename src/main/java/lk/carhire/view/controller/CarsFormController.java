package lk.carhire.view.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import lk.carhire.controller.CarController;
import lk.carhire.dto.CarDto;

public class CarsFormController {


    public TextField catIdText;
    CarController carController;

    public void initialize() {
        carController = new CarController();
    }


    public TextField carIdText;
    public TextField carNumberText;
    public TextField carBrandText;
    public TextField carModelText;
    public TextField yearText;
    public TextField rateText;

    public Button updateButton;
    public Button deleteButton;
    public TableView carTable;
    public Button saveButton;

    public void saveButtonOnAction(ActionEvent actionEvent) {
        saveCars();

    }

    public void updateButtonAction(ActionEvent actionEvent) {
        updateCars();
    }

    private void updateCars() {

        CarDto carDto = new CarDto(
                Integer.valueOf(carIdText.getText()),
                carNumberText.getText(),
                carBrandText.getText(),
                carModelText.getText(),
                Integer.valueOf(yearText.getText()),
                Double.valueOf(rateText.getText()),
                Integer.valueOf(catIdText.getText()));
        try {
            carController.updateCars(carDto);
            new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            System.out.println(e.getMessage());
        }

    }

    private void saveCars() {

        CarDto carDto = new CarDto(
                carNumberText.getText(),
                carBrandText.getText(),
                carModelText.getText(),
                Integer.valueOf(yearText.getText()),
                Double.valueOf(rateText.getText()),
                Integer.valueOf(catIdText.getText()));
        try {
            Integer id = carController.saveCar(carDto);
            System.out.println(id);
            clearForm();
            if (id > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Car saved").show();
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Car not Saved").show();
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void getCar() throws Exception {
        Integer id = Integer.valueOf(carIdText.getText());
        CarDto carDto = carController.getCar(id);

        setForm(carDto);
    }

    private void setForm(CarDto carDto) {
        carNumberText.setText(carDto.getNumber());
        carModelText.setText(carDto.getModel());
        carBrandText.setText(carDto.getBrand());
        yearText.setText(String.valueOf(carDto.getYear()));
        rateText.setText(String.valueOf(carDto.getRate()));
        catIdText.setText(String.valueOf(carDto.getCatId()));
    }

    private void clearForm() {
        carNumberText.setText("");
        carModelText.setText("");
        carBrandText.setText("");
        yearText.setText("");
        rateText.setText("");
        catIdText.setText("");
    }

    private void deleteCar() throws Exception {

        CarDto carDto = new CarDto(
                Integer.valueOf(carIdText.getText()),
                carNumberText.getText(),
                carBrandText.getText(),
                carModelText.getText(),
                Integer.valueOf(yearText.getText()),
                Double.valueOf(rateText.getText()),
                Integer.valueOf(catIdText.getText()));
        try {
            carController.deleteCar(carDto);
            new Alert(Alert.AlertType.CONFIRMATION,"Car Deleted Successfully").show();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    public void searchButtonAction(ActionEvent actionEvent) throws Exception {
        getCar();
    }

    public void carIdTextAction(ActionEvent actionEvent) throws Exception {
        getCar();
    }

    public void deleteButtonAction(ActionEvent actionEvent) throws Exception {
        deleteCar();
    }


}
