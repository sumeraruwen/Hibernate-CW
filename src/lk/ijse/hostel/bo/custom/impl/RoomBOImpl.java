package lk.ijse.hostel.bo.custom.impl;

import lk.ijse.hostel.bo.custom.RoomBO;
import lk.ijse.hostel.dao.DAOFactory;
import lk.ijse.hostel.dao.custom.RoomDAO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomBOImpl implements RoomBO {
    // private RoomDAO roomDAO = new RoomDAOImpl();
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);


     public List<RoomDTO> getAllItems() throws SQLException, ClassNotFoundException {
         List<Room> all = roomDAO.getAllRoom();
         ArrayList<RoomDTO> allItems = new ArrayList<>();
         for (Room room : all) {
             allItems.add(new RoomDTO(room.getCode(),room.getType(),room.getKeyMoney(),room.getQty()));
         }
         return allItems;
        // return null;
     }

     public boolean deleteItem(String code) throws SQLException, ClassNotFoundException{
        return roomDAO.delete(code);

     }

     public boolean saveItem(RoomDTO dto) throws SQLException, ClassNotFoundException{
          return roomDAO.save(new Room(dto.getCode(),dto.getType(),dto.getKeyMoney(),dto.getQty()));

     }

     public boolean updateItem(RoomDTO dto) throws SQLException, ClassNotFoundException{
          return roomDAO.update(new Room(dto.getCode(),dto.getType(),dto.getKeyMoney(),dto.getQty()));

     }
     public boolean itemExist(String code) throws SQLException, ClassNotFoundException {
          return roomDAO.exist(code);
     }

     /*public String generateNewId() throws SQLException, ClassNotFoundException {
          return roomDAO.generateNewId();
     }*/

}
