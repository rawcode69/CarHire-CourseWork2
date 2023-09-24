package lk.carhire.service.custom;

import lk.carhire.dto.OngoingRentDto;
import lk.carhire.dto.RentDto;
import lk.carhire.service.SuperService;

import java.util.List;

public interface RentService extends SuperService {
    String placeRent(RentDto rentDto) throws Exception;

    List<RentDto> getAllActiveRents() throws Exception;



    RentDto getRent(Integer rentId) throws Exception;

    String closeRent(OngoingRentDto ongoingRentDto) throws Exception;
}
