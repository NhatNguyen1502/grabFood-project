package GrabFood;

import java.util.ArrayList;

public class Customer extends Person{
    private int amountFooditem = 0;
    private ArrayList<FoodItem> cart = new ArrayList<FoodItem>();
    private ArrayList<Order> orderedList = new ArrayList<Order>();

    public Customer() {
        super("Null", "Null", new Address());
    };
    public Customer(String name, String phoneNumber, Address address) {
        super(name, phoneNumber, address);
    }

    public void pick(FoodItem food) {
        cart.add(food);
        amountFooditem++;
    }

    public void removeFoodItem(int index) {
        cart.remove(index);
        amountFooditem--;
    }

    public void cleanCart() {
        cart.clear();
        amountFooditem = 0;
    }

    public void order() {
        ArrayList<FoodItem> newCart = new ArrayList<FoodItem>(cart);
        orderedList.add(new Order(this, newCart ));
        String orderInf;
        for (FoodItem i : cart) {
            orderInf = this.toString()+"\nFood name: "+i.getName()+"\nPrice: " + i.getPrice()+" VND\n";
            i.getOfRes().receiveOrder(orderInf);
        }
        cleanCart();
    }

    public void showOrderedList() {
        System.out.println("------------------------------");
        if (orderedList.size() < 1) System.out.println("You have not ordered yet");
        for (int i = 0; i < orderedList.size(); i++) {
            System.out.println(i+1 + ".");
            for (FoodItem j : orderedList.get(i).getFoodList()) {
                System.out.printf("%s: %.3f VND \n",j.getName(),j.getPrice());
            }
            System.out.printf("Total price: %.3f VND\n",orderedList.get(i).getTotalPrice());
            System.out.println("------------------------------");
        }
    }

    public int getAmountFooditem() {
        return amountFooditem;
    }

    public void viewCart() {
        double total = 0;
        FoodItem food = null;
        for (int i = 0; i < amountFooditem; i++) {
            food = cart.get(i);
            System.out.printf(i+1 + ". %-20s %.3f VND \n",food.getName(),food.getPrice());
            total += food.getPrice();
        }
        System.out.printf("Total: %.3f VND \n", total);
    }
}
