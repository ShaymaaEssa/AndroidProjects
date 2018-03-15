package com.example.android.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.android.bakingapp.Model.Steps;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    public static final String RECIPE_NUMBER = "number of recipe";

    final static String selectedRecipeSteps = "select recipe steps";
    final static String selectedRecipeStepsBundle = "select recipe steps bundle";

    int recipeNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

    }

}
