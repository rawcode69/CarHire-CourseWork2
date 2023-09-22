package lk.carhire.dao;

import lk.carhire.dao.custom.impl.*;

public class DaoFactory {

    private static DaoFactory daoFactory;

    private DaoFactory(){

    }

    public static DaoFactory getInstance(){
        return daoFactory == null
                ? daoFactory = new DaoFactory()
                : daoFactory;
    }

    public SuperDao getDao(DaoType type){
        switch (type){
            case CATEGORY:
                return new CategoryDaoImpl();
            case CUSTOMER:
                return new CustomerDaoImpl();
            case CAR:
                return new CarDaoImpl();
            case USER:
                return new UserDaoImpl();
            case RENT:
                return new RentDaoImpl();
            default:
                return null;
        }
    }

    public enum DaoType{
        CATEGORY,CUSTOMER,CAR,USER,RENT
    }

}
