package com.example.android.bakingapp.Model;

/**
 * Created by goeic admin on 18-Feb-18.
 */

public class Ingredients {
    int quantity;
    String measure;
    String ingredient;

    public Ingredients(int quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public String getIngredient() {
        return ingredient;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }
}
