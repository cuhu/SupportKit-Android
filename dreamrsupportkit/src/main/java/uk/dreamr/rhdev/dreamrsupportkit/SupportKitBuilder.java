package uk.dreamr.rhdev.dreamrsupportkit;

import android.app.FragmentManager;
import android.content.Context;

/**
 * Created by Jack on 27/06/2017.
 */

public class SupportKitBuilder {
    
    public static void showSupportDialog(FragmentManager fragmentManager){
        SupportKitDialog supportKitDialog = new SupportKitDialog();
        supportKitDialog.show(fragmentManager, "SupportKitDialog");
    }
}
