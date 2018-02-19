package com.example.android.bakingapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.Model.RecipesData;
import com.example.android.bakingapp.Model.Steps;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;

/**
 * Created by goeic admin on 19-Feb-18.
 */

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailViewHolder> {


    ArrayList<Steps> stepsList ;
    Context context;

    public DetailAdapter (Context context, ArrayList<Steps> stepsList){
        this.stepsList = stepsList;
        this.context = context;
    }


    @Override
    public DetailAdapter.DetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_steps_list_item,parent,false);
        DetailViewHolder holder = new DetailViewHolder(row);
        return  holder;
    }

    @Override
    public void onBindViewHolder(DetailAdapter.DetailViewHolder holder, int position) {
        if (position == 0){
            holder.txtStepItem.setText("Ingredients");
        }
        else
        {
            holder.txtStepItem.setText(stepsList.get(position-1).getShortDescription());
        }

    }

    @Override
    public int getItemCount() {
        return stepsList.size()+1;
    }

    class DetailViewHolder extends RecyclerView.ViewHolder {
        TextView txtStepItem;

        public DetailViewHolder (View itemView){
            super(itemView);
            txtStepItem = (TextView)itemView.findViewById(R.id.txt_steps_list_item);
        }

    }
}
