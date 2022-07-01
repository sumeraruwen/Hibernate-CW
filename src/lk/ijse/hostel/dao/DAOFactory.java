package lk.ijse.hostel.dao;

import lk.ijse.hostel.dao.custom.impl.ReserveDetailDAOImpl;
import lk.ijse.hostel.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hostel.dao.custom.impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }
    public static DAOFactory getDaoFactory(){
        if(daoFactory == null){
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }
    public enum DAOTypes{
        STUDENT ,ROOM,RESERVEDETAIL
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types){
            case STUDENT:
                return new StudentDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case RESERVEDETAIL:
                return new ReserveDetailDAOImpl();
            default:
                return null;
        }

    }
}
