package com.test.mobile;

/**
 * Send a request to server with instructions from php files
 */

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class transportInstallRequest extends StringRequest {
    private static final String PRJNO_REQUEST_URL = "http://10.0.2.2/getProjNo.php";
    private static final String TABLE_DATA_REQUEST_URL = "http://10.0.2.2/getData.php";
    private Map<String, String> params;

    public transportInstallRequest(String username, Response.Listener<String> listener) {
        super(Method.POST, PRJNO_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
    }

    public transportInstallRequest(String username, String projNo, Response.Listener<String> listener){
        super(Method.POST, TABLE_DATA_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username",username);
        params.put("projNo", projNo);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
