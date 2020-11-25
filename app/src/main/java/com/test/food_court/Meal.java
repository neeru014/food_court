package com.test.food_court;

public class Meal {
    public static int id=0;
    private String Name;
    private String Price;
    private String MealPic;



    public Meal() {
    }

    public Meal( String name, String price, String mealpic){

       this.Name = name;
       this.Price = price;
       this.MealPic = mealpic;

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        this.Price = price;
    }

    public String getMealPic() {
        return MealPic;
    }

    public void setMealPic(String mealpic) {
        this.MealPic = mealpic;
    }
}
