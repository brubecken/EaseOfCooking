package com.example.easeofcooking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    ImageView recipeImage;
    TextView recipeTitle, recipeCountry, recipeTime, recipeKitchenware, recipeIngredients, recipeDescription, recipeClosing;
    String imageUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        recipeImage = findViewById(R.id.ivImage);
        recipeTitle = findViewById(R.id.tvTitle);
        recipeCountry = findViewById(R.id.tvCountry);
        recipeTime = findViewById(R.id.tvTime);
        recipeKitchenware = findViewById(R.id.tvKitchenware);
        recipeIngredients = findViewById(R.id.tvIngredients);
        recipeDescription = findViewById(R.id.tvDescription);
        recipeClosing = findViewById(R.id.tvClosing);


        Bundle mBundle = getIntent().getExtras();

        if (mBundle != null) {

            imageUrl = mBundle.getString("Picture");
            recipeTitle.setText(mBundle.getString("Title"));
            recipeCountry.setText(mBundle.getString("Country"));
            recipeTime.setText(mBundle.getString("Time"));
            recipeKitchenware.setText(mBundle.getString("Kitchenware"));
            recipeIngredients.setText(mBundle.getString("Ingredients"));
            recipeDescription.setText(mBundle.getString("Description"));
            recipeClosing.setText(mBundle.getString("Closing"));

            Glide.with(this)
                    .load(mBundle.getString("Picture"))
                    .into(recipeImage);
        }

    }
}
