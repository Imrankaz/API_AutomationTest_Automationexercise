//package tests;

import base.LoginSignUpEndpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class LogoutTest {
    @Test
    public void logoutTest() {
        try {
            System.out.println("Logout");
            RestAssured.baseURI = LoginSignUpEndpoints.URL_Scheme + LoginSignUpEndpoints.URL_Host_automationexercise_com;

            // Send GET request to /logout
            Response response = given()
                    .headers(Logout.HeadersProvider.getHeaders(LoginSignUpEndpoints.URL_Scheme, LoginSignUpEndpoints.URL_Host_automationexercise_com, LoginSignUpEndpoints.HomePage_path_automationexercise_com))
                    .cookies(Logout.CookiesProvider.getCookies())
                    .when()
                    //.get("/logout")
                    .get(LoginSignUpEndpoints.Logout_path_automationexercise_com)
                    .then()
                    .statusCode(200) // Expecting redirect
                    //.header("Location", anyOf(equalTo("/login"), equalTo("/"))) // Redirects to login or home
                    .extract()
                    .response();

            // Output & assertions
            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Logout Status Code: " + response.statusCode());
            System.out.println("Redirect Location: " + response.getHeader("Location"));
            System.out.println("Response:\n" + response.getBody().asString());
        } catch (Exception e) {
            System.err.println("Test failed due to exception: " + e.getMessage());
            e.printStackTrace();
        }

    }
    @Test
    public void logout2Test() {


        RestAssured.baseURI = "https://automationexercise.com";

        // Headers
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");
        headers.put("accept-language", "en-US,en;q=0.9");
        headers.put("priority", "u=0, i");
        headers.put("referer", "https://automationexercise.com/");
        headers.put("sec-ch-ua", "\"Chromium\";v=\"136\", \"Google Chrome\";v=\"136\", \"Not.A/Brand\";v=\"99\"");
        headers.put("sec-ch-ua-mobile", "?0");
        headers.put("sec-ch-ua-platform", "\"Windows\"");
        headers.put("sec-fetch-dest", "document");
        headers.put("sec-fetch-mode", "navigate");
        headers.put("sec-fetch-site", "same-origin");
        headers.put("sec-fetch-user", "?1");
        headers.put("upgrade-insecure-requests", "1");
        headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36");

        // Cookies
        Map<String, String> cookies = new HashMap<>();
        cookies.put("csrftoken", "pM53ELf0bMRL9INr2IU3Pjc51fr9qMr5vRqImj0pAHWsqyNLBqoOHOq8cqkGvjcO");
        cookies.put("sessionid", "qxxv8owv9d2zfczevz2aa1yy5ob9qeqe");
        cookies.put("__gads", "ID=a59b199e817f4aee:T=1746856575:RT=1746856575:S=ALNI_MZmK8cDt-B4g-xmCg4_NTjWBrgLCw");
        cookies.put("__gpi", "UID=0000101df1d99186:T=1746856575:RT=1746856575:S=ALNI_MYd6Aie9JBifCu8O5dVeOGv-yLmKg");
        cookies.put("__eoi", "ID=b1875b2fc0d00c89:T=1746856575:RT=1746856575:S=AA-AfjYrmCRsQr1aN6khPtp1GKTa");
        cookies.put("FCNEC", "%5B%5B%22AKsRol9KR1V5OTTU4fg3LjpN4EklYRccW0t9mCBviHHbgS-63VY_7TQG3kfMPIEdk7MazR9P63ec2gYFaWPkJc5e3KQn-W7SEoSvxC7qiZfl3U8SvVzuhvsEdZA3-0JtngE3imaZqKN1HZdxM1X3egp4r9FGKRnAQA%3D%3D%22%5D%5D");

        // Send GET request to /logout
        Response response = given()
                .headers(headers)
                .cookies(cookies)
                .when()
                .get("/logout")
                .then()
                .statusCode(200) // Expecting redirect
                //.header("Location", anyOf(equalTo("/login"), equalTo("/"))) // Redirects to login or home
                .extract()
                .response();

        // Output & assertions
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response:\n" + response.getBody().asString());
        System.out.println("Logout Status Code: " + response.statusCode());
        System.out.println("Redirect Location: " + response.getHeader("Location"));

    }


}
