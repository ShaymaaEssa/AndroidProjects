package com.udacity.sandwichclub.utils;

import android.widget.Toast;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try{
            JSONObject data = new JSONObject(json);
            JSONObject name = data.getJSONObject("name");
            String mainName = name.getString("mainName");

            JSONArray alsoKnownAs = name.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAsList = new ArrayList<String>();
            for (int i=0; i<alsoKnownAs.length(); i++){
                alsoKnownAsList.add(alsoKnownAs.getString(i));
            }

            String placeOfOrigin = data.getString("placeOfOrigin");
            String description = data.getString("description");
            String image = data.getString("image");

            JSONArray ingredients = data.getJSONArray("ingredients");
            List<String> ingredientsList = new ArrayList<String>();
            for (int i=0; i<ingredients.length(); i++){
                ingredientsList.add(ingredients.getString(i));
            }

            Sandwich sandwich = new Sandwich(mainName,alsoKnownAsList,placeOfOrigin,description,image,ingredientsList);
            return sandwich;


        }catch (JSONException jsonException){
            jsonException.printStackTrace();
            return null;
        }

    }
}
