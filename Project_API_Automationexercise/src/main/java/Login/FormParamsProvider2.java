package Login;

import java.util.HashMap;
import java.util.Map;

public class FormParamsProvider2 {
    public static Map<String, String> getFormParams() {
        Map<String, String> formParams = new HashMap<>();
        formParams.put("csrfmiddlewaretoken", "8eavGg5e6d3UO8DfialYJeIkSWXJqaWlejvaoOQDv88B5YDzRSPJBJWn37QgvHH4");
//        formParams.put("email", "bob22@gmail.com");
//        formParams.put("password", "123456789123456789");
        formParams.put("email", "bob22@gmail.com");
        formParams.put("password", "123456789123456789");
        return formParams;
    }
}