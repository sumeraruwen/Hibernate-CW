package lk.ijse.hostel.bo.custom.impl;

import lk.ijse.hostel.bo.custom.StudentBO;
import lk.ijse.hostel.dao.DAOFactory;
import lk.ijse.hostel.dao.SuperDAO;
import lk.ijse.hostel.dao.custom.StudentDAO;
import lk.ijse.hostel.dao.custom.impl.StudentDAOImpl;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
  //  private StudentDAO studentDAO = new StudentDAOImpl();
 private StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);


    @Override
    public List<StudentDTO> getAllCustomers() throws SQLException, ClassNotFoundException {

       ArrayList<StudentDTO> allCustomers = new ArrayList<>();
       List<Student> all = studentDAO.getAll();
       for (Student student : all) {
          allCustomers.add(new StudentDTO(student.getId(),student.getName(),student.getAddress(),student.getContact()));
       }
        return allCustomers;
    }

    @Override
    public boolean saveCustomer(StudentDTO dto) throws SQLException, ClassNotFoundException {
      // return studentDAO.save(dto);
       return studentDAO.save(new Student(dto.getId(),dto.getName(),dto.getAddress(),dto.getContact()));
    }

    @Override
    public boolean updateCustomer(StudentDTO dto) throws SQLException, ClassNotFoundException {
        return studentDAO.update(new Student(dto.getId(),dto.getName(),dto.getAddress(),dto.getContact()));
    }

    @Override
    public boolean customerExist(String id) throws SQLException, ClassNotFoundException {
       return studentDAO.exist(id);
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return studentDAO.delete(id);
    }

   /* @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
       return studentDAO.generateNewId();
    }*/
}
