package Login;

import java.util.HashMap;
import java.util.Map;

public class FormParamsProvider {
    public static Map<String, String> getFormParams(String email, String password) {
        Map<String, String> formParams = new HashMap<>();
        formParams.put("csrfmiddlewaretoken", "8eavGg5e6d3UO8DfialYJeIkSWXJqaWlejvaoOQDv88B5YDzRSPJBJWn37QgvHH4");
        formParams.put("email", email);
        formParams.put("password", password);
        return formParams;
    }
}