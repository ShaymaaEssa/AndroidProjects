package com.example.android.bakingapp.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by goeic admin on 18-Feb-18.
 */

public class RecipesData {
    int id;
    String name;
    List<Ingredients> ingredients = new ArrayList<Ingredients>();
    List<Steps> steps = new ArrayList<Steps>();
    int servings;
    String image;

    public RecipesData(int id, String name, List<Ingredients> ingredients, List<Steps> steps, int servings, String image) {
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

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public List<Steps> getSteps() {
        return steps;
    }

    public int getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }
}
