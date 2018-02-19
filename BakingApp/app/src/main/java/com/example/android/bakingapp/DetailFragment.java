package com.example.android.bakingapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.bakingapp.Model.Steps;
import com.example.android.bakingapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    int selectedRecipeId = 0 ;
    RecyclerView recyclerViewStepsMenu;
    DetailAdapter detailAdapter;
    ArrayList<Steps> selectedRecipeSteps;



    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_detail, container, false);
        recyclerViewStepsMenu = (RecyclerView)view.findViewById(R.id.recyclerview_detailfragment_detailmenu);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerViewStepsMenu.setLayoutManager(new LinearLayoutManager(getContext()));
        detailAdapter = new DetailAdapter(getContext(),selectedRecipeSteps);
        recyclerViewStepsMenu.setAdapter(detailAdapter);
    }

    public void setRecipeNumber (ArrayList<Steps> selectedRecipeSteps){
        this.selectedRecipeSteps = selectedRecipeSteps;
    }
}
