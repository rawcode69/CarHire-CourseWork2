package lk.carhire.dao.custom.impl;

import lk.carhire.dao.DaoFactory;
import lk.carhire.dao.custom.RentDao;
import lk.carhire.entity.CustomerEntity;
import lk.carhire.entity.RentEntity;
import lk.carhire.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class RentDaoImpl implements RentDao {

    Session session = SessionFactoryConfiguration.getInstance().getSession();

    @Override
    public Integer add(RentEntity rentEntity) throws Exception {
        return null;
    }

    @Override
    public void update(RentEntity rentEntity) throws Exception {

    }

    @Override
    public void delete(RentEntity rentEntity) throws Exception {

    }

    @Override
    public RentEntity get(Integer id) throws Exception {
        RentEntity rentEntity = session.get(RentEntity.class,id);
        return rentEntity;

    }

    @Override
    public List<RentEntity> getAll() throws Exception {
        String hql = "FROM RentEntity";
        Query query = session.createQuery(hql);
        List<RentEntity> rentEntities = query.list();
        return rentEntities;

    }

    @Override
    public Integer add(RentEntity rentEntity, Session session) {

        Integer id = (Integer) session.save(rentEntity);
        return id;

    }

    @Override
    public List<RentEntity> getAllActive() {
        String hql = "FROM RentEntity rent WHERE rent.isActive = true";
        Query query = session.createQuery(hql);
        List<RentEntity> rentEntities = query.list();
        return rentEntities;

    }

    @Override
    public RentEntity get(Integer rentId, Session session) {
        RentEntity rentEntity = session.get(RentEntity.class,rentId);
        return rentEntity;
    }

    @Override
    public void update(RentEntity rentEntity, Session session) {
        session.update(rentEntity);
    }
}
