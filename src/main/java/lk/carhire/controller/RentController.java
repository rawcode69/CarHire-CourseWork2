package lk.carhire.controller;

import lk.carhire.dto.OngoingRentDto;
import lk.carhire.dto.RentDto;
import lk.carhire.service.ServiceFactory;
import lk.carhire.service.custom.RentService;

import java.util.List;

public class RentController {

    RentService rentService = (RentService) ServiceFactory.getInstance().getService(ServiceFactory.serviceType.RENT);

    public String placeRent(RentDto rentDto) throws Exception {
        return rentService.placeRent(rentDto);

    }

    public List<RentDto> getAllActiveRents() throws Exception {
        return rentService.getAllActiveRents();
    }


    public RentDto getRent(Integer rentId) throws Exception {
        return rentService.getRent(rentId);
    }

    public String closeRent(OngoingRentDto ongoingRentDto) throws Exception {
        return rentService.closeRent(ongoingRentDto);
    }
}
