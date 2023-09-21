package lk.carhire.service.custom;

import lk.carhire.dto.CarDto;
import lk.carhire.service.SuperService;

import java.util.List;

public interface CarService extends SuperService {
    Integer saveCar(CarDto carDto) throws Exception;

    void updateCar(CarDto carDto) throws Exception;

    CarDto getCar(Integer id) throws Exception;

    void deleteCar(CarDto carDto) throws Exception;


    List<CarDto> getAllCars() throws Exception;

    List<CarDto> getCarsByCategory(String category) throws Exception;

    CarDto getCarByCarNumber(String carNumber) throws Exception;
}
