//package tests;

import Login.LoginData;
import base.LoginSignUpEndpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import static io.restassured.RestAssured.given;

public class AccountFlowStepsP {

    public void signUpPart1(String email, String password, String name) {
        try {
            System.out.println("🔹 Step 1: SignUp Part 1");


            RestAssured.baseURI = LoginSignUpEndpoints.URL_Scheme +LoginSignUpEndpoints.URL_Host_automationexercise_com;

            Response response = given()
                    .headers(SignUp.HeadersProvider.getHeaders(LoginSignUpEndpoints.URL_Scheme,LoginSignUpEndpoints.URL_Host_automationexercise_com,LoginSignUpEndpoints.Login_path_automationexercise_com))
                    .cookies(SignUp.CookiesProvider.getCookies())
                    .formParams(SignUp.FormParamsProvider.getFormParams(email,password,name))
                    .when()
                    //.post("/signup");
                    .post(LoginSignUpEndpoints.SignUp_path_automationexercise_com);


            response.then().body(containsString("Signup"));
            if (response.asString().contains("Enter Account Information")) {
                //System.out.println("Enter Account Information");
                //System.out.println("Test Passed");

            }
            else {
                System.out.println("Test Failed");
            }
        }
        catch (Exception e){
        System.err.println("Test failed due to exception: " + e.getMessage());
        e.printStackTrace();
    }
    }

    public void signUpPart2(String email, String password, String name) {
        try {

            System.out.println("🔹 Step 2: SignUp Part 2");

            RestAssured.baseURI = LoginSignUpEndpoints.URL_Scheme + LoginSignUpEndpoints.URL_Host_automationexercise_com;

            Response response = given()
                    .headers(SignUp.HeadersProvider.getHeaders(LoginSignUpEndpoints.URL_Scheme, LoginSignUpEndpoints.URL_Host_automationexercise_com, LoginSignUpEndpoints.SignUp_path_automationexercise_com))
                    .cookies(SignUp.CookiesProvider.getCookies())
                    .formParams(SignUp.FormParamsProvider_ToCreateAccount.getFormParams(email,password,name))
                    .when()
                    //.post("/signup");
                    .post(LoginSignUpEndpoints.SignUp_path_automationexercise_com)
                    .then()
                    .statusCode(302)
                    .extract()
                    .response();

            // Output & assertions
            //System.out.println("Status Code: " + response.getStatusCode());
            //System.out.println("Response:\n" + response.getBody().asString());

            //response.then().body(containsString("Account Created!"));
            if (response.asString().contains("Account Created!")) {


//                System.out.println("        Account Created!\n" +
//                        "        Congratulations! Your new account has been successfully created!\n" +
//                        "\n" +
//                        "        You can now take advantage of member privileges to enhance your online shopping experience with us.\n" +
//                        "\n" +
//                        "        Continue\n" +
//                        "        You have been successfully subscribed!");


//                System.out.println("Test Passed");
//                System.out.println(" Account Created fro >>   " + LoginData.Valid_email + "  >>   " + LoginData.Valid_password + "  >>  " + LoginData.Valid_name);


            } else {
               // System.out.println("Test Failed");
            }
        } catch (Exception e){
        System.err.println("Test failed due to exception: " + e.getMessage());
        e.printStackTrace();
    }

    }


    public void login(String email, String password) {
        System.out.println("🔹 Step 3: Login");


        try {
            //Signup >> Enter Account Information >> create account
           RestAssured.baseURI = LoginSignUpEndpoints.URL_Scheme +LoginSignUpEndpoints.URL_Host_automationexercise_com;

            Response response = RestAssured
                    .given()
                    .headers(Login.HeadersProvider.getHeaders(LoginSignUpEndpoints.URL_Scheme,LoginSignUpEndpoints.URL_Host_automationexercise_com,LoginSignUpEndpoints.Login_path_automationexercise_com))
                    .cookies(Login.CookiesProvider.getCookies())
                    .formParams(Login.FormParamsProvider.getFormParams(email,password))
                    .when()
                    .post(LoginSignUpEndpoints.Login_path_automationexercise_com)  // Only the path since baseURI is set
                    .then()
                    .statusCode(302)
                    .extract()
                    .response();

            //-------------------------------------------------------------
            // Optional: assertion to verify error/success message in body
            if (response.asString().contains("<title>Automation Exercise</title>")) {
                //System.out.println("Your email or password is incorrect!");
                //System.out.println("Found before redirect");

            }   else {
                System.out.println("Redirected to Home page");
            }

            //-------------------------------------------------------------

            // Assert the response status code
            response.then().statusCode(302);

            //Assertion
//            response.then().body(containsString("Your email or password is incorrect!"));
//
            //String responseBody = response.getBody().asString();
//            assert responseBody.contains("???");
        }catch (Exception e){
            System.err.println("Test failed due to exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void logout() {
        try {
            System.out.println("Logout");
            RestAssured.baseURI = LoginSignUpEndpoints.URL_Scheme +LoginSignUpEndpoints.URL_Host_automationexercise_com;

            // Send GET request to /logout
            Response response = given()
                    .headers(Logout.HeadersProvider.getHeaders(LoginSignUpEndpoints.URL_Scheme,LoginSignUpEndpoints.URL_Host_automationexercise_com,LoginSignUpEndpoints.HomePage_path_automationexercise_com))
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
//            System.out.println("Status Code: " + response.getStatusCode());
//            System.out.println("Logout Status Code: " + response.statusCode());
//            System.out.println("Redirect Location: " + response.getHeader("Location"));
//            System.out.println("Response:\n" + response.getBody().asString());
        } catch (Exception e){
        System.err.println("Test failed due to exception: " + e.getMessage());
        e.printStackTrace();
    }

    }

    public void deleteAccount() {
        try {
            RestAssured.baseURI = LoginSignUpEndpoints.URL_Scheme +LoginSignUpEndpoints.URL_Host_automationexercise_com;



            // Send POST request
            Response response = given()
                    .headers(Delete.HeadersProvider.getHeaders(LoginSignUpEndpoints.URL_Scheme,LoginSignUpEndpoints.URL_Host_automationexercise_com,LoginSignUpEndpoints.HomePage_path_automationexercise_com))
                    .cookies(Delete.CookiesProvider.getCookies())
                    //.formParams(formData)
                    .when()
                    .get(LoginSignUpEndpoints.Delete_account_path_automationexercise_com)
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();


//            System.out.println("Status Code: " + response.getStatusCode());
//            System.out.println("Response Body:\n" + response.asString());

            if (response.asString().contains("Account Deleted")) {
                //System.out.println("✅ Account deleted successfully.");
            } else {
                System.out.println("⚠️ Deletion may not have been successful.");
            }
        }catch (Exception e){
            System.err.println("Test failed due to exception: " + e.getMessage());
            e.printStackTrace();
        }
    }




}
