package com.example.easeofcooking;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecipeViewHolder> {

    private Context mContext;
    private ArrayList<RecipeData> myRecipeList;

    public MyAdapter(Context mContext, ArrayList<RecipeData> myRecipeList) {
        this.mContext = mContext;
        this.myRecipeList = myRecipeList;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_row_recipe, viewGroup, false);

        return new RecipeViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecipeViewHolder recipeViewHolder, int position) {

        Glide.with(mContext)
                .load(myRecipeList.get(position).getRecipePicture())
                .into(recipeViewHolder.imageView);

        recipeViewHolder.mTitle.setText(myRecipeList.get(position).getRecipeName());
        recipeViewHolder.mCountry.setText(myRecipeList.get(position).getRecipeCountry());
        recipeViewHolder.mTime.setText(myRecipeList.get(position).getRecipeTime());
        recipeViewHolder.mDescription.setText(myRecipeList.get(position).getRecipeDescription());


        recipeViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("Picture", myRecipeList.get(recipeViewHolder.getAdapterPosition()).getRecipePicture());
                intent.putExtra("Country", myRecipeList.get(recipeViewHolder.getAdapterPosition()).getRecipeCountry());
                intent.putExtra("Time", myRecipeList.get(recipeViewHolder.getAdapterPosition()).getRecipeTime());
                intent.putExtra("Description", myRecipeList.get(recipeViewHolder.getAdapterPosition()).getRecipeDescription());
                intent.putExtra("Kitchenware", myRecipeList.get(recipeViewHolder.getAdapterPosition()).getRecipeKitchenware());
                intent.putExtra("Ingredients", myRecipeList.get(recipeViewHolder.getAdapterPosition()).getRecipeIngredients());
                intent.putExtra("Closing", myRecipeList.get(recipeViewHolder.getAdapterPosition()).getRecipeClosing());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myRecipeList.size();
    }

    public void filteredList(ArrayList<RecipeData> filterList) {

        myRecipeList = filterList;
        notifyDataSetChanged();
    }
}

class RecipeViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView mTitle, mCountry, mTime, mDescription, mKitchenware, mIngredients, mClosing;
    CardView mCardView;


    public RecipeViewHolder(View itemView) {
        super(itemView);

        // TODO add in other TextView items
        imageView = itemView.findViewById(R.id.ivImage);
        mTitle = itemView.findViewById(R.id.tvTitle);
        mDescription = itemView.findViewById(R.id.tvDescription);
        mTime = itemView.findViewById(R.id.tvTime);
        mCountry = itemView.findViewById(R.id.tvCountry);
        mKitchenware = itemView.findViewById(R.id.tvKitchenware);
        mIngredients = itemView.findViewById(R.id.tvIngredients);
        mClosing = itemView.findViewById(R.id.tvClosing);

        mCardView = itemView.findViewById(R.id.myCardView);
    }
}
