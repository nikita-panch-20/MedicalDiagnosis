package com.gs.medicaldiagnosisexpertsystem;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;



public class UserList extends ArrayAdapter<User> {
    private Activity context;
    List<User> users;

    public UserList(Activity context, List<User> users) {
        super(context, R.layout.userlist_layout, users);
        this.context = context;
        this.users = users;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.userlist_layout, null, true);

        TextView textViewFullName = (TextView) listViewItem.findViewById(R.id.textViewFullName);
        TextView textViewUsername = (TextView) listViewItem.findViewById(R.id.textViewUserName);
        TextView textViewEmail = (TextView) listViewItem.findViewById(R.id.textViewMobile);
        TextView textViewAge = (TextView) listViewItem.findViewById(R.id.textViewAge);
        TextView textViewGender = (TextView) listViewItem.findViewById(R.id.textViewGender);
        TextView textViewHeight = (TextView) listViewItem.findViewById(R.id.textViewHeight);
        TextView textViewWeight = (TextView) listViewItem.findViewById(R.id.textViewWeight);

        User user = users.get(position);
        textViewFullName.setText(user.getFullname());
        textViewUsername.setText(user.getMobile());
        textViewAge.setText(user.getA());
        textViewGender.setText(user.getG());
        textViewHeight.setText(user.getH());
        textViewEmail.setText(user.getEmail());
        textViewWeight.setText(user.getW());

        return listViewItem;
    }
}
