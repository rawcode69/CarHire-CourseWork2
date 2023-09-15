package lk.carhire.dao.custom.impl;

import lk.carhire.dao.custom.CarDao;
import lk.carhire.dao.custom.CategoryDao;
import lk.carhire.entity.CarEntity;
import lk.carhire.entity.CategoryEntity;
import lk.carhire.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

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
        Transaction transaction = session.beginTransaction();
        try {
            session.update(categoryEntity);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public void delete(CategoryEntity categoryEntity) throws Exception {
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(categoryEntity);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public CategoryEntity get(Integer id) throws Exception {
        CategoryEntity categoryEntity = session.get(CategoryEntity.class,id);
        return categoryEntity;
    }


    @Override
    public ArrayList<CategoryEntity> getAll() throws Exception {

        String hql = "FROM CategoryEntity";
        Query query =  session.createQuery(hql);
        List <CategoryEntity> categoryEntities = query.list();
        return (ArrayList<CategoryEntity>) categoryEntities;
    }
}
