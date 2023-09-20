package lk.carhire.controller;

import lk.carhire.dto.CarDto;
import lk.carhire.service.ServiceFactory;
import lk.carhire.service.custom.CarService;

import java.util.List;

public class CarController {

    CarService carService = (CarService) ServiceFactory.getInstance().getService(ServiceFactory.serviceType.CAR);
    public Integer saveCar(CarDto carDto) throws Exception {
        return carService.saveCar(carDto);
    }

    public void updateCars(CarDto carDto) throws Exception {
        carService.updateCar(carDto);
    }


    public CarDto getCar(Integer id) throws Exception {
        return carService.getCar(id);
    }

    public void deleteCar(CarDto carDto) throws Exception {
        carService.deleteCar(carDto);
    }

    public List<CarDto> getAllCars() throws Exception {
        List<CarDto> carDto = carService.getAllCars();
        return carDto;
    }

    public List<CarDto> getCarsByCategory(String category) throws Exception {
        List<CarDto> carDtos = carService.getCarsByCategory(category);
        return  carDtos;
    }

    public CarDto getCarByCarNumber(String carNumber) {
        CarDto carDto = carService.getCarByCarNumber(carNumber);
        return carDto;
    }
}
