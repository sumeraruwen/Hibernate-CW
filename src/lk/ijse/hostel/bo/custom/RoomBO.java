package lk.ijse.hostel.bo.custom;

import lk.ijse.hostel.bo.SuperBO;
import lk.ijse.hostel.dto.RoomDTO;

import java.sql.SQLException;
import java.util.List;

public interface RoomBO extends SuperBO {
     List<RoomDTO> getAllItems() throws SQLException, ClassNotFoundException;

     boolean deleteItem(String code) throws SQLException, ClassNotFoundException;

     boolean saveItem(RoomDTO dto) throws SQLException, ClassNotFoundException;

     boolean updateItem(RoomDTO dto) throws SQLException, ClassNotFoundException;

     boolean itemExist(String code) throws SQLException, ClassNotFoundException ;

     //String generateNewId() throws SQLException, ClassNotFoundException ;
}
