import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.DriverManager;

import static com.codeborne.selenide.Selenide.open;

public class DeadlineTest {
    Data.User newUser = Data.getUser();
    @BeforeEach
    @SneakyThrows
    void setUp() {
        var dataSQL = "INSERT INTO users(id, login, password, status) VALUES (?, ?, ?, ?);";

        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
                var dataStmt = conn.prepareStatement(dataSQL);
                ) {
            dataStmt.setString(1, newUser.getId());
            dataStmt.setString(2, newUser.getLogin());
            dataStmt.setString(3, Data.sPassword());
            dataStmt.setString(4, newUser.getStatus());
            dataStmt.executeUpdate();
        }
    }

    @Test
    void DeadlineTest() {
        var loginPage = open("http://localhost:9999", Setup.class);
        var authInfo = newUser;
        var enter = loginPage.validLogin(authInfo);
    }





    /*@SneakyThrows
    @Test
    void stubTest() {
        var countSQL = "SELECT COUNT(*) FROM users;";
        var cardsSQL = "SELECT id, number, balance_in_kopecks FROM cards WHERE user_id = ?;";

        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
                var countStmt = conn.createStatement();
                var cardsStmt = conn.prepareStatement(cardsSQL);
                ) {
            try (var rs = countStmt.executeQuery(countSQL)) {
                if (rs.next()) {
                    var count = rs.getInt(1);
                    System.out.println(count);
                }
            }

            cardsStmt.setInt(1, 1);
            try (var rs = cardsStmt.executeQuery()) {
                while (rs.next()) {
                    var id = rs.getInt("id");
                    var number = rs.getString("number");
                    var balanceInKopecks = rs.getInt("balance_in_kopecks");
                }
            }
        }
    }*/
}
