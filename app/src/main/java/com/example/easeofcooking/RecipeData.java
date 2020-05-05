package com.example.easeofcooking;

public class RecipeData {
    private String recipeName;
    private String recipeCountry;
    // In minutes
    private String recipeTime;
    private String recipeKitchenware;
    private String recipeIngredients;
    private String recipeDescription;
    private String recipeClosing;
    private String recipePicture;

    // Do not remove! Takes care of error
    public RecipeData() {

    }

    public RecipeData(String recipeName, String recipeCountry, String recipeTime, String recipeKitchenware, String recipeIngredients, String recipeDescription, String recipeClosing, String recipePicture) {
        this.recipeName = recipeName;
        this.recipePicture = recipePicture;
        this.recipeCountry = recipeCountry;
        this.recipeTime = recipeTime;
        this.recipeKitchenware = recipeKitchenware;
        this.recipeIngredients = recipeIngredients;
        this.recipeDescription = recipeDescription;
        this.recipeClosing = recipeClosing;
    }

    public String getRecipeName() {
        return recipeName;
    }

//    public void setRecipeName(String recipeName) {
//        this.recipeName = recipeName;
//    }

    public String getRecipePicture() {
        return recipePicture;
    }

//    public void setRecipePicture(String recipePicture) {
//        this.recipePicture = recipePicture;
//    }

    public String getRecipeCountry() {
        return recipeCountry;
    }

//    public void setRecipeCountry(String recipeCountry) {
//        this.recipeCountry = recipeCountry;
//    }

    public String getRecipeTime() {
        return recipeTime;
    }

//    public void setRecipeTime(String recipeTime) {
//        this.recipeTime = recipeTime;
//    }

    public String getRecipeKitchenware() {
        return recipeKitchenware;
    }

//    public void setRecipeKitchenware(String recipeKitchenware) {
//        this.recipeKitchenware = recipeKitchenware;
//    }

    public String getRecipeIngredients() {
        return recipeIngredients;
    }

//    public void setRecipeIngredients(String recipeIngredients) {
//        this.recipeIngredients = recipeIngredients;
//    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

//    public void setRecipeDescription(String recipeDescription) {
//        this.recipeDescription = recipeDescription;
//    }

    public String getRecipeClosing() {
        return recipeClosing;
    }

//    public void setRecipeClosing(String recipeClosing) {
//        this.recipeClosing = recipeClosing;
//    }
}
