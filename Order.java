package GrabFood;

import java.util.ArrayList;

public class Order {
    private static int id = 0;
    private static ArrayList<Order> orderList = new ArrayList<Order>(); 
    private ArrayList<FoodItem> foodList = new ArrayList<FoodItem>();
    private double totalPrice;
    private Customer customer;

    public Order(Order order) {
        this.customer = order.customer;
        this.foodList = order.foodList;
    }

    public Order(Customer customer, ArrayList<FoodItem> orders) {
        this.customer = customer;
        this.foodList = orders;
        orderList.add(this);
        id++;
        for (FoodItem i : orders) totalPrice += i.getPrice();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }
    

    public ArrayList<FoodItem> getFoodList() {
        return foodList;
    }

    public static void removeOrder(int index) {
        orderList.remove(index);
        id--;
    }

    public void showFoodList() {
        for (int i = 0; i < foodList.size(); i++) {
            System.out.println("Item " + (i+1) + ": \n" +foodList.get(i).toString());
        }
    }

    public static Order getOrder(int index) {
        return orderList.get(index);
    }

    public static int getId() {
        return id;
    }

    public static void showOrderList() {
        for (int i = 0; i < id; i++) {
            System.out.println("==============================");
            System.out.println(i+1 + ". ");
            System.out.println("------------------------------");
            System.out.println("Customer information: ");
            orderList.get(i).customer.viewInf();
            System.out.println("------------------------------");
            System.out.println("Food items infomation: ");
            System.out.println("------------------------------");
            orderList.get(i).showFoodList();
            System.out.println("------------------------------");
            System.out.println("Total price:"+orderList.get(i).getTotalPrice() + " VND");
            System.out.println("==============================");
        }
    }
}
