package SignUp;

import java.util.HashMap;
import java.util.Map;

public class FormParamsProvider {
    public static Map<String, String> getFormParams(String email, String password, String name) {
        // Reuse FormParamsProvider1 to get base parameters
        Map<String, String> formParams = Login.FormParamsProvider.getFormParams(email, password);

        // Add the extra signup-specific parameter
        formParams.put("name", name);
        //formParams.put("name", "bobby2");
        formParams.put("form_type", "signup");

        return formParams;
    }
}

//        // Form Data


//        formData.put("name", "bobby2");
//        formData.put("form_type", "signup");



//        formParams.put("password", password);