package Logout;
import java.util.HashMap;
import java.util.Map;

public class HeadersProvider {
    public static Map<String, String> getHeaders(String URL_Scheme, String URL_Host_automationexercise_com,String path ) {
        // Headers
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");
        headers.put("accept-language", "en-US,en;q=0.9");
        headers.put("priority", "u=0, i");
        //headers.put("referer", "https://automationexercise.com/");
        headers.put("referer", URL_Scheme+URL_Host_automationexercise_com+path);
        headers.put("sec-ch-ua", "\"Chromium\";v=\"136\", \"Google Chrome\";v=\"136\", \"Not.A/Brand\";v=\"99\"");
        headers.put("sec-ch-ua-mobile", "?0");
        headers.put("sec-ch-ua-platform", "\"Windows\"");
        headers.put("sec-fetch-dest", "document");
        headers.put("sec-fetch-mode", "navigate");
        headers.put("sec-fetch-site", "same-origin");
        headers.put("sec-fetch-user", "?1");
        headers.put("upgrade-insecure-requests", "1");
        headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36");
        return headers;

    }
}


