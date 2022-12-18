package ru.croc.finaltask.school;

import ru.croc.finaltask.school.dao.UserDao;
import ru.croc.finaltask.school.dao.WordDao;
import ru.croc.finaltask.school.objects.User;
import ru.croc.finaltask.school.objects.Word;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

// Basic operations with the application database
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
        while (!operations.equals("EXIT")) {
            System.out.println("\nЧтобы войти введите 1, чтобы зарегистрироваться введите 2.");
            operations = scanner.nextLine();
            if (operations.equals("1")) {
                System.out.println("Введите логин:");
                String username = scanner.nextLine();
                System.out.println("Введите пароль:");
                String password = scanner.nextLine();
                curuser = userDao.findUser(username);
                if (curuser == null) {
                    System.out.println("[SYS]: Такого пользователя не существует!");
                } else {
                    System.out.println("[SYS]: Вы вошли!");
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
                    System.out.println("[SYS]: Введенные пароли не совпадают!");
                }
            } else if (!operations.equals("EXIT")) {
                System.out.println("[SYS]: Введено неверное число, повторите попытку!");
            }
        }
        return null;
    }

    public void workWithWords(User user) {
        String curAction = "";
        Scanner scanner = new Scanner(System.in);
        WordDao wordDao = new WordDao(this.connection);
        while (!curAction.equals("EXIT")) {
            System.out.println("\nЧтобы добавить новое слово введите 1, чтобы пометить слово как изученное введите 2" +
                    "; \nЧтобы получить весь список ваших слов введите 3, чтобы получить список изученных слов введите " +
                    "4; \nЧтобы получить список слов которые предстоит изучить введите 5 чтобы выйти введите EXIT.");
            curAction = scanner.nextLine();
            switch (curAction) {
                case "1":
                    System.out.println("Введите слово:");
                    String newWord = scanner.nextLine();
                    System.out.println("Введите перевод:");
                    String newWordTranslate = scanner.nextLine();
                    Word createdWord = wordDao.addWord(user.getId(), newWord, newWordTranslate);
                    System.out.println("Добавлено новое слово: " + createdWord + "\n");
                    break;
                case "2":
                    System.out.println("Введите слово:");
                    String curWord = scanner.nextLine();
                    Word updatedWord = wordDao.updateWordLearned(curWord);
                    System.out.println("Слово помечено как изученное: " + updatedWord + "\n");
                    break;
                case "3":
                    List<Word> userWords = wordDao.getAllUserWords(user.getUsername());
                    printWords(userWords);
                    break;
                case "4":
                    List<Word> userLearnedWords = wordDao.getAllUserLearnedWords(user.getId());
                    printWords(userLearnedWords);
                    break;
                case "5":
                    List<Word> userNotLearnedWords = wordDao.getAllUserNotLearnedWords(user.getId());
                    printWords(userNotLearnedWords);
                    break;
                case "EXIT":
                    break;
                default:
                    System.out.println("[SYS]: Введена неизвестная команда, попробуйте еще раз!");
                    break;
            }
        }
    }

    public List<Word> getUsersWords(String userName) {
        System.out.println("[SYS]: Загружаем ваш список слов...");
        WordDao wordDao = new WordDao(this.connection);
        List<Word> res = wordDao.getAllUserWords(userName);
        System.out.println("[SYS]: Слова успешно загружены!");
        return res;
    }

    public void createTables() {
        try (Statement statement = this.connection.createStatement()) {
            statement.execute("create table if not exists user(id int not null auto_increment, " +
                    "username varchar(150) " + "not null, password varchar(150) not null, primary key (id))");
            statement.execute("create table if not exists word(id int not null auto_increment, user_id int " +
                    "not null, word varchar(150) not null, translate varchar(150) not null, learned int not null, " +
                    "primary key(id), foreign key(user_id) references user(id))");

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
            System.out.println("[SYS]: Соединение с пользователем не установлено, пожалуйста перезапустите приложение!");
        }
    }

    public void printWords(List<Word> words) {
        for (Word word : words) {
            System.out.println(word);
        }
    }
}
