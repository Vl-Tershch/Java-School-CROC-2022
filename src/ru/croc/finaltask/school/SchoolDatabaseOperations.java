package ru.croc.finaltask.school;

import ru.croc.finaltask.school.dao.UserDao;
import ru.croc.finaltask.school.objects.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public final class SchoolDatabaseOperations {
    private String url;
    private String userName;
    private String password;
    private Connection connection;

    public SchoolDatabaseOperations(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
        try {
            this.connection = DriverManager.getConnection(this.url, this.userName, this.password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User connectUser() {
        Scanner scanner = new Scanner(System.in);
        UserDao userDao = new UserDao(this.connection);
        String operations = "";
        User curuser = null;
        System.out.println("Добро пожаловать в школу английского языка!");
        while (!operations.equals("1") || !operations.equals("2")) {
            System.out.println("\nЧтобы войти введите 1, чтобы зарегистрироваться введите 2.");
            operations = scanner.nextLine();
            if (operations.equals("1")) {
                System.out.println("Введите логин:");
                String username = scanner.nextLine();
                System.out.println("Введите пароль:");
                String password = scanner.nextLine();
                curuser = userDao.findUser(username);
                if (curuser == null) {
                    System.out.println("Такого пользователя не существует!");
                    break;
                } else {
                    System.out.println("Вы вошли!");
                    return curuser;
                }
            } else if (operations.equals("2")) {
                System.out.println("Введите логин:");
                String username = scanner.nextLine();
                System.out.println("Введите пароль:");
                String password = scanner.nextLine();
                System.out.println("Повторите пароль:");
                String confirmPassword = scanner.nextLine();
                if (password.equals(confirmPassword)) {
                    curuser = userDao.createUser(username, password);
                    return curuser;
                } else {
                    System.out.println("Введенные пароли не совпадают!");
                }
            } else {
                System.out.println("Введено неверное число, повторите попытку!");
            }
        }
        return null;
    }

    public void createTables() {
        try (Statement statement = this.connection.createStatement()) {
            statement.execute("create table if not exists users(id int not null, username varchar(150) " +
                    "not null, password varchar(150) not null, primary key (id))");
            statement.execute("create table if not exists words(id int not null, user_id int " +
                    "not null, word varchar(150) not null, translate varchar(150) not null, learned int not null, " +
                    "primary key(id), foreign key(user_id) references users(id))");

            System.out.println("[TRANSACTION]: Successful created tables in database!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() {
        if (this.connection != null) {
            try {
                this.connection.close();
                this.connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void checkUserConnection(User user) {
        if (user == null) {
            System.out.println("Соединение с пользователем не установлено, пожалуйста перезапустите приложение!");
        }
    }
}
