package uk.dreamr.rhdev.dreamrsupportkit;

/**
 * Created by Jack on 27/06/2017.
 */

public class SupportKitIssue {

    private String title;
    private String subject;
    private String message;
    private String body;
    private SupportKitConstants.issue issue;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public SupportKitConstants.issue getIssue() {
        return issue;
    }

    public void setIssue(SupportKitConstants.issue issue) {
        this.issue = issue;
    }
}
