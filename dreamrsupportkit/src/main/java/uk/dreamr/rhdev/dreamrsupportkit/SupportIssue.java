package uk.dreamr.rhdev.dreamrsupportkit;

/**
 * Created by Jack on 27/06/2017.
 */

public class SupportIssue {

    public enum issue{
        PAYMENT, BUG, LEGAL, FEEDBACK
    }

    private String title;
    private String subject;
    private String message;
    private String body;
    private issue issue;

    

}
