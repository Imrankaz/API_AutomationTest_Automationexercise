package SignUp;
import java.util.HashMap;
import java.util.Map;

public class FormParamsProvider_ToCreateAccount {
    public static Map<String, String> getFormParams(String email, String password, String name) {
        Map<String, String> formParams = new HashMap<>();
        formParams.put("csrfmiddlewaretoken", "1yMtDQPZGuBAAyf3quZC0C21LvKXAvNM7D78loAo5pGhRofnZctnS7g4WGDuF2yv");
        formParams.put("title", "Mr");
        //formParams.put("name", "bobby");
        formParams.put("name", name);
        //formParams.put("email_address", "bob23@gmail.com");
        formParams.put("email_address", email);
        //formParams.put("password", "123456789123456789");
        formParams.put("password", password);
        formParams.put("days", "10");
        formParams.put("months", "7");
        formParams.put("years", "1974");
        formParams.put("newsletter", "1");
        formParams.put("optin", "1");
        formParams.put("first_name", "Bobby");
        formParams.put("last_name", "Stuards");
        formParams.put("company", "bobby.ltd");
        formParams.put("address1", "7812 Hawthorn Ridge Drive Seattle");
        formParams.put("address2", "WA 98105 USA");
        formParams.put("country", "United States");
        formParams.put("state", "Washington");
        formParams.put("city", "Seattle");
        formParams.put("zipcode", "98105");
        formParams.put("mobile_number", "+1 (206) 555-9371");
        formParams.put("form_type", "create_account");
        return formParams;
    }
}
