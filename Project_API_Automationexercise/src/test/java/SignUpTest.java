import Login.LoginData;
import base.LoginSignUpEndpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class SignUpTest {


    //@Test
    public void SignUpPart1Test1() {

        RestAssured.baseURI = LoginSignUpEndpoints.URL_Scheme +LoginSignUpEndpoints.URL_Host_automationexercise_com;

        Response response = given()
                .headers(SignUp.HeadersProvider.getHeaders(LoginSignUpEndpoints.URL_Scheme,LoginSignUpEndpoints.URL_Host_automationexercise_com,LoginSignUpEndpoints.Login_path_automationexercise_com))
                .cookies(SignUp.CookiesProvider.getCookies())
                .formParams(SignUp.FormParamsProvider.getFormParams(LoginData.Valid_email2,LoginData.Valid_password2, LoginData.Valid_name2))
                .when()
                //.post("/signup");
                .post(LoginSignUpEndpoints.SignUp_path_automationexercise_com);


        response.then().body(containsString("Signup"));
        if (response.asString().contains("Enter Account Information")) {
            System.out.println("Enter Account Information");
            System.out.println("Test Passed");
        }
        else {
            System.out.println("Invalid Login");
        }
    }

    //@Test
    public void SignUpPart2Test1() {

        RestAssured.baseURI = LoginSignUpEndpoints.URL_Scheme +LoginSignUpEndpoints.URL_Host_automationexercise_com;

        Response response = given()
                    .headers(SignUp.HeadersProvider.getHeaders(LoginSignUpEndpoints.URL_Scheme,LoginSignUpEndpoints.URL_Host_automationexercise_com,LoginSignUpEndpoints.SignUp_path_automationexercise_com))
                    .cookies(SignUp.CookiesProvider.getCookies())
                    .formParams(SignUp.FormParamsProvider_ToCreateAccount.getFormParams(LoginData.Valid_email2,LoginData.Valid_password2,LoginData.Valid_name2))
                .when()
                    //.post("/signup");
                    .post(LoginSignUpEndpoints.SignUp_path_automationexercise_com)
                .then()
                    .statusCode(302)
                    .extract()
                    .response();

        // Output & assertions
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response:\n" + response.getBody().asString());
        System.out.println("Cookies: " + response.getCookies());

        if (response.asString().contains("Account Created!")) {

            //------------------------------------------------------------
            // Step 3: deleteAccountTest();
            AccountFlowStepsP steps = new AccountFlowStepsP();
            steps.deleteAccount();
        }
        else {
            System.out.println("Invalid Login");
        }

    }

}
