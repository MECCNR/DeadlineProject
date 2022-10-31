package data;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import lombok.Value;
import pages.VerifyPage;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

public class Data {

    private static final Faker faker = new Faker(new Locale("en"));

    public Data() {
    }

    public static String genLogin() {
        String login = faker.name().username();
        return login;
    }

    public static String genPassword() {
        String password = "password";
        return password;
    }

    public static String sPassword() {
        String password = "$2a$06$A1U815vX3OpQUCKSb28qeeYMKPvLvxcw6hiMJKdeLnnCeCXdvWZuG";
        return password;
    }

    public static String genId() {
        String id = faker.number().digits(5);
        return id;
    }

    public static String genStatus() {
        String status = "active";
        return status;
    }

    public static User getUser() {
        return new User(genId(), genLogin(), genPassword(), genStatus());
    }

    @SneakyThrows
    public static void getStarted(User user) {
        var dataSQL = "INSERT INTO users(id, login, password, status) VALUES (?, ?, ?, ?);";
        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
                var dataStmt = conn.prepareStatement(dataSQL);
        ) {
            dataStmt.setString(1, user.getId());
            dataStmt.setString(2, user.getLogin());
            dataStmt.setString(3, Data.sPassword());
            dataStmt.setString(4, user.getStatus());
            dataStmt.executeUpdate();
        }
    }

    public static void enterCode(User user) {
        try {
            var conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/app", "app", "pass"
            );
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT code FROM auth_codes WHERE user_id = '" + user.getId() + "';");
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                VerifyPage.validVerify(name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void killCode() {
        try {
            var conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/app", "app", "pass"
            );
            Statement statement = conn.createStatement();
            statement.execute("DELETE FROM cards WHERE balance_in_kopecks < NOW() - INTERVAL 5 MINUTE;");
            statement.execute("DELETE FROM auth_codes WHERE created < NOW() - INTERVAL 5 MINUTE;");
            statement.execute("DELETE FROM card_transactions WHERE created < NOW() - INTERVAL 5 MINUTE;");
            statement.execute("DELETE FROM users WHERE login = 'vasya';");
            statement.execute("DELETE FROM users WHERE login = 'petya';");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Value
    public static class User {
        private String id;
        private String login;
        private String password;
        private String status;
    }
}
