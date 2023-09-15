package lk.carhire.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.carhire.controller.CategoryController;
import lk.carhire.dto.CategoryDto;
import lk.carhire.dto.tm.CategoryTM;
import lk.carhire.entity.CategoryEntity;

import java.util.ArrayList;
import java.util.List;

public class CategoryFormController {
    public TextField catIdText;
    public TextField catNameText;

    public CategoryController categoryController;
    public TableColumn colCatId;
    public TableColumn colCatName;
    public TableView catTable;

    public void initialize() {
        categoryController = new CategoryController();
        System.out.println("Category Form Initialized");

        setCellValueFactory();
        ArrayList<CategoryDto> categoryDtos = loadAllCategories();

        setTableData(categoryDtos);
    }

    private void setTableData(ArrayList<CategoryDto> categoryDtos) {
        ObservableList<CategoryTM> oblist = FXCollections.observableArrayList();

        for(CategoryDto categoryDto : categoryDtos){
            CategoryTM categoryTM = new CategoryTM(String.valueOf(categoryDto.getId()) ,categoryDto.getName());
            oblist.add(categoryTM);
        }
        catTable.setItems(oblist);
    }

    private void setCellValueFactory() {
        colCatId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCatName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }


    public void saveButtonAction(ActionEvent actionEvent) throws Exception {
        saveCategory();
    }

    public void updateButtonAction(ActionEvent actionEvent) throws Exception {
        updateCategory();
    }
    public void searchButtonAction(ActionEvent actionEvent) throws Exception {
        getCategory();
    }

    public void catTextOnAction(ActionEvent actionEvent) throws Exception {
        getCategory();
    }
    public void deleteButtonAction(ActionEvent actionEvent) {
        deleteCategory();
    }

    private void deleteCategory() {
        CategoryDto categoryDto = new CategoryDto(Integer.valueOf(catIdText.getText()),catNameText.getText());

        try {
            categoryController.deleteCategory(categoryDto);
            new Alert(Alert.AlertType.CONFIRMATION,"Category Deleted").show();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void updateCategory() throws Exception{
        CategoryDto category = new CategoryDto(Integer.valueOf(catIdText.getText()),catNameText.getText());

        try {
            categoryController.updateCategory(category);
            new Alert(Alert.AlertType.CONFIRMATION,"Category Updated").show();

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
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

    private void getCategory() throws Exception{
        Integer categoryId = Integer.valueOf(catIdText.getText());
        try {
            CategoryDto categoryDto = categoryController.getCategory(categoryId);

            new Alert(Alert.AlertType.INFORMATION,"Category Id:"+categoryDto.getId().toString()+" "
                    +" Category Name: "+categoryDto.getName()).show();
            setForm(categoryDto);
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void setForm(CategoryDto categoryDto) {

        catNameText.setText(categoryDto.getName());
    }
    private ArrayList<CategoryDto> loadAllCategories() {

    try {
        List<CategoryDto> categoryDtos = categoryController.getAllCatergories();
        return (ArrayList<CategoryDto>) categoryDtos;
    }catch (Exception e){
        new Alert(Alert.AlertType.ERROR,"Categories not loaded");
    }
        return  null;
    }


}
