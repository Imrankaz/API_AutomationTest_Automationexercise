package Login;

import java.util.UUID;

public class GenerateRandomEmail {
    public static String generateRandomEmail() {
        return "user_" + UUID.randomUUID().toString().substring(0, 8) + "@testmail.com";
    }
}
