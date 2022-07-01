package lk.ijse.hostel.bo.custom;

import lk.ijse.hostel.bo.SuperBO;
import lk.ijse.hostel.dto.ReserveDetailDTO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ReserveDetailBO extends SuperBO {

    public List<StudentDTO> searchCustomer(String id) throws SQLException, ClassNotFoundException ;

    public List<RoomDTO> searchItem(String code) throws SQLException, ClassNotFoundException ;

    public boolean checkItemIsAvailable(String code) throws SQLException, ClassNotFoundException;

    public boolean checkCustomerIsAvailable(String id) throws SQLException, ClassNotFoundException ;

    //public String generateNewId() throws SQLException, ClassNotFoundException;

    public List<StudentDTO> getAllCustomers() throws SQLException, ClassNotFoundException ;

    public List<RoomDTO> getAllItems() throws SQLException, ClassNotFoundException;

    public List<ReserveDetailDTO> getAllReserve() throws SQLException, ClassNotFoundException;
}
