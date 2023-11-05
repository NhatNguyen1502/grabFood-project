package GrabFood;

import java.util.ArrayList;


public class Restaurant extends Person {
    private static int ResID = 0;
    private static ArrayList<Restaurant> resList = new ArrayList<Restaurant>();
    private ArrayList<FoodItem> foodList = new ArrayList<FoodItem>();
    private ArrayList<String> orders = new ArrayList<String>();

    public Restaurant() {
        super("New restaurant", "Null", new Address());
        resList.add(this);
        ResID++;
    }
    public Restaurant(String name, String phoneNumber, Address address) {
        super(name, phoneNumber, address);
        if (address == null) this.address = new Address();
        resList.add(this);
        ResID++;
    }

    public String getName() {
        return this.name;
    }

    public void addFood(FoodItem foodItem) {
        foodList.add(foodItem);
        foodItem.setOfRes(this);
        foodItem.setAddress(address.toString());
    }

    public void removeFoodItem(int index) {
        FoodItem.removeFood(foodList.remove(index));
    }
    
    public int amountOfFood() {
        return foodList.size();
    }
    
    public void showFoodList() {
        for (int i = 0; i < foodList.size(); i++) {
            System.out.printf(i+1 + ". %-20s %.3f VND \n", foodList.get(i).getName(),foodList.get(i).getPrice());
        }
    }

    public FoodItem getFoodItem(int index) {
        return foodList.get(index);
    }

    public static Restaurant getRestaurant(int index) {
        return resList.get(index);
    }

    public static int amountOfRes() {
        return ResID;
    }

    public static void showResList() {
        System.out.println("_______________RESTAURANT LIST______________");
        for (int i = 0; i < resList.size(); i++) {
            System.out.println(i+1 + ". "  + resList.get(i).getName());
        }
    }

    public void receiveOrder(String orderInf) {
        orders.add(orderInf);

    }

    public void showOrders() {
        int count = 0;
        if (orders.size() == 0) System.out.println("No orders yet!");
        else {
            for (String i : orders) {
                System.out.println("------------------------------");
                System.out.println(++count +". \n" +i);
                System.out.println("------------------------------");
            }
        }
    }
}
