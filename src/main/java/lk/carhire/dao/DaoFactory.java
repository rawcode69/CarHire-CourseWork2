package lk.carhire.dao;

import lk.carhire.dao.custom.impl.CategoryDaoImpl;

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
            default:
                return null;
        }
    }

    public enum DaoType{
        CATEGORY
    }

}
