package com.example.android.bakingapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by goeic admin on 18-Feb-18.
 */

public class RecipesData {
    int id;
    String name;
    ArrayList<Ingredients> ingredients = new ArrayList<Ingredients>();
    ArrayList<Steps> steps = new ArrayList<Steps>();
    int servings;
    String image;

    public RecipesData(int id, String name, ArrayList<Ingredients> ingredients, ArrayList<Steps> steps, int servings, String image) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Ingredients> getIngredients() {
        return ingredients;
    }

    public ArrayList<Steps> getSteps() {
        return steps;
    }

    public int getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }


}
