package lk.carhire.controller;

import lk.carhire.dto.RentDto;
import lk.carhire.service.ServiceFactory;
import lk.carhire.service.custom.RentService;

public class RentController {

RentService rentService = (RentService) ServiceFactory.getInstance().getService(ServiceFactory.serviceType.RENT);
    public String placeRent(RentDto rentDto) throws Exception {
         return rentService.placeRent(rentDto);

    }
}
