package uk.dreamr.rhdev.dreamrsupportkit;

import java.util.ArrayList;

/**
 * Created by Jack on 27/06/2017.
 */

class SupportKitIssue {
    private ArrayList<String> issues;
    private ArrayList<String> issueMessage;

    public SupportKitIssue(ArrayList<String> issues, ArrayList<String> issueMessage) {
        this.issues = issues;
        this.issueMessage = issueMessage;
    }

    public SupportKitIssue() {
        this.issues = new ArrayList<>();
        this.issueMessage = new ArrayList<>();
    }

    public void addIssue(String issueMessage, String issueType){
        issues.add(issueType);
        this.issueMessage.add(issueMessage);
    }

    public ArrayList<String> getIssues() {
        return issues;
    }

    public ArrayList<String> getIssueMessages() {
        return issueMessage;
    }

}
