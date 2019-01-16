package com.android.developer.kalikasan;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class CardTabA1 extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner ratingSpinner1,ratingSpinner2,ratingSpinner3,ratingSpinner4,
            ratingSpinner5,ratingSpinner6,ratingSpinner7,ratingSpinner8,ratingSpinner9,ratingSpinner10;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_card_tab_a1,container,false);
        String[] values = {"Excellent","Very Good","Good","Fair","Poor"};

         ratingSpinner1 =  v.findViewById(R.id.questionSpinner1);
         ratingSpinner1.setOnItemSelectedListener(this);

        ratingSpinner2 =  v.findViewById(R.id.questionSpinner2);
        ratingSpinner2.setOnItemSelectedListener(this);

        ratingSpinner3 =  v.findViewById(R.id.questionSpinner3);
        ratingSpinner3.setOnItemSelectedListener(this);

        ratingSpinner4 =  v.findViewById(R.id.questionSpinner4);
        ratingSpinner4.setOnItemSelectedListener(this);

        ratingSpinner5 =  v.findViewById(R.id.questionSpinner5);
        ratingSpinner5.setOnItemSelectedListener(this);

        ratingSpinner6 =  v.findViewById(R.id.questionSpinner6);
        ratingSpinner6.setOnItemSelectedListener(this);

        ratingSpinner7 =  v.findViewById(R.id.questionSpinner7);
        ratingSpinner7.setOnItemSelectedListener(this);

        ratingSpinner8 =  v.findViewById(R.id.questionSpinner8);
        ratingSpinner8.setOnItemSelectedListener(this);

        ratingSpinner9 =  v.findViewById(R.id.questionSpinner9);
        ratingSpinner9.setOnItemSelectedListener(this);

        ratingSpinner10 =  v.findViewById(R.id.questionSpinner10);
        ratingSpinner10.setOnItemSelectedListener(this);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,values);
        ratingSpinner1.setAdapter(adapter);
        ratingSpinner1.setOnItemSelectedListener(this);

        ratingSpinner2.setAdapter(adapter);
        ratingSpinner2.setOnItemSelectedListener(this);

        ratingSpinner3.setAdapter(adapter);
        ratingSpinner3.setOnItemSelectedListener(this);

        ratingSpinner4.setAdapter(adapter);
        ratingSpinner4.setOnItemSelectedListener(this);

        ratingSpinner5.setAdapter(adapter);
        ratingSpinner5.setOnItemSelectedListener(this);

        ratingSpinner6.setAdapter(adapter);
        ratingSpinner6.setOnItemSelectedListener(this);

        ratingSpinner7.setAdapter(adapter);
        ratingSpinner7.setOnItemSelectedListener(this);

        ratingSpinner8.setAdapter(adapter);
        ratingSpinner8.setOnItemSelectedListener(this);

        ratingSpinner9.setAdapter(adapter);
        ratingSpinner9.setOnItemSelectedListener(this);

        ratingSpinner10.setAdapter(adapter);
        ratingSpinner10.setOnItemSelectedListener(this);




        return v;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}


