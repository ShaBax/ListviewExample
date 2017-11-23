package net.elektrasoft.shahbazmancho.listviewexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ShaBax on 11/23/2017.
 */

public class CustomAdapter extends ArrayAdapter<DataModel> implements View.OnClickListener{

    private ArrayList<DataModel> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView fname;
        TextView lname;
    }

    public CustomAdapter(ArrayList<DataModel> data, Context context) {
        super(context, R.layout.list_view_items, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        DataModel dataModel=(DataModel)object;

        switch (v.getId())
        {
            case R.id.fnameTextView:
                Toast.makeText(v.getContext(), "First Name " +dataModel.getFname(), Toast.LENGTH_LONG).show();
                break;
            case R.id.listViewmyrides:
                Toast.makeText(v.getContext(), "Last Name: " +dataModel.getLname(), Toast.LENGTH_LONG).show();
                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        DataModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_view_items, parent, false);
            viewHolder.fname = (TextView) convertView.findViewById(R.id.fnameTextView);
            viewHolder.lname = (TextView) convertView.findViewById(R.id.lnameTextView);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        lastPosition = position;

        viewHolder.fname.setText(dataModel.getFname());
        viewHolder.lname.setText(dataModel.getLname());
        // Return the completed view to render on screen
        return convertView;
    }
}
