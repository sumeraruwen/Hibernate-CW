package lk.ijse.hostel.dao.custom.impl;

//import lk.ijse.hostel.dao.SQLUtil;
import lk.ijse.hostel.dao.custom.RoomDAO;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.sql.*;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {


    @Override
    public List<Room> getAll() throws SQLException, ClassNotFoundException {

       /* ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Room");
        ArrayList<Room> allRooms = new ArrayList<>();
            while (rst.next()){

                allRooms.add(new Room(rst.getString(1),rst.getString(2),rst.getBigDecimal(3), rst.getInt(4)));
            }
            return allRooms;*/
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction t4 = session.beginTransaction();

        Query q1 = session.createQuery("FROM Room ");
        List<Room> list1 = q1.getResultList();

        t4.commit();
        session.close();
        return list1;
       //return null;
    }

   /* @Override
    public List<Room> getAllCodes() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction t5 = session.beginTransaction();

        org.hibernate.query.Query q2 = session.createQuery("SELECT code FROM Room");
        List<Room> list = q2.list();

        t5.commit();
        session.close();
        return list;
       // return null;
    }*/

   /* @Override
    public List<Student> getAllId() throws SQLException, ClassNotFoundException {
        return null;
    }*/

  /*  @Override
    public List<ReserveDetail> getAllReserve() throws SQLException, ClassNotFoundException {
        return null;
    }*/

    @Override
    public List<Room> getAllRoom() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction t4 = session.beginTransaction();

        Query q1 = session.createQuery("FROM Room ");
        List<Room> list1 = q1.getResultList();

        t4.commit();
        session.close();
        return list1;
    }

    @Override
    public boolean save(Room entity) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction t1 = session.beginTransaction();

        session.save(entity);
        System.out.println(entity.toString());

        t1.commit();
        session.close();
        return true;
        //  return SQLUtil.executeUpdate("INSERT INTO Room (code, type, keyMoney, qty) VALUES (?,?,?,?)",entity.getCode(),entity.getType(),entity.getKeyMoney(),entity.getQty());
    }

    @Override
    public boolean update(Room entity) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction t2 = session.beginTransaction();

        session.update(entity);
       // System.out.println(entity.toString());

        t2.commit();
        session.close();
        return true;
      // return SQLUtil.executeUpdate("UPDATE Room SET type =?, keyMoney=?, qty=? WHERE code=?",entity.getType(),entity.getKeyMoney(),entity.getQty(),entity.getCode());

    }

    @Override
    public List search(String code) throws SQLException, ClassNotFoundException {

       /* ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Room WHERE code=?", code);
        if(rst.next()){
            return new Room(rst.getString(1),rst.getString(2),rst.getBigDecimal(3),rst.getInt(4));
        }*/
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction t9 = session.beginTransaction();

        org.hibernate.query.Query q1 = session.createQuery("FROM Room");
        List<Student> list = q1.list();

        t9.commit();
        session.close();
        return list;
    }

    @Override
    public boolean exist(String code) throws SQLException, ClassNotFoundException {
       return true;
      // return SQLUtil.executeQuery("SELECT code FROM Room WHERE code=?",code).next();
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction t3 = session.beginTransaction();

        Room stu = session.get(Room.class, code);
        session.delete(stu);

        t3.commit();
        session.close();
        return true;

      // return SQLUtil.executeUpdate("DELETE FROM Room WHERE code=?",code);
    }

   /* @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.executeQuery("SELECT code FROM Room ORDER BY code DESC LIMIT 1;");

        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }

    }*/
}
