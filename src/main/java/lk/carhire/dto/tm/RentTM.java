package lk.carhire.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class RentTM {
    private String custName;
    private String carNumber;
    private String rate;
    private String nosDays;
    private String advance;
    private String deposit;
    private String totalAmount;
    private String balanceToPay;
}
