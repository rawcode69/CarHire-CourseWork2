package lk.carhire.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CarTM {
    private String id;
    private String number;
    private String brand;
    private String model;
    private String rate;
    private String toRent;
}
