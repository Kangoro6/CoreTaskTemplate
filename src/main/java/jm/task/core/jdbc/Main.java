package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;


public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl user = new UserDaoJDBCImpl();
        user.createUsersTable();
        user.saveUser("Beep", "Hiver", (byte) 12);
        user.saveUser("Grim Neel", "Bad mood", (byte) 121);
        user.saveUser("Tinfist", "First Empire", (byte) 127);
        user.saveUser("Stone golem", "Shek kingdom", (byte) 58);
        user.getAllUsers();
        user.cleanUsersTable();
        user.dropUsersTable();
    }
}
