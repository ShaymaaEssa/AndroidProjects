package com.example.android.bakingapp;

import android.content.Context;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.bakingapp.Model.RecipesData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by goeic admin on 18-Feb-18.
 */

public class RecipesItemsAdapter extends ArrayAdapter<RecipesData> {

    Context context;
    List<RecipesData>recipesDatas;
    public RecipesItemsAdapter (Context context, List<RecipesData> recipesDatas){
        super(context,0,recipesDatas);
        this.context = context;
        this.recipesDatas = recipesDatas;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null){ //check if the view is new and not reusable so inflate it.
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.reciepsdata_list_item,parent,false);
        }

        TextView txtRecipesListItem = (TextView)listItemView.findViewById(R.id.txt_recipeslistitem);
        final RecipesData recipesDataItem = getItem(position);
        txtRecipesListItem.setText(recipesDataItem.getName());

        return listItemView;
    }

    public void setRecipesDatas (List<RecipesData> recipesDatas){
        this.recipesDatas = recipesDatas;
        notifyDataSetChanged();
    }
}
