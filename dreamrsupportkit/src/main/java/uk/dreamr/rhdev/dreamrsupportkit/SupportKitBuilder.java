package uk.dreamr.rhdev.dreamrsupportkit;

import android.app.FragmentManager;
import android.util.Log;

/**
 * Created by Jack on 27/06/2017.
 */

public class SupportKitBuilder {

    private static SupportKitBuilder instance;
    private boolean baseUrlSet = false;

    public static SupportKitBuilder getInstance() {
        if(instance == null){
            instance = new SupportKitBuilder();
        }
        return instance;
    }

    public void setBaseUrl(String url){
        SupportKitEndPoint.getInstance().setBaseUrl(url);
        baseUrlSet = true;
    }

    public void show(FragmentManager manager){
        if(baseUrlSet){
            SupportKitDialog dialog = new SupportKitDialog();
            dialog.show(manager, "SupportKitDialog");
        }else{
            Log.e(SupportKitConstants.SUPPORTKIT_LOG, "no base URL set");
        }

    }
}
