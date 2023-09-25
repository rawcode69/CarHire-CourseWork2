package lk.carhire.view.controller;

import jakarta.persistence.criteria.CriteriaBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.carhire.controller.UserController;
import lk.carhire.dto.CustomerDto;
import lk.carhire.dto.UserDto;
import lk.carhire.dto.tm.CategoryTM;
import lk.carhire.dto.tm.UserTM;

import java.util.List;

public class UserFormController {
    public TextField idText;
    public Button searchButton;
    public TextField lastNameText;
    public TextField roleText;
    public TextField userNameText;
    public TextField passwordText;
    public TextField firstNameText;
    public TableColumn colId;
    public TableColumn colFullName;
    public TableColumn colRole;
    public TableColumn colUserName;
    public TableView userTable;

    UserController userController;

    public void initialize() throws Exception {
        userController = new UserController();
        loadTable();
        tableListener();
    }

    private void setTableData(List<UserDto> userDtos) {
        ObservableList<UserTM> oblist = FXCollections.observableArrayList();

        for (UserDto userDto : userDtos) {

            String fullName = userDto.getFirstName() + " " + userDto.getLastName();
            UserTM userTM = new UserTM();

            userTM.setId(String.valueOf(userDto.getId()));
            userTM.setFullName(fullName);
            userTM.setRole(userDto.getRole());
            userTM.setUserName(userDto.getUserName());
            oblist.add(userTM);
        }
        userTable.setItems(oblist);
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
    }

    private List<UserDto> getAllUsers() throws Exception {
        List<UserDto> userDtos = userController.getAllUsers();
        return userDtos;
    }

    public void saveButtonAction(ActionEvent actionEvent) {
        saveUser();
    }

    private void saveUser() {

        if (firstNameText.getText() == "" || lastNameText.getText() == "" || userNameText.getText() == ""
                || passwordText.getText() == "" || roleText.getText() == "") {

            new Alert(Alert.AlertType.ERROR, "Any field Cannot be empty").show();

        } else {
            UserDto userDto = new UserDto();
            userDto.setFirstName(firstNameText.getText());
            userDto.setLastName(lastNameText.getText());
            userDto.setUserName(userNameText.getText());
            userDto.setPassWord(passwordText.getText());
            userDto.setRole(roleText.getText());
            try {
                Integer id = userController.saveUser(userDto);
                new Alert(Alert.AlertType.CONFIRMATION, "User Saved Successfully").show();
                clearForm();
                loadTable();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    private void clearForm() {
        idText.setText("");
        firstNameText.setText("");
        lastNameText.setText("");
        roleText.setText("");
        userNameText.setText("");
        passwordText.setText("");
    }

    public void updateButtonAction(ActionEvent actionEvent) {

        if (firstNameText.getText() == "" || lastNameText.getText() == "" || userNameText.getText() == ""
                || passwordText.getText() == "" || roleText.getText() == "") {

            new Alert(Alert.AlertType.ERROR, "Any Field Cannot be Empty").show();
        } else {


            UserDto userDto = new UserDto();

            userDto.setId(Integer.valueOf(idText.getText()));
            userDto.setFirstName(firstNameText.getText());
            userDto.setLastName(lastNameText.getText());
            userDto.setUserName(userNameText.getText());
            userDto.setPassWord(passwordText.getText());
            userDto.setRole(roleText.getText());

            try {
                userController.updateUser(userDto);
                new Alert(Alert.AlertType.CONFIRMATION, "User Saved Successfully").show();
                clearForm();
                loadTable();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    public void getUser() throws Exception {

        Integer id = Integer.valueOf(idText.getText());
        UserDto userDto = userController.getUser(id);

        setForm(userDto);

    }

    public UserDto getUser(Integer id) throws Exception {
        UserDto userDto = userController.getUser(id);
        return userDto;
    }

    private void setForm(UserDto userDto) {
        firstNameText.setText(userDto.getFirstName());
        lastNameText.setText(userDto.getLastName());
        roleText.setText(userDto.getRole());
        userNameText.setText(userDto.getUserName());
        passwordText.setText(userDto.getPassWord());
    }


    public void searchButtonAction(ActionEvent actionEvent) throws Exception {
        getUser();
    }

    public void deleteButtonAction(ActionEvent actionEvent) throws Exception {
        deleteUser();
    }

    private void deleteUser() throws Exception {
        UserDto userDto = new UserDto();

        userDto.setId(Integer.valueOf(idText.getText()));
        userDto.setFirstName(firstNameText.getText());
        userDto.setLastName(lastNameText.getText());
        userDto.setUserName(userNameText.getText());
        userDto.setPassWord(passwordText.getText());
        userDto.setRole(roleText.getText());

        try {
            userController.deleteUser(userDto);
            new Alert(Alert.AlertType.CONFIRMATION, "User Deleted Successfully").show();
            clearForm();
            loadTable();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void loadTable() throws Exception {
        List<UserDto> userDtos = getAllUsers();
        setCellValueFactory();
        setTableData(userDtos);
    }


    public void userIdAction(ActionEvent actionEvent) throws Exception {

        getUser();
    }


    public void clearButtonAction(ActionEvent actionEvent) {
        clearForm();
    }

    private void tableListener() {
        userTable.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData((UserTM) newValue);
                });
    }

    private void setData(UserTM userTM) {

        try {
            Integer id = Integer.valueOf(userTM.getId());
            UserDto userDto = getUser(id);

            idText.setText(String.valueOf(id));
            firstNameText.setText(userDto.getFirstName());
            lastNameText.setText(userDto.getLastName());
            roleText.setText(userDto.getRole());
            userNameText.setText(userDto.getUserName());
            passwordText.setText(userDto.getPassWord());

        } catch (Exception e) {

        }
    }
}
