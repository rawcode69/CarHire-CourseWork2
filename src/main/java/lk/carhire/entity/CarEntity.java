package lk.carhire.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "car")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class CarEntity {
    @Id
    @Column(name = "CarId",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "number",length = 10,nullable = false, unique = true)
    private String number;

    @Column(name = "brand",length = 20,nullable = false)
    private String brand;

    @Column(name = "model",length = 20,nullable = false)
    private String model;

    @Column(name = "year",nullable = false)
    private Integer year;

    @Column(name = "rate",nullable = false)
    private Double rate;

    @Column(name = "isRentable",nullable = false)
    private Boolean isRentable;

    @Column(name = "depositAmount", nullable = false)
    private Double depositAmount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CatId",nullable = false)
    private CategoryEntity categoryEntity;

    @OneToMany(mappedBy = "carEntity",targetEntity = RentEntity.class)
    List<RentEntity> rentEntities;

}
