package lk.carhire.controller;

import lk.carhire.dto.CategoryDto;
import lk.carhire.service.ServiceFactory;

import lk.carhire.service.custom.CategoryService;

import java.util.List;


public class CategoryController {

    CategoryService categoryService = (CategoryService) ServiceFactory.getInstance().getService(ServiceFactory.serviceType.CATEGORY);
    public Integer saveCategory(CategoryDto category) throws Exception {
        Integer id = categoryService.saveCategory(category);
        return id;
    }

    public void updateCategory(CategoryDto category) throws Exception {
        categoryService.updateCategory(category);
    }

    public CategoryDto getCategory(Integer categoryId) throws Exception{
        return categoryService.getCategory(categoryId);
    }


    public void deleteCategory(CategoryDto categoryDto) throws  Exception{
        categoryService.deleteCategory(categoryDto);
    }

    public List<CategoryDto> getAllCatergories() throws  Exception{

        return categoryService.getAllCatergories();

    }


    public CategoryDto getCategoryByName(String catName) throws Exception {
        return categoryService.getCategoryByName(catName);
    }
}
