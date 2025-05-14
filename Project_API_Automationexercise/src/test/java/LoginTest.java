//package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.*;

import Login.*;
import base.LoginSignUpEndpoints;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(EmailOnFailure.class)
public class LoginTest {


    @Test
    public void InvalidLoginTest1() {

        try {
            System.out.println("Try to Login with Invalid_email & Invalid_password");
            RestAssured.baseURI = LoginSignUpEndpoints.URL_Scheme +LoginSignUpEndpoints.URL_Host_automationexercise_com;

            Response response = RestAssured
                    .given()
                        .headers(Login.HeadersProvider.getHeaders(LoginSignUpEndpoints.URL_Scheme,LoginSignUpEndpoints.URL_Host_automationexercise_com,LoginSignUpEndpoints.Login_path_automationexercise_com))
                        .cookies(Login.CookiesProvider.getCookies())
                        .formParams(Login.FormParamsProvider.getFormParams(LoginData.Invalid_email,LoginData.Invalid_password))
                    .when()
                        .post(LoginSignUpEndpoints.Login_path_automationexercise_com)
                    .then()
                        .statusCode(200)
                        .extract()
                        .response();

            //-------------------------------------------------------------
            // Optional: assertion to verify error/success message in body
            if (response.asString().contains("Your email or password is incorrect!")) {
                System.out.println("Your email or password is incorrect!");
                System.out.println("Test Passed");

            }
            else {
                System.out.println("Test Failed");
            }

        }catch (Exception e){
            System.err.println("Test failed due to exception: " + e.getMessage());
            e.printStackTrace();
        }


    }

    @Test
    public void InvalidLoginTest2() {

        try {
            System.out.println("Try to Login with incomplete SignedUp Account's Invalid_email & Invalid_password");
            //Signup >> Enter Account Information (not create account)

            AccountFlowStepsP steps = new AccountFlowStepsP();
            steps.signUpPart1(LoginData.Invalid_email_signUpPart1Done,LoginData.Invalid_password_signUpPart1Done,LoginData.Valid_name);

            //try to login with this Invlid credential
            RestAssured.baseURI = LoginSignUpEndpoints.URL_Scheme +LoginSignUpEndpoints.URL_Host_automationexercise_com;

            Response response = RestAssured
                    .given()
                        .headers(Login.HeadersProvider.getHeaders(LoginSignUpEndpoints.URL_Scheme,LoginSignUpEndpoints.URL_Host_automationexercise_com,LoginSignUpEndpoints.Login_path_automationexercise_com))
                        .cookies(Login.CookiesProvider.getCookies())
                        .formParams(Login.FormParamsProvider.getFormParams(LoginData.Invalid_email_signUpPart1Done,LoginData.Invalid_password_signUpPart1Done))
                    .when()
                        .post(LoginSignUpEndpoints.Login_path_automationexercise_com)  // Only the path since baseURI is set
                    .then()
                        .statusCode(200)
                        .extract()
                        .response();

            //-------------------------------------------------------------
            // Optional: assertion to verify error/success message in body
            if (response.asString().contains("Your email or password is incorrect!")) {
                System.out.println("Your email or password is incorrect!");
                System.out.println("Test Passed");

            }
            else {
                System.out.println("Test Failed");
            }

        }catch (Exception e){
            System.err.println("Test failed due to exception: " + e.getMessage());
            e.printStackTrace();
        }


    }


    @Test
    public void InvalidLoginTest3() {

        try {
            System.out.println("Try to Login with Deleted Account's Invalid_email & Invalid_password");
            //Signup >> create account >> Login >> delete

            AccountFlowStepsP steps = new AccountFlowStepsP();
            steps.signUpPart1(LoginData.Deleted_Registered_email,LoginData.Deleted_Registered_password,LoginData.Valid_name);
            steps.signUpPart2(LoginData.Deleted_Registered_email,LoginData.Deleted_Registered_password,LoginData.Valid_name);
            steps.login(LoginData.Deleted_Registered_email,LoginData.Deleted_Registered_password);
            steps.deleteAccount();

            //try to login with this Deleted Account's Invalid credential
            RestAssured.baseURI = LoginSignUpEndpoints.URL_Scheme +LoginSignUpEndpoints.URL_Host_automationexercise_com;

            Response response = RestAssured
                    .given()
                    .headers(Login.HeadersProvider.getHeaders(LoginSignUpEndpoints.URL_Scheme,LoginSignUpEndpoints.URL_Host_automationexercise_com,LoginSignUpEndpoints.Login_path_automationexercise_com))
                    .cookies(Login.CookiesProvider.getCookies())
                    .formParams(Login.FormParamsProvider.getFormParams(LoginData.Deleted_Registered_email,LoginData.Deleted_Registered_password))
                    .when()
                    .post(LoginSignUpEndpoints.Login_path_automationexercise_com)  // Only the path since baseURI is set
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();

            //-------------------------------------------------------------
            // Optional: assertion to verify error/success message in body
            if (response.asString().contains("Your email or password is incorrect!")) {
                System.out.println("Your email or password is incorrect!");
                System.out.println("Test Passed");

            }
            else {
                System.out.println("Test Failed");
            }

        }catch (Exception e){
            System.err.println("Test failed due to exception: " + e.getMessage());
            e.printStackTrace();
        }


    }

    @Test
    public void ValidLogin1Test() {

        try {
            //Signup >> Enter Account Information >> create account >> auto Login >> Logout
            //try to login with previously created/Old_Registered credentials

            RestAssured.baseURI = LoginSignUpEndpoints.URL_Scheme +LoginSignUpEndpoints.URL_Host_automationexercise_com;

            Response loginResponse = RestAssured
                    .given()
                        .headers(Login.HeadersProvider.getHeaders(LoginSignUpEndpoints.URL_Scheme,LoginSignUpEndpoints.URL_Host_automationexercise_com,LoginSignUpEndpoints.Login_path_automationexercise_com))
                        .cookies(Login.CookiesProvider.getCookies())
                        .formParams(Login.FormParamsProvider.getFormParams(LoginData.OldRegistered_email,LoginData.Valid_password))
                    .when()
                        .post(LoginSignUpEndpoints.Login_path_automationexercise_com)  // Only the path since baseURI is set
                    .then()
                        .statusCode(302)
                        .header("Location", equalTo("/")) // assert redirected to homepage
                        .extract()
                        .response();
            System.out.println("Login successful.");

            // Output & assertions
            System.out.println("loginResponse");
            System.out.println("Status Code: " + loginResponse.getStatusCode());
            System.out.println("Response:\n" + loginResponse.getBody().asString());
            System.out.println("Logout Status Code: " + loginResponse.statusCode());

            //-------------------------------------------------------------
            // Step 2: Follow the redirect to "/" HomePage
            Response homeResponse = given()
                        .cookies(loginResponse.getCookies()) // pass cookies from login response
                    .when()
                        //.get("https://automationexercise.com/")
                        .get(LoginSignUpEndpoints.URL_Scheme+LoginSignUpEndpoints.URL_Host_automationexercise_com+LoginSignUpEndpoints.HomePage_path_automationexercise_com)
                    .then()
                        .statusCode(200)
                        //.body(containsString("<title>Automation Exercise</title>")) // verify homepage content
                        .body(containsString("<title>Automation Exercise - Signup / Login</title>")) // verify homepage content
                        .extract()
                        .response();

            // Output & assertions
            System.out.println("homeResponse");
            System.out.println("Status Code: " + homeResponse.getStatusCode());
            System.out.println("Response:\n" + homeResponse.getBody().asString());
            System.out.println("Logout Status Code: " + homeResponse.statusCode());


            // Assert the response status code
            loginResponse.then().statusCode(302);
            homeResponse.then().statusCode(200);

            System.out.println("Login and redirect successful.");
            System.out.println("Home Page");


            //-------------------------------------------------------------
            if (loginResponse.asString().contains("<title>Automation Exercise</title>") ||
                    loginResponse.asString().contains("<title>Automation Exercise - Signup / Login</title>")) {
                System.out.println("Found before redirect");

            }   else {
                System.out.println("loginResponse >> Redirected to Home page");
            }
            // Optional: assertion to verify error/success message in body
            if (homeResponse.asString().contains("<title>Automation Exercise</title>") ||
                    homeResponse.asString().contains("<title>Automation Exercise - Signup / Login</title>")){
                System.out.println("homeResponse >> Test Passed");

            }
            else {
                System.out.println("homeResponse >> Test Failed");
            }

            //------------------------------------------------------------
            // Step 3: logout();
            AccountFlowStepsP steps = new AccountFlowStepsP();
            steps.logout();


        }catch (Exception e){
            System.err.println("Test failed due to exception: " + e.getMessage());
            e.printStackTrace();
        }


    }



    @Test
    public void ValidLogin2_FullFlowTest() {

        try {
            //Signup >> Enter Account Information >> create account>>login >> delete

            AccountFlowStepsP steps = new AccountFlowStepsP();
            //Signup >> Enter Account Information
            steps.signUpPart1(LoginData.Valid_email,LoginData.Valid_password,LoginData.Valid_name);
            //Signup >> Enter Account Information >> create account
            steps.signUpPart2(LoginData.Valid_email,LoginData.Valid_password,LoginData.Valid_name);
            //try to login with this new credentials
            steps.login(LoginData.Valid_email,LoginData.Valid_password);

            //delete credentials
            steps.deleteAccount();

    }catch (Exception e){
        System.err.println("Test failed due to exception: " + e.getMessage());
        e.printStackTrace();
    }
    }


}

