package lk.carhire.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class RentDto {

    private Date rentDate;
    private Date startDate;
    private Date endDate;
    private Double advancePayment;
    private Double depositAmount;
    private Integer customerId;
    private Integer carCategory;
    private String carNumber;
    private Double advancedPayment;
    private Double rate;
    private Double total;

}
