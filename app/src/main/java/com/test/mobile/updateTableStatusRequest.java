package com.test.mobile;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class updateTableStatusRequest extends StringRequest{
    private static final String TRANSPORT_STATUS_UPDATE_URL = "http://10.0.2.2/updateTableStatus.php";
    private Map<String, String> params;

    public updateTableStatusRequest(String id, String status, Response.Listener<String> listener) {
        super(Method.POST, TRANSPORT_STATUS_UPDATE_URL, listener, null);
        params = new HashMap<>();
        params.put("ID", id);
        params.put("status",status);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
