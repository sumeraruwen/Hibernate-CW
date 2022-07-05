package lk.ijse.hostel.dao.custom.impl;

//import lk.ijse.hostel.dao.SQLUtil;
import lk.ijse.hostel.dao.custom.ReserveDetailDAO;
//import lk.ijse.hostel.db.DBConnection;
import lk.ijse.hostel.entity.ReserveDetail;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class ReserveDetailDAOImpl implements ReserveDetailDAO {


    @Override
    public List<ReserveDetail> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction t4 = session.beginTransaction();

        Query q1 = session.createQuery("FROM ReserveDetail ");
        List<ReserveDetail> list = q1.list();

        t4.commit();
        session.close();
        return list;

    }

   /* @Override
    public List<Room> getAllCodes() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction t6 = session.beginTransaction();

        org.hibernate.query.Query q1 = session.createQuery("SELECT code FROM Room");
        List<Room> list = q1.list();

        t6.commit();
        session.close();
        return list;
    }

    @Override
    public List<Student> getAllId() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction t5 = session.beginTransaction();

        Query q2 = session.createQuery("SELECT id FROM Student");
        List<Student> list = q2.list();

        t5.commit();
        session.close();
        return list;
    }

    @Override
    public List<ReserveDetail> getAllReserve() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction t4 = session.beginTransaction();

        Query q3 = session.createQuery("FROM ReserveDetail ");
        List<ReserveDetail> list = q3.list();

        t4.commit();
        session.close();
        return list;
    }*/

   /* @Override
    public List<Room> getAllRoom() throws SQLException, ClassNotFoundException {
        return null;
    }*/

    @Override
    public boolean save(ReserveDetail entity) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction t1 = session.beginTransaction();

        session.save(entity);
       // System.out.println(entity.toString());

        t1.commit();
        session.close();
        return true;
     // return SQLUtil.executeUpdate("INSERT INTO ReserveDetail (resId,id,code, qty, keyMoney, status) VALUES (?,?,?,?,?,?)",entity.getResId(),entity.getId(),entity.getCode(),entity.getQty(),entity.getKeyMoney(),entity.getStatus());
    }

    @Override
    public boolean update(ReserveDetail entity) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction t2 = session.beginTransaction();

        session.update(entity);
       // System.out.println(entity.toString());

        t2.commit();
        session.close();
        return true;
     //  return SQLUtil.executeUpdate("UPDATE Room SET qty =?, keyMoney=? WHERE code=?",entity.getKeyMoney(),entity.getQty(),entity.getCode());
    }

    @Override
    public List search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String resId) throws SQLException, ClassNotFoundException {
      return true;
      // return SQLUtil.executeQuery("SELECT resId FROM ReserveDetail WHERE resId=?",resId).next();
    }

    @Override
    public boolean delete(String resId) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction t3 = session.beginTransaction();

        ReserveDetail stu = session.get(ReserveDetail.class, resId);
        session.delete(stu);

        t3.commit();
        session.close();
        return true;
      // return SQLUtil.executeUpdate("DELETE FROM ReserveDetail WHERE resId=?",resId);
    }

   /* @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        *//*Connection connection = DBConnection.getDbConnection().getConnection();
            ResultSet rst = connection.createStatement().executeQuery("SELECT resId FROM ReserveDetail ORDER BY resId DESC LIMIT 1;");
            if (rst.next()) {
                String id = rst.getString("resId");
                int newItemId = Integer.parseInt(id.replace("R00-", "")) + 1;
                return String.format("R00-%03d", newItemId);
            } else {
                return "R00-001";
            }*//*
        ResultSet rst = SQLUtil.executeQuery("SELECT resId FROM ReserveDetail ORDER BY resId DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("resId");
            int newItemId = Integer.parseInt(id.replace("R00-", "")) + 1;
            return String.format("R00-%03d", newItemId);
        } else {
            return "R00-001";
        }
    }*/
}
