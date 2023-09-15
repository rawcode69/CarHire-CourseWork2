package lk.carhire.service.custom;

import lk.carhire.dto.CategoryDto;
import lk.carhire.service.SuperService;

import java.util.List;

public interface CategoryService extends SuperService {

    public Integer saveCategory(CategoryDto category) throws Exception;

    public void updateCategory(CategoryDto category) throws  Exception;


    public  CategoryDto getCategory(Integer categoryId) throws  Exception;


    public void deleteCategory(CategoryDto categoryDto) throws Exception;

    List<CategoryDto> getAllCatergories() throws  Exception;
}
