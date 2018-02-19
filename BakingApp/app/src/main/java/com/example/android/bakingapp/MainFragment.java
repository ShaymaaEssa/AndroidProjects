package com.example.android.bakingapp;


import android.content.Intent;
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
        recipesDataListView = (ListView) view.findViewById(R.id.listView_mainfragment_recipesitems);
        adapter = new RecipesItemsAdapter(getActivity(),recipesData);
        recipesDataListView.setAdapter(adapter);

        recipesDataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(DetailActivity.selectedRecipeSteps, recipesData.get(position).getSteps());
                intent.putExtra(DetailActivity.selectedRecipeStepsBundle,bundle);
                startActivity(intent);
            }
        });


        getRecipesData();

        return view;
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
                                JSONObject currentIngredientsObject = ingredientsJsonData.getJSONObject(i);
                                int quantity = Integer.parseInt(currentIngredientsObject.getString("quantity"));
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
