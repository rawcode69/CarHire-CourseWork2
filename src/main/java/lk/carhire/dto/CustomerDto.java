package lk.carhire.dto;

import lombok.*;

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

}
