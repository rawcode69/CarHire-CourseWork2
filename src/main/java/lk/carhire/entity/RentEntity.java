package lk.carhire.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "rent")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class RentEntity {
    @Id
    @Column(name = "RentId",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rentDate",nullable = false,columnDefinition = "date")
    private Date date;

    @Column(name = "StartDate",nullable = false,columnDefinition = "date")
    private Date startDate;

    @Column(name = "EndDate",nullable = false,columnDefinition = "date")
    private Date endDate;

    @Column(name = "rate",nullable = false)
    private Double rate;

    @Column(name = "total",nullable = false)
    private Double total;

    @Column(name = "deposit",nullable = false)
    private Double deposit;

    @Column(name = "advancedPayment",nullable = false)
    private Double advancedPayment;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CarId",nullable = false)
    private CarEntity carEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CustId",nullable = false)
    private CustomerEntity customerEntity;


}
