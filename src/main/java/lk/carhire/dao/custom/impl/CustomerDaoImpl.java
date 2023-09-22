package lk.carhire.dao.custom.impl;

import lk.carhire.dao.custom.CustomerDao;
import lk.carhire.entity.CustomerEntity;
import lk.carhire.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    Session session = SessionFactoryConfiguration.getInstance().getSession();
    @Override
    public Integer add(CustomerEntity customerEntity) throws Exception {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Integer id = (Integer) session.save(customerEntity);
            transaction.commit();
            return id;
        }catch (Exception e){
            transaction.rollback();
            return -1;
        }
    }

    @Override
    public void update(CustomerEntity customerEntity) throws Exception {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(customerEntity);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public void delete(CustomerEntity customerEntity) throws Exception {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(customerEntity);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            throw e;
        }
    }

    @Override

    public CustomerEntity get(Integer id) throws Exception {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        CustomerEntity customerEntity = session.get(CustomerEntity.class,id);
        return customerEntity;

    }

    @Override
    public List<CustomerEntity> getAll() throws Exception {

        String hql = "FROM CustomerEntity";
        Query query = session.createQuery(hql);
        List <CustomerEntity> customerEntities =  query.list();
        return customerEntities;
    }

    @Override
    public CustomerEntity get(Integer customerId, Session session) {
        return null;
    }
}
