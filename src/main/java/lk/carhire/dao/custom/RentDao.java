package lk.carhire.dao.custom;

import lk.carhire.dao.CrudDao;
import lk.carhire.entity.RentEntity;
import org.hibernate.Session;

public interface RentDao extends CrudDao <RentEntity, Integer> {

    Integer add(RentEntity rentEntity, Session session);
}
