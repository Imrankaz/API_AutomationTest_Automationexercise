//package tests;

import base.LoginSignUpEndpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class DeleteTest {

    //@Test
    public void deleteAccountTest() {
        try {
            RestAssured.baseURI = LoginSignUpEndpoints.URL_Scheme + LoginSignUpEndpoints.URL_Host_automationexercise_com;


            // Send POST request
            Response response = given()
                    .headers(Delete.HeadersProvider.getHeaders(LoginSignUpEndpoints.URL_Scheme, LoginSignUpEndpoints.URL_Host_automationexercise_com, LoginSignUpEndpoints.HomePage_path_automationexercise_com))
                    .cookies(Delete.CookiesProvider.getCookies())
                    //.formParams(formData)
                    .when()
                    .get(LoginSignUpEndpoints.Delete_account_path_automationexercise_com)
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();


            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Response Body:\n" + response.asString());

            if (response.asString().contains("Account Deleted")) {
                System.out.println("✅ Account deleted successfully.");
            } else {
                System.out.println("⚠️ Deletion may not have been successful.");
            }
        } catch (Exception e) {
            System.err.println("Test failed due to exception: " + e.getMessage());
            e.printStackTrace();
        }
    }


    //@Test
    public void DeleteAccountTest1() {
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


//        // Cookies
        Map<String, String> cookies = new HashMap<>();
        cookies.put("csrftoken", "pM53ELf0bMRL9INr2IU3Pjc51fr9qMr5vRqImj0pAHWsqyNLBqoOHOq8cqkGvjcO");
        cookies.put("sessionid", "qxxv8owv9d2zfczevz2aa1yy5ob9qeqe");


        // Send POST request
        Response response = given()
                .headers(headers)
                .cookies(cookies)
                //.formParams(formData)
                .when()
                .get("/delete_account")
                .then()
                .statusCode(200)
                .extract()
                .response();


        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body:\n" + response.asString());

        if (response.asString().contains("Account Deleted")) {
            System.out.println("✅ Account deleted successfully.");
        } else {
            System.out.println("⚠️ Deletion may not have been successful.");
        }
    }



}
