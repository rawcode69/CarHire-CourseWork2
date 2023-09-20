package lk.carhire.dto;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class CustomerDto {
    private Integer id;
    private String userName;
    private String email;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String district;
    private String postalCode;
    private String mobile;
    private Date toReturn;

    public CustomerDto(String userName, String email, String firstName, String lastName, String street, String city, String district, String postalCode, String mobile) {
        this.userName = userName;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.district = district;
        this.postalCode = postalCode;
        this.mobile = mobile;
    }
}
