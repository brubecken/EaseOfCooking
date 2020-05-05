package com.example.easeofcooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    ArrayList<RecipeData> myRecipeList;
    //TODO Necessary? ProgressDialog
    ProgressDialog progressDialog;
    MyAdapter myAdapter;
    EditText txt_Search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mRecyclerView = findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        txt_Search = findViewById(R.id.txt_SearchText);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Recipes...");

        myRecipeList = new ArrayList<>();

//        mRecipeData = new RecipeData("Pan Seared Boar", "string","Japan","15", "Knife, Cast Iron Pan", "Boar", "Cook Boar in Pan ","Enjoy!");
//        myRecipeList.add(mRecipeData);
//        mRecipeData = new RecipeData("Pan Seared Boar", R.drawable.test,"Japan","15", "Knife, Cast Iron Pan", "Boar", "Cook Boar in Pan ","Enjoy!");
//        myRecipeList.add(mRecipeData);
//        mRecipeData = new RecipeData("Pan Seared Boar", R.drawable.test,"Japan","15", "Knife, Cast Iron Pan", "Boar", "Cook Boar in Pan ","Enjoy!");
//        myRecipeList.add(mRecipeData);
//        mRecipeData = new RecipeData("Pan Seared Boar", R.drawable.test,"Japan","15", "Knife, Cast Iron Pan", "Boar", "Cook Boar in Pan ","Enjoy!");
//        myRecipeList.add(mRecipeData);

        final MyAdapter myAdapter = new MyAdapter(MainActivity.this, myRecipeList);
        mRecyclerView.setAdapter(myAdapter);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Recipe");

        progressDialog.show();

        ValueEventListener eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                myRecipeList.clear();

                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {

                    RecipeData recipeData = itemSnapshot.getValue(RecipeData.class);
                    myRecipeList.add(recipeData);
                }

                myAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

        txt_Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filter(s.toString());
            }
        });

    }

    // TODO fix search
    private void filter(String text) {

        ArrayList<RecipeData> filterList = new ArrayList<>();

        for (RecipeData recipe : myRecipeList) {

            if (recipe.getRecipeName().toLowerCase().contains(text.toLowerCase())) {

                filterList.add(recipe);
            }
        }

        myAdapter.filteredList(filterList);
    }


    public void btnUploadActivity(View view) {

        startActivity(new Intent(this, UploadRecipe.class));
    }
}
