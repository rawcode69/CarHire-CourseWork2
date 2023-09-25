package lk.carhire.view.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.carhire.Launcher;
import lk.carhire.controller.UserController;
import lk.carhire.dto.UserDto;

public class LoginFormController {
    public TextField userNameText;
    public TextField passwordText;
    public Button loginButton;

    UserController userController;
    Launcher launcher;

    public void initialize() {
        userController = new UserController();
        launcher = new Launcher();
    }

    public void loginButtonAction(ActionEvent actionEvent) throws Exception {

        String userName = userNameText.getText();
        String password = passwordText.getText();

        try {
            UserDto userDto = userController.getUserByUserName(userName);

            String userNameGot = userDto.getUserName();
            String userPasswordGot = userDto.getPassWord();

            if (userName.equals(userNameGot) && password.equals(userPasswordGot)) {
                Stage stage = new Stage();
                launcher.launchDashboard(stage);
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }
}
