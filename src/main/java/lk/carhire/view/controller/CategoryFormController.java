package lk.carhire.view.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
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

    public void saveButtonOnAction(ActionEvent actionEvent) throws Exception {
        saveCategory();
    }

    private void saveCategory() throws Exception {

       CategoryDto category = new CategoryDto();
       category.setName(catNameText.getText());
        try {
            categoryController.saveCategory(category);
            new Alert(Alert.AlertType.CONFIRMATION, "Category Saved");
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }


    }


}
