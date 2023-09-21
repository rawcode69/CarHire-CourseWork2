package lk.carhire.dao.custom;

import lk.carhire.dao.CrudDao;
import lk.carhire.dao.SuperDao;
import lk.carhire.dto.CarDto;
import lk.carhire.entity.CarEntity;

import java.util.List;

public interface CarDao extends CrudDao <CarEntity,Integer> {

    public List <CarEntity> getCarsByCategory(String category) throws Exception;

    public CarEntity getCarByCarNumber(String number) throws  Exception ;


}
