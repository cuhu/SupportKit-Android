package uk.dreamr.rhdev.dreamrsupportkit;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jack on 27/06/2017.
 */

public class SupportKitEndPoint {

    private static SupportKitEndPoint instance;
    private String baseUrl;
    private static final String TAG = "SupportKitTag";
    private RequestQueue requestQueue;
    private SupportKitResponse supportKitResponse;

    public static SupportKitEndPoint getInstance() {
        if (instance == null) {
            instance = new SupportKitEndPoint();
        }
        return instance;
    }

    public void setBaseUrl(final String url) {
        if (baseUrl == null) {
            baseUrl = url;
            Log.i(SupportKitConstants.SUPPORTKIT_LOG, "setting base url to : " + url);
        } else {
            Log.w(SupportKitConstants.SUPPORTKIT_LOG, "support kit base URL already set, cannot set again");
        }
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public RequestQueue getRequestQueue(Context appContext) {
        if (requestQueue == null) {
            Log.i(SupportKitConstants.SUPPORTKIT_LOG, "creating new request queue");
            requestQueue = Volley.newRequestQueue(appContext);
        }
        return requestQueue;
    }

    public void setSupportKitResponse(SupportKitResponse supportKitResponse) {
        this.supportKitResponse = supportKitResponse;
    }

    public void addToRequestQueue(Context appContext, JsonObjectRequest req, @Nullable String tag) {
        //String useTag = (tag == null ? TAG : tag);
        req.setTag(TAG);
        getRequestQueue(appContext).add(req);
    }

    public void cancelAllRequests() {
        if (requestQueue != null) {
            requestQueue.cancelAll(TAG);
        } else {
            Log.e(SupportKitConstants.SUPPORTKIT_LOG, "request queue is null");
        }
    }

    public void postIssue(String issueType, String issueDescription) {
        if (baseUrl == null) {
            Log.e(SupportKitConstants.SUPPORTKIT_LOG, "Could not post issue, no url provided");
            return;
        }
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", issueType);
            jsonObject.put("description", issueDescription);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    baseUrl, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.i(SupportKitConstants.SUPPORTKIT_LOG, "Response received");
                    Log.d(SupportKitConstants.SUPPORTKIT_LOG, "Json : " + response.toString());
                    String result = response.optString("result", "");
                    if(result.equals(SupportKitConstants.RESULT_SUCCESS)){
                        if (supportKitResponse != null) {
                            supportKitResponse.postSuccess();
                        }
                    }else{
                        if (supportKitResponse != null) {
                            supportKitResponse.errorCaught(""); // TODO handle correctly
                        }
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(SupportKitConstants.SUPPORTKIT_LOG, "error on response");
                    Log.e(SupportKitConstants.SUPPORTKIT_LOG, error.toString());
                    if (supportKitResponse != null) {
                        supportKitResponse.errorCaught("Could not send issue");
                    }

                }
            });
        } catch (JSONException e) {
            Log.e(SupportKitConstants.SUPPORTKIT_LOG, "Could not create request");
            e.printStackTrace();
        }
    }


}
