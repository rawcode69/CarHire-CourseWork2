package lk.carhire.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.carhire.controller.CarController;
import lk.carhire.controller.CustomerController;
import lk.carhire.controller.RentController;
import lk.carhire.dto.CarDto;
import lk.carhire.dto.CustomerDto;
import lk.carhire.dto.OngoingRentDto;
import lk.carhire.dto.RentDto;
import lk.carhire.dto.tm.CustomerTM;
import lk.carhire.dto.tm.OngoingRentTM;
import lk.carhire.dto.tm.RentTM;
import lk.carhire.entity.CarEntity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class OngoingRentFormController {

    public Button rentCompletedButton;
    public TableColumn colRentId;


    public TableColumn colRentDate;
    public TableColumn colReturnDate;
    public TableColumn colOverDue;
    public TableColumn colBalance;
    public TableColumn colCustName;

    public Label currentDateLabel;
    public TableView rentTable;
    public TableColumn colCarNumber;
    public TextField rentIdText;
    public TextField custNameText;
    public TextField carNumberText;
    public TextField rentDateText;
    public TextField returnDateText;
    public TextField overDueText;
    public TextField balanceToPayText;

    RentController rentController;
    CustomerController customerController;

    CarController carController;

    public void initialize() throws Exception {
        rentController = new RentController();
        customerController = new CustomerController();
        carController = new CarController();
        currentDate();
        List<RentDto> rentDtos = loadAllActiveRents();
        setCellValueFactory();
        setTableValues(rentDtos);
        tableListener();
    }

    private void setTableValues(List<RentDto> rentDtos) throws Exception {
        ObservableList<OngoingRentTM> obList = FXCollections.observableArrayList();

        for (RentDto rentDto : rentDtos) {

            CustomerDto customerDto = customerController.getCustomer(rentDto.getCustomerId());
            String custName = customerDto.getFirstName() + " " + customerDto.getLastName();


            OngoingRentTM ongoingRentTM = new OngoingRentTM();
            ongoingRentTM.setRentId(String.valueOf(rentDto.getId()));
            ongoingRentTM.setCustomerName(custName);
            ongoingRentTM.setCarNumber(rentDto.getCarNumber());
            ongoingRentTM.setReturnDate(rentDto.getEndDate().toString());
            ongoingRentTM.setRentDate(rentDto.getRentDate().toString());

            Date returnDate = rentDto.getEndDate();
            Date currentDate = java.sql.Date.valueOf(currentDateLabel.getText());

            String isOverdue = "No";

            int difference = currentDate.compareTo(returnDate);
            Double balance = rentDto.getTotal() - rentDto.getAdvancePayment();
            long nosExtraDays = 0;

            if (difference > 0) {
                isOverdue = "YES";
                nosExtraDays = (returnDate.getTime() - currentDate.getTime()) / (24 * 60 * 60 * 1000);
                balance += nosExtraDays * rentDto.getRate();
            }

            ongoingRentTM.setOverdue(isOverdue);
            ongoingRentTM.setBalanceToPay(String.valueOf(balance));

            obList.add(ongoingRentTM);
        }

        rentTable.setItems(obList);
        
    }

    private void setCellValueFactory() {

        colRentId.setCellValueFactory(new PropertyValueFactory<>("rentId"));
        colCustName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colCarNumber.setCellValueFactory(new PropertyValueFactory<>("carNumber"));
        colRentDate.setCellValueFactory(new PropertyValueFactory<>("rentDate"));
        colOverDue.setCellValueFactory(new PropertyValueFactory<>("overdue"));
        colBalance.setCellValueFactory(new PropertyValueFactory<>("balanceToPay"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

    }


    private List<RentDto> loadAllActiveRents() throws Exception {

        try {
            List<RentDto> rentDtos = rentController.getAllActiveRents();
            return rentDtos;
        } catch (Exception e) {
            throw e;
        }
    }

    public void rentCompletedButtonAction(ActionEvent actionEvent) throws Exception {

        closeRent();

    }

    private void closeRent() throws Exception {

    Integer rentId = Integer.valueOf(rentIdText.getText());

    Double balanceToPay = Double.valueOf(balanceToPayText.getText());
    RentDto rentDto = rentController.getRent(rentId);
    Integer custId = rentDto.getCustomerId();
    CarDto carDto = carController.getCarByCarNumber(carNumberText.getText());

    OngoingRentDto ongoingRentDto = new OngoingRentDto();

    ongoingRentDto.setBalanceToPay(balanceToPay);
    ongoingRentDto.setCustId(custId);
    ongoingRentDto.setCarId(carDto.getId());
    ongoingRentDto.setRentId(rentId);


    try {
        String resp =  rentController.closeRent(ongoingRentDto);
        new Alert(Alert.AlertType.INFORMATION,resp).show();
    }catch (Exception e){
        new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        System.out.println(e);
    }


    }

    private void currentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNow = sdf.format(new Date());
        currentDateLabel.setText(dateNow);

    }

    private void tableListener(){
        rentTable.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    try {
                        setData((OngoingRentTM) newValue);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    private void setData(OngoingRentTM ongoingRentTM) {

        try {
            rentIdText.setText(ongoingRentTM.getRentId());
            custNameText.setText(ongoingRentTM.getCustomerName());
            carNumberText.setText(ongoingRentTM.getCarNumber());
            rentDateText.setText(ongoingRentTM.getRentDate());
            returnDateText.setText(ongoingRentTM.getReturnDate());
            overDueText.setText(ongoingRentTM.getOverdue());
            balanceToPayText.setText(ongoingRentTM.getBalanceToPay());

        }catch (Exception e){

        }
    }
}
