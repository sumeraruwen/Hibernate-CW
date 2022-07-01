package lk.ijse.hostel.bo.custom;

import lk.ijse.hostel.bo.SuperBO;
import lk.ijse.hostel.dto.StudentDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface StudentBO extends SuperBO {
    public List<StudentDTO> getAllCustomers() throws SQLException, ClassNotFoundException;

    public boolean saveCustomer(StudentDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean updateCustomer(StudentDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean customerExist(String id) throws SQLException, ClassNotFoundException ;

    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

   // public String generateNewId() throws SQLException, ClassNotFoundException ;
}
