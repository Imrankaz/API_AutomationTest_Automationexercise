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

        } catch (Exception e) {
            System.err.println("Test failed due to exception: " + e.getMessage());
            e.printStackTrace();
        }

    }

}
