package jm.task.core.jdbc.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UserServiceImpl extends Util implements UserService {
    SessionFactory sessionFactory = getSessionFactory();
    Connection connection = this.getConnection();

    public UserServiceImpl() {
    }

    public void createUsersTable() {
        String sql = "create table if not exists users\n(\n\tid int not null auto_increment,\n\tname VARCHAR(40) not null,\n\tlastName VARCHAR(40) not null,\n\tage int null,\n\tconstraint users_pk\n\t\tprimary key (id)\n);";

        try {
            this.connection.setAutoCommit(false);
            Statement statement = this.connection.createStatement();
            statement.executeUpdate(sql);
            this.connection.commit();
            this.connection.setAutoCommit(true);
        } catch (SQLException var6) {
            try {
                this.connection.rollback();
            } catch (SQLException var5) {
                var5.printStackTrace();
            }

            var6.printStackTrace();
        }

    }

    public void dropUsersTable() {
        String dropSql = "drop table if exists users";

        try {
            this.connection.setAutoCommit(false);
            Statement statement = this.connection.createStatement();
            statement.executeUpdate(dropSql);
            this.connection.commit();
            this.connection.setAutoCommit(true);
        } catch (SQLException var6) {
            try {
                this.connection.rollback();
            } catch (SQLException var5) {
                var5.printStackTrace();
            }

            var6.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        session.getTransaction().commit();
        System.out.println("User с именем " + name + " добавлен в таблицу.");
    }

    public void removeUserById(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = (User)session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
    }

    public List<User> getAllUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<User> usersList = session.createQuery("from User").getResultList();
        Iterator var3 = usersList.iterator();

        while(var3.hasNext()) {
            User u = (User)var3.next();
            System.out.println(u);
        }

        session.getTransaction().commit();
        return usersList;
    }

    public void cleanUsersTable() {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete User").executeUpdate();
        session.getTransaction().commit();
    }
}