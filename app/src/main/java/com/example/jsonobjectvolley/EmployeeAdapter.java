package com.example.jsonobjectvolley;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EmployeeAdapter extends BaseAdapter {
    Context context;
    ArrayList<Employee> employees;

    public EmployeeAdapter(Context context, ArrayList<Employee> employees) {
        this.context = context;
        this.employees = employees;
    }

    @Override
    public int getCount() {
        return employees.size();
    }

    @Override
    public Object getItem(int position) {
        return employees.get(position);
    }

    @Override
    public long getItemId(int i) {
        return (long) getItem(i);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if(v == null){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item, null);
        }
        Employee e = (Employee) getItem(position);
        TextView name = v.findViewById(R.id.tv_name);
        TextView age = v.findViewById(R.id.tv_age);
        TextView email = v.findViewById(R.id.tv_email);

        name.setText(e.getName()+" ");
        age.setText(e.getAge()+" ");
        email.setText(e.getMail()+" ");

        return v;
    }
}
