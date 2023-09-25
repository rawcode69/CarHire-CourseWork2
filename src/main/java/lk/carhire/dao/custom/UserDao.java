package lk.carhire.dao.custom;

import lk.carhire.dao.CrudDao;
import lk.carhire.dao.SuperDao;
import lk.carhire.entity.UserEntity;

public interface UserDao extends CrudDao<UserEntity,Integer> {

    UserEntity getUserByUserName(String userName);
}
