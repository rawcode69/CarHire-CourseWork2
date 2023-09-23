package lk.carhire.dao.custom;

import lk.carhire.dao.CrudDao;
import lk.carhire.dao.SuperDao;
import lk.carhire.entity.CategoryEntity;

public interface CategoryDao extends CrudDao<CategoryEntity,Integer> {


    CategoryEntity getCategoryByName(String catName);
}
