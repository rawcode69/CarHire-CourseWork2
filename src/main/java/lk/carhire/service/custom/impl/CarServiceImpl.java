package lk.carhire.service.custom.impl;

import lk.carhire.dao.DaoFactory;
import lk.carhire.dao.custom.CarDao;
import lk.carhire.dao.custom.CategoryDao;
import lk.carhire.dto.CarDto;
import lk.carhire.entity.CarEntity;
import lk.carhire.entity.CategoryEntity;
import lk.carhire.service.custom.CarService;

import java.util.ArrayList;
import java.util.List;

public class CarServiceImpl implements CarService {

    CarDao carDao = (CarDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.CAR);
    CategoryDao categoryDao = (CategoryDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.CATEGORY);

    @Override
    public Integer saveCar(CarDto carDto) throws Exception {

        CategoryEntity categoryEntity = categoryDao.get(carDto.getCatId());

        CarEntity carEntity = new CarEntity();
        carEntity.setNumber(carDto.getNumber());
        carEntity.setModel(carDto.getModel());
        carEntity.setBrand(carDto.getBrand());
        carEntity.setRate(carDto.getRate());
        carEntity.setYear(carDto.getYear());
        carEntity.setIsRentable(true);
        carEntity.setDepositAmount(carDto.getDepositAmount());
        carEntity.setCategoryEntity(categoryEntity);

        return carDao.add(carEntity);

    }

    @Override
    public void updateCar(CarDto carDto) throws Exception {

        CategoryEntity categoryEntity = categoryDao.get(carDto.getCatId());

        CarEntity carEntity = new CarEntity();
        carEntity.setId(carDto.getId());
        carEntity.setNumber(carDto.getNumber());
        carEntity.setModel(carDto.getModel());
        carEntity.setBrand(carDto.getBrand());
        carEntity.setRate(carDto.getRate());
        carEntity.setYear(carDto.getYear());
        carEntity.setDepositAmount(carDto.getDepositAmount());
        carEntity.setCategoryEntity(categoryEntity);

        carDao.update(carEntity);
    }

    @Override
    public CarDto getCar(Integer id) throws Exception {

        CarEntity carEntity = carDao.get(id);

        CarDto carDto = new CarDto();
        carDto.setNumber(carEntity.getNumber());
        carDto.setBrand(carEntity.getBrand());
        carDto.setModel(carEntity.getModel());
        carDto.setYear(carEntity.getYear());
        carDto.setRate(carEntity.getRate());
        carDto.setCatId(carEntity.getCategoryEntity().getId());
        return carDto;
    }

    @Override
    public void deleteCar(CarDto carDto) throws Exception {

        CategoryEntity categoryEntity = categoryDao.get(carDto.getCatId());

        CarEntity carEntity = new CarEntity();
        carEntity.setId(carDto.getId());
        carEntity.setNumber(carDto.getNumber());
        carEntity.setModel(carDto.getModel());
        carEntity.setBrand(carDto.getBrand());
        carEntity.setRate(carDto.getRate());
        carEntity.setYear(carDto.getYear());
        carEntity.setCategoryEntity(categoryEntity);
        carEntity.setIsRentable(true);
        carEntity.setDepositAmount(carDto.getDepositAmount());

        carDao.delete(carEntity);
    }

    @Override
    public List<CarDto> getAllCars() throws Exception {

        List<CarEntity> carEntities = carDao.getAll();
        List<CarDto> carDtos = new ArrayList<>();

        for (CarEntity carEntity : carEntities) {
            CarDto carDto = new CarDto();
            carDto.setId(carEntity.getId());
            carDto.setNumber(carEntity.getNumber());
            carDto.setIsRentable(carEntity.getIsRentable());
            carDto.setRate(carEntity.getRate());
            carDto.setYear(carEntity.getYear());
            carDto.setBrand((carEntity.getBrand()));
            carDto.setModel(carEntity.getModel());

            carDtos.add(carDto);
        }
        return carDtos;
    }

    @Override
    public List<CarDto> getCarsByCategory(String category) throws Exception {
        List<CarEntity> carEntities = carDao.getCarsByCategory(category);
        List<CarDto> carDtos = new ArrayList<>();

        for (CarEntity carEntity : carEntities) {
            CarDto carDto = new CarDto();
            carDto.setId(carEntity.getId());
            carDto.setNumber(carEntity.getNumber());
            carDto.setIsRentable(carEntity.getIsRentable());
            carDto.setRate(carEntity.getRate());
            carDto.setYear(carEntity.getYear());
            carDto.setBrand((carEntity.getBrand()));
            carDto.setModel(carEntity.getModel());

            carDtos.add(carDto);
        }
        return carDtos;
    }

    @Override
    public CarDto getCarByCarNumber(String carNumber) throws Exception {
        CarEntity carEntity = carDao.getCarByCarNumber(carNumber);

        CarDto carDto = new CarDto();

        carDto.setId(carEntity.getId());
        carDto.setNumber(carEntity.getNumber());
        carDto.setIsRentable(carEntity.getIsRentable());
        carDto.setRate(carEntity.getRate());
        carDto.setYear(carEntity.getYear());
        carDto.setBrand((carEntity.getBrand()));
        carDto.setModel(carEntity.getModel());

        return carDto;
    }
}
