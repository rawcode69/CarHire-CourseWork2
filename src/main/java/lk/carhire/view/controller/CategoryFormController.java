package lk.carhire.view.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import lk.carhire.controller.CategoryController;
import lk.carhire.dto.CategoryDto;

public class CategoryFormController {
    public TextField catIdText;
    public TextField catNameText;

    public CategoryController categoryController;

    public CategoryFormController() {
        categoryController = new CategoryController();

    }

    public void saveButtonOnAction(ActionEvent actionEvent) {
        saveCategory();
    }

    private void saveCategory() {

       CategoryDto category = new CategoryDto(Integer.valueOf(catIdText.getText()),catNameText.getText());
       categoryController.saveCategory();

    }


}
