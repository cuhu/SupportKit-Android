package uk.dreamr.rhdev.dreamrsupportkit;

import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Jack on 27/06/2017.
 */

public class SupportKitBuilder {

    private boolean baseUrlSet = false;
    private boolean baseEmailSet = false;
    private boolean issuesAdded = false;
    private String emailTo;
    private SupportKitIssue issues;


    public SupportKitBuilder(){

    }

    public SupportKitBuilder setBaseUrl(String url){
        SupportKitEndPoint.getInstance().setBaseUrl(url);
        return this;
    }

    public SupportKitBuilder setEmailTo(String email){
        emailTo = email;
        baseEmailSet = true;
        return this;
    }

    public void addIssue(String issueMessage, String issueType){
        if(issues == null){
            issues = new SupportKitIssue();
        }
        issues.addIssue(issueMessage,issueType);
        issuesAdded = true;
    }

    public void show(FragmentManager manager)throws SupportKitBuilderException{
        if(baseEmailSet){
            if(!issuesAdded){
                Log.w(SupportKitConstants.SUPPORTKIT_LOG, "Using default issues");
            }
            SupportKitDialog dialog = new SupportKitDialog();
            Bundle bundle = new Bundle();
            if(issues != null) {
                bundle.putStringArrayList(SupportKitConstants.SUPPORTKIT_ISSUES_LIST, issues.getIssues());
                bundle.putStringArrayList(SupportKitConstants.SUPPORTKIT_ISSUE_MESSAGES_LIST, issues.getIssueMessages());
            }
            bundle.putString(SupportKitConstants.SUPPORTKIT_EMAIL, emailTo);
            dialog.setArguments(bundle);
            dialog.show(manager, "SupportKitDialog");
        }else{
            Log.e(SupportKitConstants.SUPPORTKIT_LOG, "no email set");
            throw new SupportKitBuilderException("please add email address",new Throwable("No email address found"));
        }
    }
}
