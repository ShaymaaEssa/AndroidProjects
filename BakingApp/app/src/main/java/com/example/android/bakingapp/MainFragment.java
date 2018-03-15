package com.example.android.bakingapp;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Movie;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.bakingapp.Model.Ingredients;
import com.example.android.bakingapp.Model.RecipesData;
import com.example.android.bakingapp.Model.Steps;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.AppCompatDrawableManager.get;



/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    List<RecipesData>recipesData = new ArrayList<RecipesData>();
    final String Recipes_Data_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    SharedPreferences mPrefs;

    RecipesItemsAdapter adapter;
    ListView recipesDataListView;
    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        //Recipes listview
        recipesDataListView = (ListView) view.findViewById(R.id.listView_mainfragment_recipesitems);
        adapter = new RecipesItemsAdapter(getActivity(),recipesData);
        recipesDataListView.setAdapter(adapter);

        //create shared preference to store recipe object
        mPrefs = this.getActivity().getSharedPreferences(DetailFragment.SHARED_PREFERENCE,Context.MODE_PRIVATE);

        recipesDataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                saveRecipeObjectInSharedPreference(recipesData.get(position));

                //to update widget with the recipe ingredients
                Intent intent = new Intent(RecipeAppWidget.ACTION_TEXT_CHANGED);
                ArrayList<Ingredients> selectedRecipeIngredients = recipesData.get(position).getIngredients();
                String ingrediantsText = recipesData.get(position).getName()+"\n \n";
                for (int i=0 ;i<selectedRecipeIngredients.size();i++){
                    ingrediantsText += selectedRecipeIngredients.get(i).toString() +" \n";
                }
                intent.putExtra("NewString", ingrediantsText);
                getActivity().getApplicationContext().sendBroadcast(intent);

                //to open the detail activity
                Intent intent2 = new Intent(getActivity(),DetailActivity.class);
                startActivity(intent2);
            }
        });
        getRecipesData();
        return view;
    }

    //save recipe object in sharedpreference
    //https://stackoverflow.com/questions/7145606/how-android-sharedpreferences-save-store-object
    private void saveRecipeObjectInSharedPreference(RecipesData recipe) {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(recipe);
        prefsEditor.putString(DetailFragment.RECIPE_SHARED_PREFERENCE, json);
        prefsEditor.commit();
    }

    private void getRecipesData() {
        recipesData.clear();
        Uri builtUri = Uri.parse(Recipes_Data_URL)
                .buildUpon()
                .build();
        StringRequest getRecipesDataRequest = new StringRequest(Request.Method.GET, builtUri.toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.contains("quantity")) {
                    try {
                        JSONArray jsonResult = new JSONArray(response);
                        for (int i = 0; i < jsonResult.length(); i++) {
                            JSONObject currentObject = jsonResult.getJSONObject(i);
                            int id = Integer.parseInt(currentObject.getString("id"));
                            String name = currentObject.getString("name");

                            ArrayList<Ingredients> ingredientsList = new ArrayList<Ingredients>();
                            JSONArray ingredientsJsonData = currentObject.getJSONArray("ingredients");
                            for (int j=0; j<ingredientsJsonData.length();j++){
                                JSONObject currentIngredientsObject = ingredientsJsonData.getJSONObject(j);
                                double quantity = Double.parseDouble(currentIngredientsObject.getString("quantity"));
                                String measure = currentIngredientsObject.getString("measure");
                                String ingredient = currentIngredientsObject.getString("ingredient");
                                Ingredients ingredientItem = new Ingredients(quantity,measure,ingredient);
                                ingredientsList.add(ingredientItem);
                            }

                            ArrayList<Steps> stepsList = new ArrayList<Steps>();
                            JSONArray stepsJsonData = currentObject.getJSONArray("steps");
                            for (int j=0; j<stepsJsonData.length();j++){
                                JSONObject currentStepObject = stepsJsonData.getJSONObject(j);
                                int stepsId = Integer.parseInt(currentStepObject.getString("id"));
                                String shortDescription = currentStepObject.getString("shortDescription");
                                String description = currentStepObject.getString("description");
                                String videoURL = currentStepObject.getString("videoURL");
                                String thumbnailURL=currentStepObject.getString("thumbnailURL");
                                Steps stepItem = new Steps(stepsId,shortDescription,description,videoURL,thumbnailURL);
                                stepsList.add(stepItem);
                            }

                            int servings = Integer.parseInt(currentObject.getString("servings"));
                            String image = currentObject.getString("image");


                            RecipesData recipesDataItem = new RecipesData(id,name,ingredientsList,stepsList,servings,image);
                            recipesData.add(recipesDataItem);
                            Log.v("MainFragment1",recipesDataItem.getName());
                        }

                        Log.v("MainFragment1",recipesData.get(1).getName());

                        adapter.setRecipesDatas(recipesData);
                        //adapter = new RecipesItemsAdapter(getActivity(),recipesData);



                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "Error in handling Json Data", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(getContext()).add(getRecipesDataRequest);
    }

}
