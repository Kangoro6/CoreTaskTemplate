package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl hUser = new UserDaoHibernateImpl();
        hUser.createUsersTable();
        hUser.saveUser("Beep", "Hiver", (byte) 12);
        hUser.saveUser("Grim Neel", "Bad mood", (byte) 121);
        hUser.saveUser("Tinfist", "First Empire", (byte) 127);
        hUser.saveUser("Stone golem", "Shek kingdom", (byte) 58);
        hUser.getAllUsers();
//        hUser.cleanUsersTable();
//        hUser.dropUsersTable();
    }
}
