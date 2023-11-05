package GrabFood;

import java.util.ArrayList;
import java.util.Scanner;


public class DeliveryPerson extends Person {
    private ArrayList<Order> pickList = new ArrayList<Order>();
    private String vehecle;
    Scanner input = new Scanner(System.in);

    public DeliveryPerson() {
        super("Null", "Null", new Address());
    }
    public DeliveryPerson(String name, String phoneNumber, String vehecle, Address address) {
        super(name, phoneNumber, address);
        this.vehecle = vehecle;
    } 
    public void setVehecle(String vehecle) {
        this.vehecle = vehecle;
    }
    public void pick(Order order) {
        pickList.add(order);
    }
    public void showPickList() {
        if (pickList.size() < 1) System.out.println("No orders have been selected yet");
        else
            for (int i = 0; i < pickList.size(); i++) {
                System.out.println("==============================");
                System.out.println(i+1 + ".");
                    Order o = pickList.get(i);
                    System.out.println("------------------------------");
                    System.out.println("Customer's information: ");
                    o.getCustomer().viewInf();
                    System.out.println("------------------------------");
                    System.out.println("Food items information: ");
                    o.showFoodList();
                System.out.println("===========================");
            }
    }
    @Override
    public void viewInf() {
        super.viewInf();
        System.out.println("Vehicle: " + vehecle);
    }
}