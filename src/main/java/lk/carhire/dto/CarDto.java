package lk.carhire.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString

public class CarDto {

    Integer id;
    String number;
    String brand;
    String model;
    Integer year;
    Double rate;

    public CarDto() {
    }

    public CarDto(Integer id, String number, String brand, String model, Integer year, Double rate) {
        this.id = id;
        this.number = number;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.rate = rate;
    }

}
