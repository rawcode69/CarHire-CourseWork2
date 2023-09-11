package lk.carhire.service;

import lk.carhire.service.custom.impl.CategoryServiceImpl;

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
         default:return null;
     }
    }

    public enum serviceType{
        CATEGORY
    }
}
