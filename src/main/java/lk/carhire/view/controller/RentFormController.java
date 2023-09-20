package lk.carhire.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.carhire.controller.CarController;
import lk.carhire.controller.CategoryController;
import lk.carhire.controller.CustomerController;
import lk.carhire.dto.CarDto;
import lk.carhire.dto.CategoryDto;
import lk.carhire.dto.CustomerDto;

import java.util.List;

public class RentFormController {

    public TextField idText;
    public Label customerInfoLabel;
    public ComboBox carCatCombo;
    public ComboBox carNumberCombo;
    public Label carInfoLabel;
    CustomerController customerController;
    CategoryController categoryController;
    CarController carController;

    public void initialize() throws Exception {
        customerController = new CustomerController();
        categoryController = new CategoryController();
        carController = new CarController();
        setCategories();

    }

    public void checkCutomerButtonAction(ActionEvent actionEvent) throws Exception {
        checkCustomer();
    }

    private void checkCustomer() throws Exception {

        Integer id = Integer.valueOf(idText.getText());
        customerInfoLabel.setText("");
        try {
            CustomerDto customerDto = customerController.getCustomer(id);
            if (customerDto == null) {
                new Alert(Alert.AlertType.INFORMATION, "Customer not found");
            } else {
                if (customerDto.getToReturn() == null) {
                    customerInfoLabel.setText("Customer is eligible to rent a car");
                } else {
                    customerInfoLabel.setText("Customer is not eligible to rent a car.Already in a ongoing rent");
                }
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCategories() throws Exception {

        try {
            List<CategoryDto> categoryDtos = categoryController.getAllCatergories();

            ObservableList<String> obList = FXCollections.observableArrayList();

            for (CategoryDto categoryDto : categoryDtos) {
                obList.add(categoryDto.getName());
            }
            carCatCombo.setItems(obList);

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    private void setCars() throws Exception {

        try {
            String category =  carCatCombo.getValue().toString();
            List<CarDto> carDtos = carController.getCarsByCategory(category);

            ObservableList<String> obList = FXCollections.observableArrayList();

            for (CarDto carDto : carDtos) {
                obList.add(carDto.getNumber());
            }

            carNumberCombo.setItems(obList);

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }
    public void carCategoryOnAction(ActionEvent actionEvent) throws Exception {
        setCars();
        System.out.println(carCatCombo.getValue().toString());
    }

    public void getCarByCarNumber(){
        try {
            String carNumber = carNumberCombo.getValue().toString();
            CarDto carDto = carController.getCarByCarNumber(carNumber);
            carInfoLabel.setText(carDto.getModel().toString());
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    public void checkCarButtonAction(ActionEvent actionEvent) {
        getCarByCarNumber();
    }
}
