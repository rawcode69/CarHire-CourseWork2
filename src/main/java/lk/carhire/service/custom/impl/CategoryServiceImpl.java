package lk.carhire.service.custom.impl;

import lk.carhire.dao.DaoFactory;
import lk.carhire.dao.custom.CategoryDao;
import lk.carhire.dto.CategoryDto;
import lk.carhire.entity.CategoryEntity;
import lk.carhire.service.custom.CategoryService;

public class CategoryServiceImpl implements CategoryService {

    CategoryDao categoryDao = (CategoryDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.CATEGORY);
    @Override
    public Integer saveCategory(CategoryDto category) throws Exception {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(category.getName());
        return categoryDao.add(categoryEntity);
    }
}
