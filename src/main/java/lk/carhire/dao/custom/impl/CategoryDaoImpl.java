package lk.carhire.dao.custom.impl;

import lk.carhire.dao.custom.CarDao;
import lk.carhire.dao.custom.CategoryDao;
import lk.carhire.entity.CarEntity;
import lk.carhire.entity.CategoryEntity;
import lk.carhire.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class CategoryDaoImpl implements CategoryDao {

    Session session = SessionFactoryConfiguration.getInstance().getSession();

    @Override
    public Integer add(CategoryEntity categoryEntity) throws Exception {
        Transaction transaction = session.beginTransaction();
        try {
            Integer id = (Integer) session.save(categoryEntity);
            transaction.commit();
            return id;
        }catch (Exception e){
            transaction.rollback();
            return null;
        }

    }

    @Override
    public void update(CategoryEntity categoryEntity) throws Exception {

    }

    @Override
    public void delete(CategoryEntity categoryEntity) throws Exception {

    }

    @Override
    public CategoryEntity get(String s) throws Exception {
        return null;
    }

    @Override
    public ArrayList<CategoryEntity> getAll() throws Exception {
        return null;
    }
}
