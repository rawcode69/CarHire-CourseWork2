package lk.carhire.service;

import lk.carhire.service.custom.impl.CarServiceImpl;
import lk.carhire.service.custom.impl.CategoryServiceImpl;
import lk.carhire.service.custom.impl.CustomerServiceImpl;
import lk.carhire.service.custom.impl.UserServiceImpl;

public class ServiceFactory {

    private static ServiceFactory serviceFactory;
    private ServiceFactory(){

    }

    public static ServiceFactory getInstance(){
        return serviceFactory == null
                ? serviceFactory = new ServiceFactory()
                : serviceFactory;
    }

    public SuperService getService(serviceType type){
     switch (type){
         case CATEGORY:
             return new CategoryServiceImpl();
         case CUSTOMER:
             return new CustomerServiceImpl();
         case CAR:
             return new CarServiceImpl();
         case USER:
             return new UserServiceImpl();
         default:return null;
     }
    }

    public enum serviceType{
        CATEGORY,CUSTOMER,CAR,USER
    }
}
