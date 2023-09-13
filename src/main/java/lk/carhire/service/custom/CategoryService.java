package lk.carhire.service.custom;

import lk.carhire.dto.CategoryDto;
import lk.carhire.service.SuperService;

public interface CategoryService extends SuperService {

    public Integer saveCategory(CategoryDto category) throws Exception;
}
