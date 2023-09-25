package lk.carhire.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.carhire.controller.CarController;
import lk.carhire.controller.CategoryController;
import lk.carhire.controller.CustomerController;
import lk.carhire.controller.RentController;
import lk.carhire.dto.CarDto;
import lk.carhire.dto.CategoryDto;
import lk.carhire.dto.CustomerDto;
import lk.carhire.dto.RentDto;
import lk.carhire.dto.tm.RentTM;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

public class RentFormController {


    public TextField idText;
    public Label customerInfoLabel;
    public ComboBox carCatCombo;
    public ComboBox carNumberCombo;
    public Label carInfoLabel;
    public TableView rentTable;
    public TableColumn colCarNumber;
    public TableColumn colCustName;
    public TableColumn colRate;
    public TableColumn colNosDays;
    public TableColumn colAdvance;

    public TableColumn colTotal;
    public TableColumn colBalance;

    public TableColumn colDeposit;
    public DatePicker startDatePicker;
    public DatePicker EndDatePicker;
    public TextField advancePaymentText;
    public TextField depositAmountText;

    public Label currentDateLabel;
    public Button addButton;
    public Button checkCarButton;
    CustomerController customerController;
    CategoryController categoryController;
    CarController carController;

    RentController rentController;

    public void initialize() throws Exception {
        customerController = new CustomerController();
        categoryController = new CategoryController();
        carController = new CarController();
        rentController = new RentController();
        setCategories();
        currentDate();

        addButton.setDisable(true);
        depositAmountText.setEditable(false);
        advancePaymentText.setText("0");
        checkCarButton.setDisable(true);
        rentTable.setEditable(false);

    }

    public void checkCutomerButtonAction(ActionEvent actionEvent) throws Exception {
        checkCustomer();
    }

    private boolean checkCustomer() throws Exception {

        try {
            Integer id = Integer.valueOf(idText.getText());
            //   customerInfoLabel.setText("");
            CustomerDto customerDto = customerController.getCustomer(id);
            if (customerDto == null) {
                new Alert(Alert.AlertType.INFORMATION, "Customer not found");
                return false;
            } else {
                if (customerDto.getToReturn() == null) {
                    customerInfoLabel.setText("Customer is eligible to rent a car");
                    checkCarButton.setDisable(false);
                    return true;
                } else {
                    customerInfoLabel.setText("Customer is not eligible to rent a car.Already in a ongoing rent");
                    return false;
                }
            }

        } catch (NullPointerException nullPointerException) {
            customerInfoLabel.setText("Customer Not Found");
            return false;
        } catch (NumberFormatException numberFormatException) {
            customerInfoLabel.setText("Only Input Numbers for Customer Id");
            return false;
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            System.out.println(e);
            throw e;
        }

        //  checkCarButton.setDisable(false);


    }

    private void setCategories() throws Exception {

        try {
            List<CategoryDto> categoryDtos = categoryController.getAllCatergories();

            ObservableList<String> obList = FXCollections.observableArrayList();

            for (CategoryDto categoryDto : categoryDtos) {
                obList.add(categoryDto.getName());
            }
            carCatCombo.setItems(obList);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void setCars() throws Exception {

        try {
            String category = carCatCombo.getValue().toString();
            List<CarDto> carDtos = carController.getCarsByCategory(category);

            ObservableList<String> obList = FXCollections.observableArrayList();

            for (CarDto carDto : carDtos) {
                obList.add(carDto.getNumber());
            }

            carNumberCombo.setItems(obList);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    public void carCategoryOnAction(ActionEvent actionEvent) throws Exception {
        setCars();
        System.out.println(carCatCombo.getValue().toString());
    }

    public CarDto getCarByCarNumber() {
        try {
            String carNumber = carNumberCombo.getValue().toString();
            CarDto carDto = carController.getCarByCarNumber(carNumber);
            return carDto;
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            return null;
        }
    }

    public void checkCarButtonAction(ActionEvent actionEvent) throws Exception {
        checkCar();
    }

    public boolean checkCar() throws Exception {
        CarDto carDto = getCarByCarNumber();

        if (!carDto.getIsRentable()) {
            carInfoLabel.setText(carDto.getBrand().toString() + ", " + carDto.getModel() + ", " + carDto.getYear() + " - " +
                    "Car is not free to rent");
            return false;

        } else {
            carInfoLabel.setText(carDto.getBrand().toString() + ", " + carDto.getModel() + ", " + carDto.getYear() + " - " +
                    "Car is free to rent");
            depositAmountText.setText(String.valueOf(carDto.getDepositAmount()));
        }

        if (carDto.getIsRentable() && checkCustomer()) {
            addButton.setDisable(false);
        }

        return true;
    }

    public void addButtonAction(ActionEvent actionEvent) throws Exception {

        if (checkCustomer() && checkCar()) {
            loadTable();
        }

    }

    private void setValueFactory() {
        colCustName.setCellValueFactory(new PropertyValueFactory<>("custName"));
        colCarNumber.setCellValueFactory(new PropertyValueFactory<>("carNumber"));
        colRate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        colNosDays.setCellValueFactory(new PropertyValueFactory<>("nosDays"));
        colAdvance.setCellValueFactory(new PropertyValueFactory<>("advance"));
        colDeposit.setCellValueFactory(new PropertyValueFactory<>("deposit"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        colBalance.setCellValueFactory(new PropertyValueFactory<>("balanceToPay"));

    }

    private void setTableData() throws Exception {

        ObservableList<RentTM> obList = FXCollections.observableArrayList();

        RentTM rentTM = new RentTM();
        CustomerDto customerDto = customerController.getCustomer(Integer.valueOf(idText.getText()));

        String custName = customerDto.getFirstName() + " " + customerDto.getLastName();

        LocalDate starDate = startDatePicker.getValue();
        LocalDate endDate = EndDatePicker.getValue();

        String msg = null;
        long nosDates = 0;

        if (starDate == null || endDate == null) {
            new Alert(Alert.AlertType.ERROR, "Please Input Dates").show();

        } else {
            nosDates = starDate.until(endDate, ChronoUnit.DAYS);
            CarDto carDto = carController.getCarByCarNumber(carNumberCombo.getValue().toString());

            double total = (nosDates * carDto.getRate());


            rentTM.setCustName(custName);
            rentTM.setCarNumber(carDto.getNumber());
            rentTM.setRate(String.valueOf(carDto.getRate()));
            rentTM.setNosDays(String.valueOf(nosDates));
            rentTM.setAdvance(advancePaymentText.getText());
            rentTM.setDeposit(depositAmountText.getText());
            rentTM.setTotalAmount(String.valueOf(total));
            rentTM.setBalanceToPay((String.valueOf(total - Double.valueOf(advancePaymentText.getText()))));


            if (nosDates < 0) {
                new Alert(Alert.AlertType.ERROR, "Please Input Valid Dates").show();
            } else {

                if (nosDates > 30) {
                    new Alert(Alert.AlertType.ERROR, "You Cannot Rent a Car for more than 30 Days").show();
                } else {
                    obList.add(rentTM);
                    rentTable.setItems(obList);
                }

            }

        }


    }

    private void loadTable() throws Exception {
        setValueFactory();
        setTableData();


    }


    public void rentButtonOnAction(ActionEvent actionEvent) throws Exception {
        placeRent();
    }

    private void placeRent() throws Exception {

        CarDto carDto = carController.getCarByCarNumber(carNumberCombo.getValue().toString());
        Double rate = carDto.getRate();

        LocalDate starDate = startDatePicker.getValue();
        LocalDate endDate = EndDatePicker.getValue();

        long nosDates = 0;

        if (starDate != null && endDate != null) {
            nosDates = starDate.until(endDate, ChronoUnit.DAYS);
        }
        Double total = rate * nosDates;
        RentDto rentDto = new RentDto();

        String catName = carCatCombo.getValue().toString();
        CategoryDto categoryDto = categoryController.getCategoryByName(catName);

        rentDto.setRentDate(java.sql.Date.valueOf(currentDateLabel.getText()));
        rentDto.setStartDate(java.sql.Date.valueOf(startDatePicker.getValue()));
        rentDto.setEndDate(java.sql.Date.valueOf(EndDatePicker.getValue()));
        rentDto.setAdvancePayment(Double.valueOf(advancePaymentText.getText()));
        rentDto.setDepositAmount(Double.valueOf(depositAmountText.getText()));
        rentDto.setCustomerId(Integer.valueOf(idText.getText()));
        rentDto.setCarCategory(categoryDto.getId());
        rentDto.setCarNumber(carNumberCombo.getValue().toString());
        rentDto.setAdvancePayment(Double.valueOf(advancePaymentText.getText()));
        rentDto.setRate(rate);
        rentDto.setTotal(total);
        rentDto.setIsActive(true);

        try {
            String resp = rentController.placeRent(rentDto);
            clearTable();
            clearForm();

            new Alert(Alert.AlertType.CONFIRMATION, resp).show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            System.out.println(e);
        }

    }

    private void currentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNow = sdf.format(new Date());
        currentDateLabel.setText(dateNow);
    }

    private void clearForm() {

        try {
            startDatePicker.setValue(null);
            EndDatePicker.setValue(null);
            advancePaymentText.setText("");
            depositAmountText.setText("");
            idText.setText("");
            carCatCombo.setValue("");
            carNumberCombo.setValue("");
            carInfoLabel.setText("");
            customerInfoLabel.setText("");

        } catch (Exception e) {
            throw e;
        }

    }

    private void clearTable() {
        try {
            ObservableList<String> dataList = rentTable.getItems();
            dataList.clear();
        } catch (Exception e) {
            throw e;
        }

    }

}
