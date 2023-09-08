package lk.carhire.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

public class CustomerDto {
    Integer id;
    String userName;
    String email;
    String firstName;
    String lastName;
    String street;
    String city;
    String district;
    String postalCode;

    public CustomerDto() {
    }

    public CustomerDto(Integer id, String userName, String email, String firstName, String lastName, String street, String city, String district, String postalCode) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.district = district;
        this.postalCode = postalCode;
    }


}
