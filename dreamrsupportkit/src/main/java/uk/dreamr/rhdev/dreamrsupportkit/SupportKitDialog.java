package uk.dreamr.rhdev.dreamrsupportkit;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Jack on 27/06/2017.
 */

public class SupportKitDialog extends DialogFragment implements SupportKitView{

    private TextView txtTitle;
    private TextView txtDescription;
    private ListView listView;
    private EditText editIssue;
    private ImageView btnArrow;
    private Button btnCancel;
    private Button btnSend;
    private int issueSelected = -1;
    private SupportKitIssue issues;
    private String emailTo;
    private static final int REQUEST_SEND_MAIL = 1009;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_support, container, false);
        setupUI(view);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        resizeDialog(getDialog().getWindow(), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private void setupUI(View view){
        txtTitle = (TextView) view.findViewById(R.id.support_kit_title);
        txtDescription= (TextView) view.findViewById(R.id.support_kit_description);
        editIssue = (EditText) view.findViewById(R.id.support_kit_issue_text);
        btnArrow = (ImageView) view.findViewById(R.id.support_kit_arrow);
        fillList();
        setupList(view);
        setupButtons(view);
        setResponseHandler();
        setStyle();

    }

    private void fillList(){
        ArrayList<String> issueTypes = getArguments().getStringArrayList(SupportKitConstants.SUPPORTKIT_ISSUES_LIST);
        ArrayList<String> issueMessages = getArguments().getStringArrayList(SupportKitConstants.SUPPORTKIT_ISSUE_MESSAGES_LIST);
        emailTo = getArguments().getString(SupportKitConstants.SUPPORTKIT_EMAIL);
        if(issueTypes != null && issueMessages != null){
            issues = new SupportKitIssue(issueTypes, issueMessages);
        }else{
            issues = new SupportKitIssue();
            issues.addIssue(SupportKitConstants.ISSUE_FEEDBACK, SupportKitConstants.ISSUE_FEEDBACK_NAME);
            issues.addIssue(SupportKitConstants.ISSUE_BUG, SupportKitConstants.ISSUE_BUG_NAME);
            issues.addIssue(SupportKitConstants.ISSUE_PAYMENT, SupportKitConstants.ISSUE_PAYMENT_NAME);
            issues.addIssue(SupportKitConstants.ISSUE_LEGAL, SupportKitConstants.ISSUE_LEGAL_NAME);
        }
    }
    private void setupList(View view){
        listView = (ListView) view.findViewById(R.id.support_kit_list);
        SupportKitListAdapter adapter = new SupportKitListAdapter(getCorrectContext(),issues.getIssueMessages());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                issueSelected = position;
                showListOrIssue(true,issues.getIssueMessages().get(position));
            }
        });
    }

    private void setupButtons(View view){
        btnCancel = (Button) view.findViewById(R.id.support_kit_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btnSend = (Button) view.findViewById(R.id.support_kit_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtIssue;
                if(issueSelected > -1 && (txtIssue = editIssue.getText().toString()).length() > 0){
                    Log.d(SupportKitConstants.SUPPORTKIT_LOG, "sending issue");
                    SupportKitEmailSender emailSender = new SupportKitEmailSender(SupportKitDialog.this);
                    emailSender.setEmailAddressTo(emailTo);
                    emailSender.setEmailSubject(issues.getIssueMessages().get(issueSelected));
                    emailSender.setEmailBody(txtIssue);
                    emailSender.sendEmail();
                }else{
                    showMessage("Please give us some information");
                }
            }
        });
        btnArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showListOrIssue(false,null);
            }
        });
    }
    /*
    when sending api todo - setup to use default api endpoint
    SupportKitEndPoint.getInstance().postIssue(
                            getCorrectContext(),
                            issues.getIssues().get(issueSelected),
                            txtIssue
                    );
     */

    @Override
    public void launchIntent(Intent emailIntent) {
        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail"));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getCorrectContext(), "There are no email applications installed.", Toast.LENGTH_SHORT).show();
        }
    }

    private void
        setResponseHandler(){
        SupportKitEndPoint.getInstance().setSupportKitResponse(new SupportKitResponse() {
            @Override
            public void errorCaught(String error) {
                showMessage(error);
            }

            @Override
            public void postSuccess() {
                showMessage("issue reported :)");
                dismiss();
            }
        });
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    private void showListOrIssue(boolean showIssue, @Nullable String issue){
        if(showIssue){
            listView.setVisibility(View.GONE);
            editIssue.setVisibility(View.VISIBLE);
            btnArrow.setVisibility(View.VISIBLE);
            btnSend.setVisibility(View.VISIBLE);

        }else{
            editIssue.setVisibility(View.GONE);
            btnArrow.setVisibility(View.GONE);
            btnSend.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            issueSelected = -1; // resetting value to default if clicked wrong catagory
        }
        txtDescription.setText((issue == null ? getString(R.string.issue_exp) : issue));
    }

    private void showMessage(String message){
        Toast.makeText(getCorrectContext(), message, Toast.LENGTH_SHORT).show();
    }

    private Context getCorrectContext(){
        if(Build.VERSION.SDK_INT >= 23){
            return getContext();
        }else{
            return ((Context) getActivity());
        }
    }

    private int fetchColor(int type) {
        int color = -1;
        switch(type){
            case SupportKitConstants.COLOUR_TYPE_PRIMARY:
                color = R.attr.colorPrimary;
                break;
            case SupportKitConstants.COLOUR_TYPE_PRIMARYDARK:
                color = R.attr.colorPrimaryDark;
                break;
            case SupportKitConstants.COLOUR_TYPE_ACCENT:
                color = R.attr.colorAccent;
                break;
        }

        TypedValue typedValue = new TypedValue();
        TypedArray a = getCorrectContext().obtainStyledAttributes(typedValue.data, new int[] { color});
        int colorReturned = a.getColor(0, 0);
        a.recycle();
        return colorReturned;
    }

    private void setStyle(){
        styleText(txtTitle);
        styleButtons(btnCancel);
        styleButtons(btnSend);
    }

    private void styleText(TextView txt){
        txt.setTextColor(fetchColor(SupportKitConstants.COLOUR_TYPE_PRIMARYDARK));
    }

    private void styleButtons(Button btn){
        btn.setBackgroundColor(Color.TRANSPARENT);
        btn.setTextColor(fetchColor(SupportKitConstants.COLOUR_TYPE_PRIMARY));
    }

    private int getColourResource(int resource){
        if(Build.VERSION.SDK_INT >= 23){
            return getResources().getColor(resource, null);
        }else{
            return getResources().getColor(resource);
        }
    }

    private boolean isColorDark(int color){
        double darkness = 1-(0.299* Color.red(color) + 0.587*Color.green(color) + 0.114*Color.blue(color))/255;
        if(darkness<0.5){
            return false; // It's a light color
        }else{
            return true; // It's a dark color
        }
    }

    public static void resizeDialog(Window window, int width, int height) {
        try {
            window.setLayout(width, height);
            window.setGravity(Gravity.CENTER);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


}
