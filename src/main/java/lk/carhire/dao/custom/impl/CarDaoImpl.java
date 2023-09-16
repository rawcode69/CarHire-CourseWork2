package lk.carhire.dao.custom.impl;

import lk.carhire.dao.custom.CarDao;
import lk.carhire.entity.CarEntity;
import lk.carhire.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class CarDaoImpl implements CarDao {

    Session session = SessionFactoryConfiguration.getInstance().getSession();

    @Override
    public Integer add(CarEntity carEntity) throws Exception {

        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Integer id = (Integer) session.save(carEntity);
            transaction.commit();
            return id;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }

    }

    @Override
    public void update(CarEntity carEntity) throws Exception {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String sql = "UPDATE car SET CatId = :newCatId,rate = :newRate, year = :newYear, number = :newNumber, brand = :newBrand, model = :newModel WHERE CarId = :carId";
        try {
            Query query = session.createNativeQuery(sql);
            query.setParameter("newCatId", carEntity.getCategoryEntity().getId());
            query.setParameter("newRate", carEntity.getRate());
            query.setParameter("newYear", carEntity.getYear());
            query.setParameter("newNumber", carEntity.getNumber());
            query.setParameter("newBrand", carEntity.getBrand());
            query.setParameter("newModel", carEntity.getModel());
            query.setParameter("carId", carEntity.getId());
            Integer updated = query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }

    }

    @Override
    public void delete(CarEntity carEntity) throws Exception {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(carEntity);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public CarEntity get(Integer id) throws Exception {
        CarEntity carEntity = session.get(CarEntity.class, id);
        return carEntity;
    }

    @Override
    public ArrayList<CarEntity> getAll() throws Exception {
        return null;
    }
}
