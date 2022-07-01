package lk.ijse.hostel.bo.custom.impl;

import lk.ijse.hostel.bo.custom.ReserveDetailBO;
import lk.ijse.hostel.dao.DAOFactory;
import lk.ijse.hostel.dao.SuperDAO;
import lk.ijse.hostel.dao.custom.ReserveDetailDAO;
import lk.ijse.hostel.dao.custom.RoomDAO;
import lk.ijse.hostel.dao.custom.StudentDAO;
import lk.ijse.hostel.dao.custom.impl.ReserveDetailDAOImpl;
import lk.ijse.hostel.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hostel.dao.custom.impl.StudentDAOImpl;
import lk.ijse.hostel.dto.ReserveDetailDTO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.entity.ReserveDetail;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReserveDetailBOImpl implements ReserveDetailBO {
   // private RoomDAO roomDAO = new RoomDAOImpl();
    //private StudentDAO studentDAO = new StudentDAOImpl();
    //private ReserveDetailDAO reserveDetailDAO = new ReserveDetailDAOImpl();
   private StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    private  RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    private ReserveDetailDAO reserveDetailDAO = (ReserveDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVEDETAIL);

    @Override
    public List<StudentDTO> searchCustomer(String id) throws SQLException, ClassNotFoundException {

      /*  Student ent = studentDAO.search(id);
        return new StudentDTO(ent.getId(),ent.getName(),ent.getAddress(),ent.getContact());*/
        List<Student> student = studentDAO.search(id);
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student ent : student){
            studentDTOS.add(new StudentDTO(ent.getId(),ent.getName(),ent.getAddress(),ent.getContact()));
        }
        return studentDTOS;

    }

    @Override
    public List<RoomDTO> searchItem(String code) throws SQLException, ClassNotFoundException {
       /* Room ent = roomDAO.search(code);
        return new RoomDTO(ent.getCode(),ent.getType(),ent.getKeyMoney(),ent.getQty());*/
        List<Room> room = roomDAO.search(code);
        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room ent : room){
            roomDTOS.add(new RoomDTO(ent.getCode(),ent.getType(),ent.getKeyMoney(),ent.getQty()));
        }
        return roomDTOS;
    }

    @Override
    public boolean checkItemIsAvailable(String code) throws SQLException, ClassNotFoundException {
       return roomDAO.exist(code);
    }

    @Override
    public boolean checkCustomerIsAvailable(String id) throws SQLException, ClassNotFoundException {
        return studentDAO.exist(id);
    }

   /* @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
      return  reserveDetailDAO.generateNewId();
    }*/

    @Override
    public List<StudentDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        List<Student> all = studentDAO.getAll();
        ArrayList<StudentDTO> allCustomers = new ArrayList<>();
        for (Student ent : all) {
            allCustomers.add(new StudentDTO(ent.getId(),ent.getName(),ent.getAddress(),ent.getContact()));
        }
        return allCustomers;
       // return null;
    }

    @Override
    public List<RoomDTO> getAllItems() throws SQLException, ClassNotFoundException {
        List<Room> all = roomDAO.getAll();
        ArrayList<RoomDTO> allItems = new ArrayList<>();
        for (Room ent : all) {
            allItems.add(new RoomDTO(ent.getCode(),ent.getType(),ent.getKeyMoney(),ent.getQty()));
        }
        return allItems;
        //return null;
    }

    @Override
    public List<ReserveDetailDTO> getAllReserve() throws SQLException, ClassNotFoundException {
        ArrayList<ReserveDetailDTO> allReserve = new ArrayList<>();
        List<ReserveDetail> all = reserveDetailDAO.getAll();
        for (ReserveDetail reserve : all) {
            allReserve.add(new ReserveDetailDTO(reserve.getResId(),reserve.getId(),reserve.getCode(),reserve.getQty(),reserve.getKeyMoney(),reserve.getStatus()));
        }
        return allReserve;
    }
}
