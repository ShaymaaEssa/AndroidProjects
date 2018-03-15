package com.example.android.bakingapp;


import android.content.Context;
import android.content.SharedPreferences;
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

import com.example.android.bakingapp.Model.RecipesData;
import com.example.android.bakingapp.Model.Steps;
import com.example.android.bakingapp.R;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    public static final String SHARED_PREFERENCE = "bakingAppSharedPreference";
    public static final String RECIPE_SHARED_PREFERENCE = "selectedRecipe"; //selected recipe saved in shared preference
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

        //get the selected recipe from shared preference
        SharedPreferences mPrefs = this.getActivity().getSharedPreferences(SHARED_PREFERENCE,Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString(RECIPE_SHARED_PREFERENCE, "");
        RecipesData obj = gson.fromJson(json, RecipesData.class);
        setRecipeNumber(obj.getSteps());
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
