package lk.carhire.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString


public class CarDto {

    private Integer id;
    private String number;
    private String brand;
    private String model;
    private Integer year;
    private Double rate;
    private Integer catId;


    public CarDto(String number, String brand, String model, Integer year, Double rate, Integer catId) {
        this.number = number;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.rate = rate;
        this.catId = catId;
    }



}
