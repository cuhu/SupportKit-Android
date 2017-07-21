package uk.dreamr.rhdev.dreamrsupportkit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mylokaye on 30/06/2017.
 */

class SupportKitListAdapter extends ArrayAdapter<String> {

    private LayoutInflater inflater;
    private ArrayList<String> issues;

    public SupportKitListAdapter(Context context, ArrayList<String> issues) {
        super(context, 0, issues);
        this.issues = issues;
        this.inflater = LayoutInflater.from(context);
    }

    private static class PlaceHolder {
        TextView issue;
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PlaceHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.view_issue_row, parent, false);
            holder = new PlaceHolder();
            holder.issue = (TextView) convertView.findViewById(R.id.support_kit_row_issue);
            convertView.setTag(holder);
        }else{
            holder = (PlaceHolder) convertView.getTag();
        }
        holder.issue.setText(issues.get(position));
        return convertView;
    }
}
