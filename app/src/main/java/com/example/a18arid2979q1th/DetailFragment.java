package com.example.a18arid2979q1th;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class DetailFragment extends Fragment {

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_detail, container, false);
        String Unit=getArguments().getString("unit");
        String Color=getArguments().getString("color");

        EditText unit=(EditText) view.findViewById(R.id.editText1);
        EditText color=(EditText) view.findViewById(R.id.editText2);

        if(Color.equals("Red")){
            color.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
        }else if(Color.equals("Green")){
            color.setTextColor(ContextCompat.getColor(getContext(), R.color.green));
        }else if(Color.equals("Blue")) {
            color.setTextColor(ContextCompat.getColor(getContext(), R.color.blue));
        }

        if(Unit.equals("Days")){
            color.setText(String.valueOf(getArguments().getInt("days")));
        }else if(Unit.equals("Months")){
            color.setText(String.valueOf(getArguments().getInt("months")));
        }else {
            color.setText(String.valueOf(getArguments().getInt("years")));
        }

        unit.setText("Age in"+" "+Unit);
        return view;
    }

}