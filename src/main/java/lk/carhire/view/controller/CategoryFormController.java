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

    public void initialize() {
        categoryController = new CategoryController();
        System.out.println("Category Form Initialized");
    }

    private void saveCategory() throws Exception {

       CategoryDto category = new CategoryDto();
       category.setName(catNameText.getText());
        try {
            Integer id =  categoryController.saveCategory(category);
            new Alert(Alert.AlertType.CONFIRMATION, "Category Saved").show();
            System.out.println(id);
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }


    }

    public void saveButtonAction(ActionEvent actionEvent) throws Exception {
        saveCategory();
    }
}
