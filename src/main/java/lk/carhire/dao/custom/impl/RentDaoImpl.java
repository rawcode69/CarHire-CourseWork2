package lk.carhire.dao.custom.impl;

import lk.carhire.dao.custom.RentDao;
import lk.carhire.entity.RentEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RentDaoImpl implements RentDao {
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
    public RentEntity get(Integer integer) throws Exception {
        return null;
    }

    @Override
    public List<RentEntity> getAll() throws Exception {
        return null;
    }

    @Override
    public Integer add(RentEntity rentEntity, Session session) {

            Integer id = (Integer) session.save(rentEntity);
            return id;

    }
}
