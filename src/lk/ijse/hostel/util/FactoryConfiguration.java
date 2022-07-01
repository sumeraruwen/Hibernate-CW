package lk.ijse.hostel.util;

import lk.ijse.hostel.entity.ReserveDetail;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        // configure() -> load and config Hibernate.cfg.xml file to SessionFactory
        // addAnnotatedClass() -> define which Entity that gonna use to Persist
      /*  Configuration configuration = new Configuration().configure().addAnnotatedClass(Student.class)
                .addAnnotatedClass(Room.class).addAnnotatedClass(ReserveDetail.class);

        // build a SessionFactory and assign it to sessionFactory reference
        sessionFactory = configuration.buildSessionFactory();

        Properties properties = new Properties();
        try {
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        Configuration configuration = new Configuration();
        Properties p = new Properties();
        try {
            p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));
        } catch (IOException ignored) {}

        configuration.setProperties(p);
        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(Room.class);
        configuration.addAnnotatedClass(ReserveDetail.class);


        sessionFactory = configuration.buildSessionFactory();

    }
    public static FactoryConfiguration getInstance() {
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration()
                : factoryConfiguration;
    }
    // return a new Session through sessionFactory
    public Session getSession() {
        return sessionFactory.openSession();
    }
}
