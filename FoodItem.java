package GrabFood;

import java.util.ArrayList;

public class FoodItem {
    private static int foodID = 0;
    private static ArrayList<FoodItem> listFood = new ArrayList<FoodItem>();
    private String name;
    private double price;
    private Restaurant ofRes;
    private String address;

    public FoodItem() {};
    public FoodItem(String name, double price) {
        this.name = name;
        this.price = price;
        listFood.add(this);
        foodID++;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setOfRes(Restaurant ofRes) {
        this.ofRes = ofRes;
    }
    public Restaurant getOfRes() {
        return ofRes;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public static int amountOfFood() {
        return foodID;
    }

    public double getPrice() {
        return price;
    }

    public static void showFoodList() {
        System.out.println("_______________FOOD LIST______________");
        for(int i = 0; i < foodID; i++ ) {
            System.out.printf(i+1 +". %-20s %.3f VND \n",listFood.get(i).getName(), listFood.get(i).getPrice());
        }
    }
    public static void removeFood(FoodItem food) {
        listFood.remove(food);
        foodID--;
    }
    public static FoodItem getFood(int index) {
        return listFood.get(index);
    }

    @Override
    public String toString() {
        return  "Name: " + name + "\nPrice: " + price + "VND" +
                "\nAddress of restaurant: " + address + "\n";
    }
}
