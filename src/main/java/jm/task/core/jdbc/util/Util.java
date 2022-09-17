package jm.task.core.jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import jm.task.core.jdbc.model.User;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class Util {
    // реализуйте настройку соеденения с БД

    private static final String url = "jdbc:mysql://127.0.0.1:3306/my_db_users";
    private static final String username = "root";
    private static final String password = "aurum_kata_sql_root_447_2022";

    private static Connection connection;
    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /*public static void main(String[] args) throws SQLException {
        Util util = new Util();
        util.getConnection();
    }
    public static Connection getConnection() {

        Connection connection = null;
        Driver driver = null;
        try {
            driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }*/


    //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //
    //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //

    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration cfg = new Configuration();
            Properties prop = new Properties();
            prop.put(Environment.URL, url);
            prop.put(Environment.USER, username);
            prop.put(Environment.PASS, password);
            prop.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            prop.put(Environment.SHOW_SQL, "true");
            prop.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            prop.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
            prop.put(Environment.HBM2DDL_AUTO, "create");

            cfg.setProperties(prop);
            cfg.addAnnotatedClass(User.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(cfg.getProperties()).build();
            sessionFactory = cfg.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }


    //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //
    //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //


}
