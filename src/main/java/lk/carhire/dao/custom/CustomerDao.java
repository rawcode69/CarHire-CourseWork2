package lk.carhire.dao.custom;

import lk.carhire.dao.CrudDao;
import lk.carhire.dao.SuperDao;
import lk.carhire.entity.CustomerEntity;
import org.hibernate.Session;

public interface CustomerDao extends CrudDao<CustomerEntity,Integer> {
    CustomerEntity get(Integer customerId, Session session);

    void update(CustomerEntity customerEntity, Session session);
}
