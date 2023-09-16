package lk.carhire.service.custom;

import lk.carhire.dto.CarDto;
import lk.carhire.service.SuperService;

public interface CarService extends SuperService {
    Integer saveCar(CarDto carDto) throws Exception;

    void updateCar(CarDto carDto) throws Exception;

    CarDto getCar(Integer id) throws Exception;

    void deleteCar(CarDto carDto) throws Exception;
}
