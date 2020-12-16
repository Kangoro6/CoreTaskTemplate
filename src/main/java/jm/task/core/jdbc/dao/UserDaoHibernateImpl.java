package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {
    SessionFactory sessionFactory = getSessionFactory();
    Connection connection = getConnection();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Statement statement;
        String sql = "create table if not exists users\n" +
                "(\n" +
                "\tid int not null auto_increment,\n" +
                "\tname VARCHAR(40) not null,\n" +
                "\tlastName VARCHAR(40) not null,\n" +
                "\tage int null,\n" +
                "\tconstraint users_pk\n" +
                "\t\tprimary key (id)\n" +
                ");";
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Statement statement;
        String dropSql = "drop table if exists users";

        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate(dropSql);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = new User(name, lastName, age);

        session.save(user);

        session.getTransaction().commit();

        System.out.println("User с именем " + name + " добавлен в таблицу.");
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        User user = session.get(User.class, id);
        session.delete(user);

        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<User> usersList = session.createQuery("from User").getResultList();

        for (User u : usersList) {
            System.out.println(u);
        }

        session.getTransaction().commit();
        return usersList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        session.createQuery("delete User").executeUpdate();

        session.getTransaction().commit();
    }
}
