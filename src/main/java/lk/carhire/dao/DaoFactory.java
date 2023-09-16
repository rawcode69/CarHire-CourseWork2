package lk.carhire.dao;

import lk.carhire.dao.custom.impl.CarDaoImpl;
import lk.carhire.dao.custom.impl.CategoryDaoImpl;
import lk.carhire.dao.custom.impl.CustomerDaoImpl;

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
            default:
                return null;
        }
    }

    public enum DaoType{
        CATEGORY,CUSTOMER,CAR
    }

}
