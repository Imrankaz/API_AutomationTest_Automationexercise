package SignUp;

import java.util.HashMap;
import java.util.Map;

public class HeadersProvider {
    public static Map<String, String> getHeaders(String URL_Scheme, String URL_Host_automationexercise_com,String path) {
        //Map<String, String> headers = new HashMap<>();
        //Map<String, String> headers = Login.HeadersProvider2.getHeaders();
        Map<String, String> headers = Login.HeadersProvider.getHeaders(URL_Scheme, URL_Host_automationexercise_com, path);

        return headers;
    }
}
