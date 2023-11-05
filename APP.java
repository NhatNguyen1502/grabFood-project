package GrabFood;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class APP {
    static Scanner input = new Scanner(System.in);
    static String absolutePath;
    static ArrayList<String> listAcc = new ArrayList<String>();
    static int pick, pick1;

    public static void main(String[] args) {
        
        availableRestaurant();

        do {
            getAccsFromFile();
            clean();
            System.out.println("_______________WELCOME TO GRAB FOOD_______________ ");
            System.out.println("\n1. Log in \n2. Sign up \n0. Exit");
                pick = choose(2);
                switch (pick) {
                    case 1:
                        clean();
                        logIn();
                        run();
                        break;
                    case 2:
                        clean();
                        signUp();
                        run();
                        break;
                }
        } while (pick != 0);
    }
    static void run() {
        do {
            clean();
            Restaurant restaurant = new Restaurant();
            Customer customer = new Customer();
            DeliveryPerson delivery = new DeliveryPerson();
            System.out.println("_______________CHOOSE ROLE_______________");
            System.out.println("\n1. Customer \n2. Restaurant \n3. Delivery \n0. Log out ");
            pick1 = choose(3);
            switch (pick1) {
                case 1:
                    customerMethod(customer);
                    break;
                case 2:
                    
                    restaurantMethod(restaurant);
                    break;
                case 3:
                    deliveryMethod(delivery);
            }
        } while (pick1 != 0);
    }
    static void customerMethod(Customer customer) {
        int pick, pick1, pick2;
        do{
            clean();
            System.out.println("_______________CUSTOMER_______________");
            System.out.println("\n1. View restaurant list\n2. View food list\n3. View my information\n4. View cart \n5. View ordered list \n0. Back");
            pick = choose(5);
                switch (pick) {
                    case 1:
                        do{
                            clean();
                            Restaurant.showResList();
                            System.out.println("------------------------------");
                            System.out.println("(PICK ID OF RESTAURANT TO VIEW DETAIL INFORMAION)");
                            System.out.println("0. Back ");
                            pick1 = choose(Restaurant.amountOfRes());
                            if(pick1 != 0){
                                do{
                                    Restaurant res = Restaurant.getRestaurant(pick1-1);
                                    clean();
                                    System.out.println("_______________" + res.getName().toUpperCase() + " RESTAURANT______________");
                                    res.viewInf();
                                    System.out.println("------------------------------");
                                    System.out.println("MENU");
                                    res.showFoodList();
                                    System.out.println("------------------------------");
                                    System.out.println("(PICK ID OF RESTAURANT TO VIEW DETAIL FOOD)");
                                    System.out.println("0. Back ");
                                    pick2 = choose(res.amountOfFood());
                                    if (pick2 != 0) {
                                        int pick3;
                                        do{
                                            clean();
                                            FoodItem food = res.getFoodItem(pick2-1);
                                            System.out.printf("______________THE DETAILS OF %s_____________ \n",food.getName().toUpperCase());
                                            System.out.println(food);
                                            System.out.println("------------------------------");
                                            System.out.println("1. Add this food into your cart\n0. Back");
                                            pick3 = choose(1);
                                            if (pick3 == 1){
                                                customer.pick(food);
                                                clean();
                                                System.out.println("Successfull added into cart!");
                                                try {
                                                    Thread.sleep(1000);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                clean();
                                            }
                                        } while (pick3 != 0);
                                    }
                                } while (pick2 != 0);
                            }
                        } while (pick1 != 0);
                        break;
                    case 2:
                        do{
                            clean();
                            FoodItem.showFoodList();
                            System.out.println("0. Back ");
                            pick1 = choose(FoodItem.amountOfFood());
                            if(pick1 != 0) {
                                FoodItem food = FoodItem.getFood(pick1-1);
                                do{
                                    clean();
                                    System.out.printf("______________THE DETAILS OF %s_____________ \n",food.getName().toUpperCase());
                                    System.out.println(food);
                                    System.out.println("------------------------------");
                                    System.out.println("1. Add this food into your cart\n0. Back");
                                    pick2 = choose(1);
                                    if (pick2 == 1){
                                        customer.pick(food);
                                        clean();
                                        System.out.println("Successfull added into cart!");
                                        try {
                                            Thread.sleep(1000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        clean();
                                    }
                                } while (pick2 != 0);
                            }
                        } while (pick1 != 0);
                        break;
                    case 3:
                        do{
                            clean();
                            System.out.println("_______________MY INFORMATION_______________");
                            System.out.println("------------------------------");
                            customer.viewInf();
                            System.out.println("------------------------------");
                            System.out.println("\n1. Modify name \n2. Modify number phone \n3. Modify address \n0. Back");
                            pick1 = choose(3);
                            modify(customer, pick1);
                        } while (pick1 != 0);
                        break;
                    case 4:
                        int amountItem;
                        do {
                            clean();
                            System.out.println("______________YOUR CART_____________");
                            amountItem = customer.getAmountFooditem();
                            if (amountItem == 0) {
                                System.out.println("Your cart is empty!");
                            } else {
                                customer.viewCart();
                                System.out.println("------------------------------");
                                System.out.println(amountItem+1 + ". ORDER");
                                System.out.println("(PICK ID OF ITEM WHAT YOU WANT TO REMOVE)");
                            }
                            System.out.println("------------------------------");
                            System.out.println("0. Back ");
                            pick1 = choose(amountItem +1);
                            if (pick1 != 0) {
                                if (pick1 != amountItem+1) customer.removeFoodItem(pick1-1);
                                else {
                                    clean();
                                    System.out.println("______________BILL_____________");
                                    customer.viewCart();
                                    System.out.println("SUCCESFULL ORDER!");
                                    customer.order();
                                    try {
                                        Thread.sleep(3000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    clean();
                                    break;
                                }
                            }
                        } while (pick1 != 0);
                        break;
                    case 5:
                        clean();
                        System.out.println("______________ORDERED LIST_____________");
                        customer.showOrderedList();
                        System.out.println("Enter to Back");
                        input.nextLine();input.nextLine();
                        break;    
                }
        } while (pick != 0);
    }

    static void restaurantMethod(Restaurant restaurant) {
        int pick, pick1;
        do{
            clean();
            System.out.println("_______________RESTAURANT_______________");
            System.out.println("\n1. View my restaurant information \n2. View menu \n3. Check orders \n0. Back");
            pick = choose(3);
                switch (pick) {
                    case 1:
                        do{
                            clean();
                            System.out.println("_______________RESTAURANT INFORMATION_______________");
                            System.out.println("------------------------------");
                            restaurant.viewInf();
                            System.out.println("------------------------------");
                            System.out.println("\n1. Modify name \n2. Modify number phone \n3. Modify address \n0. Back");
                            pick1 = choose(3);
                            modify(restaurant, pick1);
                        } while (pick1 != 0);
                        break;
                    case 2:
                        do{
                            clean();
                            System.out.println("_______________MENU_______________");
                            restaurant.showFoodList();
                            System.out.println("------------------------------");
                            int amount = restaurant.amountOfFood();
                            System.out.println(amount+1 +". Add food item\n" + (amount+2) +". Remove food item");
                            System.out.println("(CHOOSE ID OF FOOD ITEM TO MODIFY)");
                            System.out.println("0. Back");
                            pick1 = choose(amount + 2);
                            if (pick1 != 0) {
                                if ( pick1 == amount+1){
                                    clean();
                                    input.nextLine();
                                    System.out.println("______________ADD FOOD ITEM______________");
                                    System.out.print("Enter name of food: ");
                                    String name = input.nextLine();
                                    System.out.print("Enter price of food: ");
                                    double price = input.nextDouble();
                                    restaurant.addFood(new FoodItem(name, price));
                                } else if (pick1 == amount+2){
                                    if (amount > 0){
                                        clean();
                                        System.out.println("______________REMOVE FOOD ITEM______________");
                                        restaurant.showFoodList();
                                        System.out.println("(ENTER IF OF FOOD WHAT YOU WANT TO REMOVE)");
                                        System.out.println("0. Back");
                                        int index = choose(amount);
                                        if (index !=0) restaurant.removeFoodItem(index-1);
                                    } else System.out.println("Menu is empty!");
                                }else {
                                    input.nextLine();
                                    FoodItem food = restaurant.getFoodItem(pick1-1);
                                    clean();
                                    System.out.println("_______________MODIFY FOOD ITEM_______________");
                                    System.out.printf("Name: %s \nPrice: %.3f VND \n",food.getName(), food.getPrice());
                                    System.out.print("Enter name of food: ");
                                    String name = input.nextLine();
                                    System.out.print("Enter price of food: ");
                                    double price = input.nextDouble();
                                    food.setName(name);
                                    food.setPrice(price);
                                    clean();
                                } 
                            }
                        } while (pick1 != 0);
                        break;
                    case 3:
                        clean();
                        input.nextLine();
                        System.out.println("_______________OREDER LIST_______________");
                        restaurant.showOrders();
                        System.out.println("Enter to Back");
                        input.nextLine();
                        break;
                    
                }
        } while (pick != 0);
    }

    
    static void deliveryMethod(DeliveryPerson delivery) {
        int pick, pick1;
        do{
            clean();
            System.out.println("_______________DELIVERY_______________");
            System.out.println("\n1. View my information \n2. View orders list \n3. View picked list \n0. Back");
            pick = choose(3);
                switch (pick) {
                    case 1:
                        do{
                            clean();
                            System.out.println("_______________MY INFORMATION_______________");
                            System.out.println("------------------------------");
                            delivery.viewInf();
                            System.out.println("------------------------------");
                            System.out.println("\n1. Modify name \n2. Modify number phone \n3. Modify address \n4. Modify vehicle\n0. Back");
                            pick1 = choose(4);
                            if (pick1 == 4) {
                                input.nextLine();
                                System.out.print("Enter vehicle: ");
                                String vehicle = input.nextLine();
                                delivery.setVehecle(vehicle);
                            } else modify(delivery, pick1);
                        } while (pick1 != 0);
                        break;
                    case 2:
                        do{
                            clean();
                            System.out.println("_______________VIEW ORDERS LIST_______________");
                            Order.showOrderList();
                            System.out.println("(Enter id of orders what you want to pick)");
                            System.out.println("0. Back");

                            pick1 = choose(Order.getId());
                            if (pick1 != 0) {
                                Order o = new Order(Order.getOrder(pick1-1));
                                delivery.pick(o);
                                Order.removeOrder(pick1-1);
                            }
                        } while (pick1 != 0);
                        break;
                    case 3:
                        clean();
                        System.out.println("_______________VIEW PICKED LIST_______________");
                        delivery.showPickList();
                        System.out.println("Enter to back");
                        input.nextLine();input.nextLine();
                }
        } while (pick != 0);
    }

    static void modify(Person x,int pick1) {
        switch (pick1) {
            case 1:
                input.nextLine();
                System.out.print("Enter your name: ");
                String name = input.nextLine();
                x.setName(name);
                break;
            case 2:
                input.nextLine();
                System.out.print("Enter your number phone: ");
                String phoneNumber = input.nextLine();
                x.setPhoneNumber(phoneNumber);
                break;
            case 3:
                input.nextLine();
                System.out.print("Enter city: ");
                String city = input.nextLine();
                System.out.print("Enter district: ");
                String district = input.nextLine();
                System.out.print("Enter street: ");
                String street = input.nextLine();
                x.setAddress(new Address(street, district, city));
                break;
        }
    }



    static int choose(int amount) {
        int choose = -190;
        while (choose > amount || choose < 0) {
            try {
                System.out.print("\nYour choose: ");
                choose = input.nextInt();
            } catch (Exception e) {
                System.out.println("Enter type integer, please!");
                choose = -190;
            } finally {
                if (choose == -190) input.nextLine(); // Đọc và xóa dòng nhập không hợp lệ
            }
            if (choose > amount || choose < 0) System.out.println("You have selected out of range! Re-enter, please!");
        }
        return choose;
    }

   
    static void getAccsFromFile() {

        //tạo hoặc lấy đường dẫn file 
        File currentDirectory = new File("GrabFood\\Account.txt");
        absolutePath = currentDirectory.getAbsolutePath();
        File myFile = new File(absolutePath);

        //Lấy danh sách account từ file
        try {
            //tạo file nếu file chưa tồn tại
            myFile.createNewFile();
            
            FileInputStream in = new FileInputStream(myFile);
            InputStreamReader in1 = new InputStreamReader(in, "UTF-8");
            BufferedReader file = new BufferedReader(in1);

            String x;
            while ((x = file.readLine()) != null ) {
                listAcc.add(x);
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Đăng nhập
    static void logIn() {
        int count = 0;
        if (input.hasNextLine()) input.nextLine();
        System.out.println("_______________LOG IN_______________");
        System.out.print("\nUser name: ");
        String userName = input.nextLine();
        while (checkAcc(userName) == -1) {
            count++;
            if (count == 3) {
                System.out.println("Enter incorrectly 3 times, automatically switch to the sign up item");
                System.out.println("Enter to continue");
                signUp();
                break;
            }
            System.out.println("Account does not exist! Re-enter!");
            System.out.print("User name: ");
            userName = input.nextLine();
        }
        if (count == 3) return;
        int indexPassword = checkAcc(userName) + 1;
        count = 0;
        System.out.print("Password: ");
        String password = input.nextLine();
        while (password.equals(listAcc.get(indexPassword)) == false) {
            count++;
            if (count == 3) {
                System.out.println("Enter incorrectly 3 times, automatically switch to the sign up item");
                System.out.println("Enter to continue");
                signUp();
                break;
            }
            System.out.println("Wrong password! Re-enter!");
            System.out.print("Password: ");
            password = input.nextLine();
        }       
    }

    // Đăng kí 
    static void signUp() {
        if (input.hasNextLine()) input.nextLine();
        clean();
        System.out.println("_______________SIGN UP_______________");
        File f = new File(absolutePath);
        try {
            FileWriter writer = new FileWriter(f,true);
            BufferedWriter filein = new BufferedWriter(writer);

            System.out.print("\nUser name: ");
            String userName = input.nextLine();
            while (checkAcc(userName) != -1) {
                System.out.println("This name account has already existed! Re-enter!");
                System.out.print("User name: ");
                userName = input.nextLine();
            }
            System.out.print("Password: ");
            String password = input.nextLine();
            filein.write(userName + "\n");
            filein.write(password + "\n");
            filein.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //kiểm tra tên tài khoản đã tồn tại chưa nế
    static int checkAcc(String userName) {
        for (int i = 0; i < listAcc.size(); i+=2) {
            if (userName.equalsIgnoreCase(listAcc.get(i))) return i;
        }
        return -1;
    }

    //Xóa sạch màn hình
    static void clean() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
    
    //dữ liệu cho sẵn 
    static void availableRestaurant() {
        Address address = new Address("101B Le Huu Trac","Da Nang" , "Da Nang");
        Restaurant[] lRes = new Restaurant[5];
        lRes[0] = new Restaurant("Sea food", "0369998877", address);
        lRes[1] = new Restaurant("Hong Kong", "0369543654", address);
        lRes[2] = new Restaurant("Panda food", "0369345321", address);
        lRes[3] = new Restaurant("ARG noodle", "0369573272", address);
        lRes[4] = new Restaurant("PL Pho", "0364572345", address);
        
        FoodItem[] lFoods = new FoodItem[15];
        lFoods[0] = new FoodItem("Shrimp", 150);
        lFoods[1] = new FoodItem("Crab balls", 150);
        lFoods[2] = new FoodItem("Crawfish", 150);
        lFoods[3] = new FoodItem("Clams", 150);
        lFoods[4] = new FoodItem("Chicken wings", 150);
        lFoods[5] = new FoodItem("Blu crab", 150);
        lFoods[6] = new FoodItem("Lemon chicken", 150);
        lFoods[7] = new FoodItem("King crab legs", 150);
        lFoods[8] = new FoodItem("Fried egg", 150);
        lFoods[9] = new FoodItem("Seafood soup", 150);
        lFoods[10] = new FoodItem("Chilli chicken", 150);
        lFoods[11] = new FoodItem("Kung po beef", 150);
        lFoods[12] = new FoodItem("Lemon chicken", 150);
        lFoods[13] = new FoodItem("Butter prawn", 150);
        lFoods[14] = new FoodItem("Lemon beef", 150);

        lRes[0].addFood(lFoods[0]);
        lRes[0].addFood(lFoods[1]);
        lRes[0].addFood(lFoods[2]);
        lRes[1].addFood(lFoods[3]);
        lRes[1].addFood(lFoods[4]);
        lRes[1].addFood(lFoods[5]);
        lRes[2].addFood(lFoods[6]);
        lRes[2].addFood(lFoods[7]);
        lRes[2].addFood(lFoods[8]);
        lRes[3].addFood(lFoods[9]);
        lRes[3].addFood(lFoods[10]);
        lRes[3].addFood(lFoods[11]);
        lRes[4].addFood(lFoods[12]);
        lRes[4].addFood(lFoods[13]);
        lRes[4].addFood(lFoods[14]);
    }
}