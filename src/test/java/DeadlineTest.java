import data.Data;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import pages.DashboardPage;
import pages.Setup;

import static com.codeborne.selenide.Selenide.open;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DeadlineTest {
    Data.User newUser = Data.getUser();

    @BeforeEach
    @SneakyThrows
    void setUp() {
        Data.getStarted(newUser);
    }

    @AfterAll
    void killTest() {
        Data.killCode();
    }

    @Test
    @SneakyThrows
    void DeadlineTest() {
        var loginPage = open("http://localhost:9999", Setup.class);
        var authInfo = newUser;
        var enter = loginPage.validLogin(authInfo);
        Data.enterCode(newUser);
        new DashboardPage();
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
