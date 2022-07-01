package lk.ijse.hostel.bo;

import lk.ijse.hostel.bo.custom.impl.ReserveDetailBOImpl;
import lk.ijse.hostel.bo.custom.impl.RoomBOImpl;
import lk.ijse.hostel.bo.custom.impl.StudentBOImpl;
import lk.ijse.hostel.dao.DAOFactory;
import lk.ijse.hostel.dao.SuperDAO;
import lk.ijse.hostel.dao.custom.impl.ReserveDetailDAOImpl;
import lk.ijse.hostel.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hostel.dao.custom.impl.StudentDAOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){

    }
    public static BOFactory getBoFactory(){
        if(boFactory == null){
            boFactory = new BOFactory();
        }
        return boFactory;
    }
    public enum BOTypes{
        STUDENT ,ROOM,RESERVEDETAIL
    }

    public SuperBO getBO(BOFactory.BOTypes types){
        switch (types){
            case STUDENT:
                return new StudentBOImpl();
            case ROOM:
                return new RoomBOImpl();
            case RESERVEDETAIL:
                return new ReserveDetailBOImpl();
            default:
                return null;
        }

    }
}
