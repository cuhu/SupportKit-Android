package uk.dreamr.rhdev.dreamrsupportkit;

import android.util.Log;

/**
 * Created by Jack on 27/06/2017.
 */

public class SupportKitEndPoint {
    public static final String SUPPORTKIT_LOG = "SupportKitLogs";
    private static String baseUrl;

    public static void setBaseUrl(final String url){
        if(baseUrl == null){
            baseUrl = url;
            Log.d(SUPPORTKIT_LOG, "setting base url to : "+url);
        }else{
            Log.w(SUPPORTKIT_LOG, "support kit base URL already set, cannot set again");
        }
    }

    public static String getBaseUrl(){
        return baseUrl;
    }

}
