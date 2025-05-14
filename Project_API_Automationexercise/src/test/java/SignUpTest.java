//package tests;

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

    @Test
    public void SignUpPart1Test2() {

        RestAssured.baseURI = "https://automationexercise.com";

        Response response = given()
                //                .headers(headers)
                .headers(SignUp.HeadersProvider.getHeaders(LoginSignUpEndpoints.URL_Scheme,LoginSignUpEndpoints.URL_Host_automationexercise_com,LoginSignUpEndpoints.Login_path_automationexercise_com))
                .cookies(SignUp.CookiesProvider.getCookies())
                .formParams(SignUp.FormParamsProvider.getFormParams(LoginData.Valid_email,LoginData.Valid_password, LoginData.Valid_name))
                .when()
                .post("/signup");

        // Output & assertions
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response:\n" + response.getBody().asString());

        response.then().body(containsString("Signup"));
    }

    @Test
    public void SignUpPart1Test3() {

        RestAssured.baseURI = LoginSignUpEndpoints.URL_Scheme +LoginSignUpEndpoints.URL_Host_automationexercise_com;

        Response response = given()
                .headers(SignUp.HeadersProvider.getHeaders(LoginSignUpEndpoints.URL_Scheme,LoginSignUpEndpoints.URL_Host_automationexercise_com,LoginSignUpEndpoints.Login_path_automationexercise_com))
                .cookies(SignUp.CookiesProvider.getCookies())
                .formParams(SignUp.FormParamsProvider.getFormParams(LoginData.Valid_email,LoginData.Valid_password, LoginData.Valid_name))
                .when()
                //.post("/signup");
                .post(LoginSignUpEndpoints.SignUp_path_automationexercise_com);

        // Output & assertions
//        System.out.println("Status Code: " + response.getStatusCode());
//        System.out.println("Response:\n" + response.getBody().asString());

        response.then().body(containsString("Signup"));
        if (response.asString().contains("Enter Account Information")) {
            System.out.println("Enter Account Information");
            System.out.println("Test Passed");

        }
        else {
            System.out.println("Invalid Login");
        }
    }

    @Test
    public void SignUpPart2Test1() {

        RestAssured.baseURI = LoginSignUpEndpoints.URL_Scheme +LoginSignUpEndpoints.URL_Host_automationexercise_com;

        Response response = given()
                .headers(SignUp.HeadersProvider.getHeaders(LoginSignUpEndpoints.URL_Scheme,LoginSignUpEndpoints.URL_Host_automationexercise_com,LoginSignUpEndpoints.SignUp_path_automationexercise_com))
                .cookies(SignUp.CookiesProvider.getCookies())
                .formParams(SignUp.FormParamsProvider_ToCreateAccount.getFormParams(LoginData.Valid_email,LoginData.Valid_password,LoginData.Valid_name))
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

        //response.then().body(containsString("Account Created!"));
        if (response.asString().contains("Account Created!")) {
//            response.then().body(containsString("Congratulations! Your new account has been successfully created!"));
//            response.then().body(containsString("You can now take advantage of member privileges to enhance your online shopping experience with us."));
//            response.then().body(containsString("You have been successfully subscribed!"));


            System.out.println("        Account Created!\n" +
                    "        Congratulations! Your new account has been successfully created!\n" +
                    "\n" +
                    "        You can now take advantage of member privileges to enhance your online shopping experience with us.\n" +
                    "\n" +
                    "        Continue\n" +
                    "        You have been successfully subscribed!");


            System.out.println("Test Passed");
            System.out.println(" Account Created fro >>   "+LoginData.Valid_email+"  >>   "+ LoginData.Valid_password +"  >>  " + LoginData.Valid_name);


            //------------------------------------------------------------
            // Step 3: deleteAccountTest();
            AccountFlowStepsP steps = new AccountFlowStepsP();
            steps.deleteAccount();
        }
        else {
            System.out.println("Invalid Login");
        }


        /*
        Account Created!
        Congratulations! Your new account has been successfully created!

        You can now take advantage of member privileges to enhance your online shopping experience with us.

        Continue
        You have been successfully subscribed!
         */
    }

}
