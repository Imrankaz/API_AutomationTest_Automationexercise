package SignUp;

import java.util.Map;

public class CookiesProvider {
    public static Map<String, String> getCookies() {
        //Map<String, String> cookies = new HashMap<>();
        Map<String, String> cookies = Login.CookiesProvider.getCookies();
        //cookies.put("xx", "xxxx");
        return cookies;
    }
}
