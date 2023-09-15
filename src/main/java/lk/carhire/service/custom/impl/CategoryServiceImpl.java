package lk.carhire.service.custom.impl;

import lk.carhire.dao.DaoFactory;
import lk.carhire.dao.custom.CategoryDao;
import lk.carhire.dto.CategoryDto;
import lk.carhire.entity.CategoryEntity;
import lk.carhire.service.custom.CategoryService;

import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    CategoryDao categoryDao = (CategoryDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.CATEGORY);
    @Override
    public Integer saveCategory(CategoryDto category) throws Exception {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(category.getName());
        return categoryDao.add(categoryEntity);
    }

    @Override
    public void updateCategory(CategoryDto category) throws Exception {
        CategoryEntity categoryEntity = new CategoryEntity(category.getId(),category.getName());
        categoryDao.update(categoryEntity);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) throws Exception {
        CategoryEntity categoryEntity = categoryDao.get(categoryId);
        CategoryDto categoryDto = new CategoryDto(categoryEntity.getId(),categoryEntity.getName());
        return categoryDto;
    }

    @Override
    public void deleteCategory(CategoryDto categoryDto) throws Exception {
        CategoryEntity categoryEntity = new CategoryEntity(categoryDto.getId(),categoryDto.getName());
        categoryDao.delete(categoryEntity);

    }

    @Override
    public List<CategoryDto> getAllCatergories() throws Exception {
        List <CategoryEntity> categoryEntities = categoryDao.getAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();

        for(CategoryEntity categoryEntity:categoryEntities){
            CategoryDto categoryDto = new CategoryDto(categoryEntity.getId(),categoryEntity.getName());
            categoryDtos.add(categoryDto);
        }
        return categoryDtos;
    }


}
