package uk.dreamr.rhdev.dreamrsupportkit;

import java.util.HashMap;

/**
 * Created by Jack on 27/06/2017.
 */

public class SupportKitConstants {
    public static final String SUPPORTKIT_LOG = "SupportKitLogs";

    // types of issue
    public static final String ISSUE_PAYMENT = "I have an issue with payments";
    public static final String ISSUE_PAYMENT_NAME = "payment";
    public static final String ISSUE_BUG = "I have found a bug";
    public static final String ISSUE_BUG_NAME = "bug";
    public static final String ISSUE_LEGAL = "There may be a legal issue";
    public static final String ISSUE_LEGAL_NAME = "legal";
    public static final String ISSUE_FEEDBACK = "I have some feedback";
    public static final String ISSUE_FEEDBACK_NAME = "feedback";
    public static final String ISSUE_DESCRIPTION = "Please select an issue to report";
    public enum issue{
        PAYMENT, BUG, LEGAL, FEEDBACK
    }

    public static final String[] ISSUES = new String[]{
            ISSUE_FEEDBACK,
            ISSUE_BUG,
            ISSUE_PAYMENT,
            ISSUE_LEGAL
    };
    private static HashMap<String,String> ISSUE_MAP;

    public static HashMap<String,String> getIssueMap(){
        if(ISSUE_MAP == null){
            ISSUE_MAP = new HashMap<>();
            ISSUE_MAP.put(ISSUE_FEEDBACK, ISSUE_FEEDBACK_NAME);
            ISSUE_MAP.put(ISSUE_BUG, ISSUE_BUG_NAME);
            ISSUE_MAP.put(ISSUE_PAYMENT, ISSUE_PAYMENT_NAME);
            ISSUE_MAP.put(ISSUE_LEGAL, ISSUE_LEGAL_NAME);
        }
        return ISSUE_MAP;
    }

}
