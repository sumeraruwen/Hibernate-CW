package lk.ijse.hostel.dao.custom.impl;

//import lk.ijse.hostel.dao.SQLUtil;
import lk.ijse.hostel.dao.custom.StudentDAO;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public List<Student> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction t4 = session.beginTransaction();

        Query q1 = session.createQuery("FROM Student");
        List<Student> list = q1.list();

        t4.commit();
        session.close();
        return list;
       /* ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Student");
        ArrayList<Student> allStudents = new ArrayList<>();
        while(rst.next()){
            allStudents.add(new Student( rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4)));
        }
        return allStudents;*/
       // return null;
    }
/*
    @Override
    public List<Room> getAllCodes() throws SQLException, ClassNotFoundException {
        return null;
    }*/

  /*  @Override
    public List<Student> getAllId() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction t5 = session.beginTransaction();

        Query q2 = session.createQuery("SELECT id FROM Student");
        List<Student> list = q2.list();

        t5.commit();
        session.close();
        return list;
       // return null;
    }*/

  /*  @Override
    public List<ReserveDetail> getAllReserve() throws SQLException, ClassNotFoundException {
        return null;
    }*/

  /*  @Override
    public List<Room> getAllRoom() throws SQLException, ClassNotFoundException {
        return null;
    }*/

    @Override
    public boolean save(Student entity) throws SQLException, ClassNotFoundException {
       //return SQLUtil.executeUpdate("INSERT INTO Student (id, name, address, contact) VALUES (?,?,?,?)",entity.getId(),entity.getName(),entity.getAddress(),entity.getContact());
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction t1 = session.beginTransaction();

        session.save(entity);
       // System.out.println(entity.toString());

        t1.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student entity) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction t2 = session.beginTransaction();

        session.update(entity);
        //System.out.println(entity.toString());

        t2.commit();
        session.close();
        return true;
     //  return SQLUtil.executeUpdate("UPDATE Student SET name =?, address=?, contact=? WHERE id=?",entity.getName(),entity.getAddress(),entity.getContact(),entity.getId());
    }

    @Override
    public List search(String id) throws SQLException, ClassNotFoundException {

       /* ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Student WHERE id=?", id);
        if(rst.next()){
            return new Student(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4));
        }*/
        //
        // return null;
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction t9 = session.beginTransaction();

        Query q1 = session.createQuery("FROM Student");
        List<Student> list = q1.list();

        t9.commit();
        session.close();
        return list;

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return true;
       /* Session session = FactoryConfiguration.getInstance().getSession();
        Transaction t5 = session.beginTransaction();
        Student s1 = session.get(Student.class, id);
        t5.commit();
        session.close();
        return true;*/
      // return SQLUtil.executeQuery("SELECT id FROM Student WHERE id=?",id).next();
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction t3 = session.beginTransaction();

        Student stu = session.load(Student.class, id);
        session.delete(stu);

        t3.commit();
        session.close();
        return true;

     //  return SQLUtil.executeUpdate("DELETE FROM Student WHERE id=?",id);
    }

  /*  @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT id FROM Student ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newItemId = Integer.parseInt(id.replace("S00-", "")) + 1;
            return String.format("S00-%03d", newItemId);
        } else {
            return "S00-001";
        }


    }*/
}
