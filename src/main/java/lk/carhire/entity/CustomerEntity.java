package lk.carhire.entity;

import jakarta.persistence.*;
import lk.carhire.entity.embedded.Address;
import lk.carhire.entity.embedded.CustomerFullName;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class CustomerEntity {
    @Id
    @Column(name = "CustId",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "UserName",length = 50,nullable = false)
    private String userName;

    @Column(name = "email",length = 225,nullable = false)
    private String email;

    private CustomerFullName fullName;

    private Address address;

    @Column(name = "postalcode",length = 20,nullable = false)
    private String postalCode;

    @ElementCollection
    @CollectionTable(
            name = "mobile",
            joinColumns = @JoinColumn(name = "CustId")
    )
    private List <String> mobiles;

    @CreationTimestamp
    @Column(name= "createDate", nullable = false)
    private Date createDate;

    @OneToMany(mappedBy = "customerEntity",targetEntity = RentEntity.class)
    List<RentEntity> rentEntities;


}
