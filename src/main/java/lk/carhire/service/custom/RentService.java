package lk.carhire.service.custom;

import lk.carhire.dto.RentDto;
import lk.carhire.service.SuperService;

public interface RentService extends SuperService {
    String placeRent(RentDto rentDto) throws Exception;
}
