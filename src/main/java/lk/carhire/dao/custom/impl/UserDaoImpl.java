package lk.carhire.dao.custom.impl;

import lk.carhire.dao.custom.UserDao;
import lk.carhire.entity.UserEntity;
import lk.carhire.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    Session session = SessionFactoryConfiguration.getInstance().getSession();

    @Override
    public Integer add(UserEntity userEntity) throws Exception {

        Transaction transaction = session.beginTransaction();
        try {
            Integer id = (Integer) session.save(userEntity);
            transaction.commit();
            return id;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public void update(UserEntity userEntity) throws Exception {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(userEntity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public void delete(UserEntity userEntity) throws Exception {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(userEntity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public UserEntity get(Integer id) throws Exception {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        UserEntity userEntity = session.get(UserEntity.class, id);
        session.close();
        return userEntity;
    }

    @Override
    public List<UserEntity> getAll() throws Exception {
        String hql = "FROM UserEntity";
        Query query = session.createQuery(hql);
        List<UserEntity> userEntities = query.list();
        return userEntities;
    }

    @Override
    public UserEntity getUserByUserName(String userName) {

        String hql = "FROM UserEntity user WHERE user.userName = :UserName";

        UserEntity userEntity = session.createQuery(hql, UserEntity.class).
                setParameter("UserName", userName).uniqueResult();

        return userEntity;
    }
}
