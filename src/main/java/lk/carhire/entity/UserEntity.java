package lk.carhire.entity;

import jakarta.persistence.*;
import lk.carhire.entity.embedded.UserFullName;
import lombok.*;

@Entity
@Table(name = "user")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class UserEntity {
    @Id
    @Column(name = "UserId",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "UserName",length = 50,nullable = false,unique = true)
    private String userName;

    @Column(name = "password",length = 100,nullable = false)
    private String password;

    private UserFullName fullName;

    @Column(name = "role",length = 20,nullable = false)
    private String role;

}
