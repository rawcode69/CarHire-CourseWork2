package lk.carhire.controller;

import lk.carhire.dto.CategoryDto;
import lk.carhire.service.ServiceFactory;

import lk.carhire.service.custom.CategoryService;


public class CategoryController {

    CategoryService categoryService = (CategoryService) ServiceFactory.getInstance().getService(ServiceFactory.serviceType.CATEGORY);
    public Integer saveCategory(CategoryDto category) throws Exception {
        Integer id = categoryService.saveCategory(category);
        return id;
    }
}
