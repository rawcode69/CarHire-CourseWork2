package lk.carhire.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import lk.carhire.controller.CarController;
import lk.carhire.dto.CarDto;
import lk.carhire.dto.tm.CarTM;
import lk.carhire.dto.tm.CategoryTM;

import java.util.List;

public class CarsFormController {


    public TextField catIdText;
    public TableColumn colId;
    public TableColumn colNumber;
    public TableColumn colBrand;
    public TableColumn colModel;
    public TableColumn colRate;
    public TableColumn colToRent;
    public TextField depositText;
    CarController carController;

    public void initialize() throws Exception {
        carController = new CarController();
        loadTable();
        tableListener();

    }

    private void setTableData(List<CarDto> carDtos) {
        ObservableList <CarTM> obList = FXCollections.observableArrayList();

        for(CarDto carDto : carDtos){
            String toRent = null;
            if(carDto.getIsRentable() == true){
                toRent = "YES";
            }else {
                toRent = "NO";
            }
            CarTM carTM = new CarTM();
            carTM.setId(String.valueOf(carDto.getId()));
            carTM.setNumber(carDto.getNumber());
            carTM.setBrand(carDto.getBrand());
            carTM.setModel(carDto.getModel());
            carTM.setRate(String.valueOf(carDto.getRate()));
            carTM.setToRent(toRent);

            obList.add(carTM);
        }
        carTable.setItems(obList);

    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colRate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        colToRent.setCellValueFactory(new PropertyValueFactory<>("toRent"));
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

    private List<CarDto> getAllCars() throws Exception {
        List <CarDto> carDtos =  carController.getAllCars();
        return carDtos;
    }

    private void updateCars() {

        CarDto carDto = new CarDto();
            carDto.setId(Integer.valueOf(carIdText.getText()));
            carDto.setNumber(carNumberText.getText());
            carDto.setBrand(carBrandText.getText());
            carDto.setModel(carModelText.getText());
            carDto.setYear(Integer.valueOf(yearText.getText()));
            carDto.setRate(Double.valueOf(rateText.getText()));
            carDto.setCatId(Integer.valueOf(catIdText.getText()));
            carDto.setDepositAmount(Double.valueOf(depositText.getText()));
        try {
            carController.updateCars(carDto);
            new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated").show();
            clearForm();
            loadTable();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            System.out.println(e.getMessage());
        }

    }

    private void saveCars() {
        CarDto carDto = new CarDto();

        carDto.setNumber(carNumberText.getText());
        carDto.setBrand(carBrandText.getText());
        carDto.setModel(carModelText.getText());
        carDto.setYear(Integer.valueOf(yearText.getText()));
        carDto.setRate(Double.valueOf(rateText.getText()));
        carDto.setCatId(Integer.valueOf(catIdText.getText()));
        carDto.setDepositAmount(Double.valueOf(depositText.getText()));

        try {
            Integer id = carController.saveCar(carDto);
            System.out.println(id);
            clearForm();
            loadTable();
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

    private CarDto getCar(Integer id) throws Exception{
        try {
            CarDto carDto = carController.getCar(id);
            return carDto;
        }catch (Exception e){
            throw e;
        }
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
        carIdText.setText("");
        carNumberText.setText("");
        carModelText.setText("");
        carBrandText.setText("");
        yearText.setText("");
        rateText.setText("");
        catIdText.setText("");
    }

    private void deleteCar() throws Exception {

        CarDto carDto = new CarDto();
        carDto.setId(Integer.valueOf(carIdText.getText()));
        carDto.setNumber(carNumberText.getText());
        carDto.setBrand(carBrandText.getText());
        carDto.setModel(carModelText.getText());
        carDto.setYear(Integer.valueOf(yearText.getText()));
        carDto.setRate(Double.valueOf(rateText.getText()));
        carDto.setCatId(Integer.valueOf(catIdText.getText()));
        carDto.setDepositAmount(Double.valueOf(depositText.getText()));

        try {
            carController.deleteCar(carDto);
            new Alert(Alert.AlertType.CONFIRMATION,"Car Deleted Successfully").show();
            clearForm();
            loadTable();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    private void loadTable() throws Exception {
        setCellValueFactory();
        List <CarDto> carDtos = getAllCars();
        setTableData(carDtos);
    }

    private void tableListener(){
        carTable.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    try {
                        setData((CarTM) newValue);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    private void setData(CarTM carTM) throws Exception {
            try {
                Integer carId = Integer.valueOf(carTM.getId());
                CarDto carDto = getCar(carId);
                carIdText.setText(carTM.getId());
                carNumberText.setText(carDto.getNumber());
                carBrandText.setText(carDto.getBrand());
                carModelText.setText(carDto.getModel());
                yearText.setText(String.valueOf(carDto.getYear()));
                rateText.setText(String.valueOf(carDto.getRate()));
                catIdText.setText(String.valueOf(carDto.getCatId()) );
            }catch (Exception e){

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

    public void clearButtonAction(ActionEvent actionEvent) {
        clearForm();
    }
}
