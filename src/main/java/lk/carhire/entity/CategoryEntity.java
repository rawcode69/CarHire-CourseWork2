package lk.carhire.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "carcategory")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class CategoryEntity {

    @Id
    @Column(name = "CatId",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Catname",length = 100, nullable = false)
    private String name;

    @OneToMany(mappedBy = "categoryEntity",targetEntity = CarEntity.class)
    List<CarEntity> carEntities;

    public CategoryEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
