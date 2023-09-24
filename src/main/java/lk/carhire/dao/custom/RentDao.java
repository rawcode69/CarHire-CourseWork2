package lk.carhire.dao.custom;

import lk.carhire.dao.CrudDao;
import lk.carhire.entity.RentEntity;
import org.hibernate.Session;

import java.util.List;

public interface RentDao extends CrudDao <RentEntity, Integer> {

    Integer add(RentEntity rentEntity, Session session);

    List<RentEntity> getAllActive();

    RentEntity get(Integer rentId, Session session);

    void update(RentEntity rentEntity, Session session);
}
