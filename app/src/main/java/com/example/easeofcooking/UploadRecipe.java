package com.example.easeofcooking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;

public class UploadRecipe extends AppCompatActivity {

    ImageView recipeImage;
    Uri uri;
    EditText txt_RecipeName, txt_Country, txt_Time, txt_Kitchenware, txt_Ingredients, txt_Description, txt_Closing;
    String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_recipe);

        recipeImage = findViewById(R.id.iv_recipeImage);
        txt_RecipeName = findViewById(R.id.txt_RecipeName);
        txt_Country = findViewById(R.id.txt_Country);
        txt_Time = findViewById(R.id.txt_Time);
        txt_Kitchenware = findViewById(R.id.txt_Kitchenware);
        txt_Ingredients = findViewById(R.id.txt_Ingredients);
        txt_Description = findViewById(R.id.txt_Description);
        txt_Closing = findViewById(R.id.txt_Closing);

    }

    public void btnSelectImage(View view) {

        Intent photoPicker = new Intent(Intent.ACTION_PICK);
        photoPicker.setType("image/*");
        startActivityForResult(photoPicker, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && data != null) {

            uri = data.getData();
            recipeImage.setImageURI(uri);

        } else Toast.makeText(this, "Please select an image.", Toast.LENGTH_SHORT).show();

    }

    public void uploadImage() {

        //TODO  Recipe image not appearing
        StorageReference storageReference = FirebaseStorage.getInstance()
                .getReference().child("RecipeImage").child(uri.getLastPathSegment());

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Recipe Uploading...");
        progressDialog.show();


        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete()) ;
                Uri urlImage = uriTask.getResult();
                imageUrl = urlImage.toString();
                uploadRecipe();
                Toast.makeText(UploadRecipe.this, "Picture Uploaded", Toast.LENGTH_SHORT);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void btnUploadRecipe(View view) {
        uploadImage();
    }

    public void uploadRecipe() {

        RecipeData recipeData = new RecipeData(
                txt_RecipeName.getText().toString(),
                txt_Country.getText().toString(),
                txt_Time.getText().toString(),
                txt_Kitchenware.getText().toString(),
                txt_Ingredients.getText().toString(),
                txt_Description.getText().toString(),
                txt_Closing.getText().toString(),
                imageUrl
        );

        String myCurrentDateTime = DateFormat.getDateTimeInstance()
                .format(Calendar.getInstance().getTime());

        FirebaseDatabase.getInstance().getReference("Recipe")
                .child(myCurrentDateTime).setValue(recipeData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {

                    Toast.makeText(UploadRecipe.this, "Recipe Uploaded", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(UploadRecipe.this, "Upload Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
