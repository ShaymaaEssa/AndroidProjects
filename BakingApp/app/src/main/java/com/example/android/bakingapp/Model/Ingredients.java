package com.example.android.bakingapp.Model;

/**
 * Created by goeic admin on 18-Feb-18.
 */

public class Ingredients {
    double quantity;
    String measure;
    String ingredient;

    public Ingredients(double quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public String getIngredient() {
        return ingredient;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    @Override
    public String toString() {
        return quantity +" " +measure +" "+ingredient;
    }
}
