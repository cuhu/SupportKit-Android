package uk.dreamr.rhdev.dreamrsupportkit;

import android.content.Context;
import android.content.Intent;

/**
 * Created by mylokaye on 28/06/2017.
 */

public class SupportKitEmailSender {

    private SupportKitView view;
    private String emailAddressTo;
    private String emailSubject;
    private String emailBody;

    public SupportKitEmailSender(SupportKitView view){
        this.view = view;
    }

    public String getEmailAddressTo() {
        return emailAddressTo;
    }

    public void setEmailAddressTo(String emailAddressTo) {
        this.emailAddressTo = emailAddressTo;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    public void sendEmail(){
        if(emailAddressTo != null && emailSubject != null && emailBody != null ){
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailAddressTo});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
            emailIntent.putExtra(Intent.EXTRA_TEXT, emailBody);
            if(view != null){
                view.launchIntent(emailIntent);
            }
        }
    }

}
