package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/db_test?useUnicode=true&serverTimezone=UTC&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Guard778";
    public Connection getConnection() {
        Connection connection = null;
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
//    private static SessionFactory sessionFactory;
//    public static SessionFactory getSessionFactory() {
//        if (sessionFactory == null) {
//            try {
//                Configuration configuration = new Configuration();
//
//                Properties settings = new Properties();
//                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver" );
//                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/db_test?useUnicode=true&serverTimezone=UTC&useSSL=false" );
//                settings.put(Environment.USER, "root" );
//                settings.put(Environment.PASS, "Guard778" );
//                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect" );
//            }
//        }
//    }

}
