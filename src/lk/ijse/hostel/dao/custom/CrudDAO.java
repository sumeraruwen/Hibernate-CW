package lk.ijse.hostel.dao.custom;

import lk.ijse.hostel.dao.SuperDAO;
//import lk.ijse.hostel.db.DBConnection;
import lk.ijse.hostel.entity.Student;

import java.sql.*;
import java.util.List;

public interface CrudDAO<T,ID>extends SuperDAO {
    // List<Student> getAll() throws SQLException, ClassNotFoundException ;

     List<T> getAll() throws SQLException, ClassNotFoundException ;

/*

     List<Room> getAllCodes() throws SQLException, ClassNotFoundException ;

     List<Student> getAllId() throws SQLException, ClassNotFoundException ;

     List<ReserveDetail> getAllReserve() throws SQLException, ClassNotFoundException ;
*/

     boolean save(T dto) throws SQLException, ClassNotFoundException ;


     boolean update(T dto) throws SQLException, ClassNotFoundException ;

     List search(ID id) throws SQLException, ClassNotFoundException ;


     boolean exist(ID id) throws SQLException, ClassNotFoundException ;


     boolean delete(ID id) throws SQLException, ClassNotFoundException ;


    // String generateNewId() throws SQLException, ClassNotFoundException ;


}
