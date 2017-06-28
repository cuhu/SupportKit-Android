package uk.dreamr.rhdev.dreamrsupportkit;

import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jack on 27/06/2017.
 */

public class SupportKitDialog extends DialogFragment {

    private TextView txtTitle;
    private TextView txtDescription;
    private ListView listView;
    private EditText editIssue;
    private ImageView btnArrow;
    private Button btnCancel;
    private Button btnSend;
    private int issueSelected = -1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_support, container, false);
        setupUI(view);
        return view;
    }

    private void setupUI(View view){
        txtTitle = (TextView) view.findViewById(R.id.support_kit_title);
        txtDescription= (TextView) view.findViewById(R.id.support_kit_description);
        editIssue = (EditText) view.findViewById(R.id.support_kit_issue_text);
        btnArrow = (ImageView) view.findViewById(R.id.support_kit_arrow);
        setupList(view);
        setupButtons(view);
        setResponseHandler();

    }
    private void setupList(View view){
        listView = (ListView) view.findViewById(R.id.support_kit_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getCorrectContext(),android.R.layout.simple_list_item_1,SupportKitConstants.ISSUES);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                issueSelected = position;
                showListOrIssue(true,SupportKitConstants.ISSUES[position]);
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
                    SupportKitEndPoint.getInstance().postIssue(
                            SupportKitConstants.getIssueMap().get(SupportKitConstants.ISSUES[issueSelected]),
                            txtIssue
                    );
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
        txtDescription.setText((issue == null ? SupportKitConstants.ISSUE_DESCRIPTION : issue));
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


}
