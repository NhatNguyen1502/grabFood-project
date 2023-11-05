package GrabFood;

public abstract class Person {
    protected String name;
    protected String phoneNumber;
    protected Address address;

    public Person() {}
    public Person(String name, String phoneNumber, Address address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void viewInf() {
        System.out.println("Name: " + name + "\nPhone number: " + phoneNumber
                            + "\nAddress: " + address.toString());
    }
    @Override
    public String toString() {
        return "Name: " + name + "\nPhone number: " + phoneNumber
                + "\nAddress: " + address.toString();
    }
}
