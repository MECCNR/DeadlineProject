import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class Data {

    public Data() {
    }

    private static final Faker faker = new Faker(new Locale("en"));

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

    @Value
    public static class User {
        private String id;
        private String login;
        private String password;
        private String status;
    }
}
