package lk.ijse.hostel.dao.custom;

import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.entity.Room;

import java.sql.SQLException;
import java.util.List;

public interface RoomDAO extends CrudDAO<Room,String>{

    List<Room> getAllRoom() throws SQLException, ClassNotFoundException ;
}
