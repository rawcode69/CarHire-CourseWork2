package lk.carhire.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import lk.carhire.controller.CustomerController;
import lk.carhire.dto.CustomerDto;
import lk.carhire.dto.tm.CarTM;
import lk.carhire.dto.tm.CustomerTM;

import java.util.List;
import java.util.PrimitiveIterator;

public class CustomerFormController {
    public TextField custIdText;
    public TextField userNameText;
    public Button searchButton;
    public TextField firstNameText;
    public TextField lastNameText;
    public TextField streetText;
    public TextField cityText;

    public Button updateButton;
    public Button saveButton;
    public TextField districtText;
    public TextField postalCodeText;
    public TextField emailText;
    public TextField mobileText;
    public Button deleteButton;
    public TableColumn clmCustId;
    public TableColumn clmcustName;
    public TableColumn clmCustAddress;
    public TableColumn clmCustMobile;

    public TableView custTable;
    CustomerController customerController;



    public void initialize(){
        customerController = new CustomerController();
       // System.out.println("Customer Form Initialized");
        loadTables();
        tableListener();
    }

    private void setTableData(List<CustomerDto> customerDtos) {
        ObservableList<CustomerTM> oblist = FXCollections.observableArrayList();

        for(CustomerDto customerDto : customerDtos){
           String name = customerDto.getFirstName()+" "+customerDto.getLastName();
           String address = customerDto.getStreet()+", "+customerDto.getCity()+", "+customerDto.getDistrict();

           CustomerTM customerTM = new CustomerTM(
                   String.valueOf(customerDto.getId())
                   ,name
                   ,address
                   ,customerDto.getMobile());
           oblist.add(customerTM);
        }
            custTable.setItems(oblist);

    }

    private void setCellValueFactory() {
        clmCustId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmcustName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmCustAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        clmCustMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    }

    private List<CustomerDto> getAllCustomers() {
        try {
           return customerController.getAllCustomer();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Customers Not Loaded"+e.getMessage()).show();
            return null;
        }
    }


    public void saveButtonOnAction(ActionEvent actionEvent) {
        saveCustomer();
    }

    public void searchButtonAction(ActionEvent actionEvent) {
        getCustomer();
    }

    public void deleteButtonAction(ActionEvent actionEvent) {
        deleteCustomer();
    }

    private void deleteCustomer() {

        CustomerDto customerDto = new CustomerDto();

        customerDto.setId(Integer.valueOf(custIdText.getText()));
        customerDto.setUserName(userNameText.getText());
        customerDto.setEmail(emailText.getText());
        customerDto.setFirstName(firstNameText.getText());
        customerDto.setLastName(lastNameText.getText());
        customerDto.setStreet(streetText.getText());
        customerDto.setCity(cityText.getText());
        customerDto.setDistrict(districtText.getText());
        customerDto.setPostalCode(postalCodeText.getText());
        customerDto.setMobile(mobileText.getText());


        try {
            customerController.deleteCustomer(customerDto);
            new Alert(Alert.AlertType.CONFIRMATION,"Customer Deleted").show();
            loadTables();
            clearForm();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void saveCustomer() {
        CustomerDto customerDto = new CustomerDto(
                userNameText.getText(),
                emailText.getText(),
                firstNameText.getText(),
                lastNameText.getText(),
                streetText.getText(),
                cityText.getText(),
                districtText.getText(),
                postalCodeText.getText(),
                mobileText.getText());
        try {
            Integer id = customerController.saveCustomer(customerDto);
            new Alert(Alert.AlertType.CONFIRMATION,"Customer Saved Successfully").show();
            loadTables();
            clearForm();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    private void getCustomer(){

        Integer id = Integer.valueOf(custIdText.getText());

        try {
           CustomerDto customer =  customerController.getCustomer(id);
           setForm(customer);
        }catch (Exception e){

        }
    }

    private CustomerDto getCustomer(Integer id) throws Exception {
        try {
            CustomerDto customerDto = customerController.getCustomer(id);
            return customerDto;
        }catch (Exception e){
            throw e;
        }
    }

    private void setForm(CustomerDto customer) {
        userNameText.setText(customer.getUserName());
        firstNameText.setText(customer.getFirstName());
        lastNameText.setText(customer.getLastName());
        streetText.setText(customer.getStreet());
        cityText.setText(customer.getCity());
        districtText.setText(customer.getDistrict());
        postalCodeText.setText(customer.getPostalCode());
        emailText.setText(customer.getEmail());
        mobileText.setText(customer.getMobile());

    }


    public void updateButtonAction(ActionEvent actionEvent) {

         updateCustomer();
    }

    private void updateCustomer(){
        CustomerDto customerDto = new CustomerDto();

        customerDto.setId(Integer.valueOf(custIdText.getText()));
        customerDto.setUserName(userNameText.getText());
        customerDto.setEmail(emailText.getText());
        customerDto.setFirstName(firstNameText.getText());
        customerDto.setLastName(lastNameText.getText());
        customerDto.setStreet(streetText.getText());
        customerDto.setCity(cityText.getText());
        customerDto.setDistrict(districtText.getText());
        customerDto.setPostalCode(postalCodeText.getText());
        customerDto.setMobile(mobileText.getText());

        try {
            customerController.updateCustomer(customerDto);
            new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated").show();
            loadTables();
            clearForm();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void clearForm(){
        custIdText.setText("");
        userNameText.setText("");
        firstNameText.setText("");
        lastNameText.setText("");
        streetText.setText("");
        cityText.setText("");
        districtText.setText("");
        emailText.setText("");
        mobileText.setText("");
        postalCodeText.setText("");
    }


    public void custIdTextAction(ActionEvent actionEvent) {
        Integer id = Integer.valueOf(custIdText.getText());

        try {
            CustomerDto customer =  customerController.getCustomer(id);
            setForm(customer);
        }catch (Exception e){

        }
    }

    private void loadTables(){
        setCellValueFactory();
        List<CustomerDto> customerDtos = getAllCustomers();
        setTableData(customerDtos);
    }

    private void tableListener(){
        custTable.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    try {
                        setData((CustomerTM) newValue);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    private void setData(CustomerTM customerTM) throws Exception {


        try {
            Integer id = Integer.valueOf(customerTM.getId());
            CustomerDto customerDto = getCustomer(id);
            custIdText.setText(String.valueOf(id) );
            userNameText.setText(customerDto.getUserName());
            firstNameText.setText(customerDto.getFirstName());
            lastNameText.setText(customerDto.getLastName());
            streetText.setText(customerDto.getStreet());
            cityText.setText(customerDto.getCity());
            districtText.setText(customerDto.getDistrict());
            postalCodeText.setText(customerDto.getPostalCode());
            emailText.setText(customerDto.getEmail());
            mobileText.setText(customerDto.getMobile());
        }catch (Exception e){

        }

    }
}
