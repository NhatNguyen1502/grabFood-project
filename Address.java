package GrabFood;

public class Address {
    private String street;
    private String city;
    private String district;

    public Address() {
        this.street = "Null";
        this.city = "Null";
        this.district = "Null";
    }

    public Address(String street, String district, String city) {
        this.street = street;
        this.city = city;
        this.district = district;
    }
    
    public void modifyInf(String city, String district, String street) {
        this.district = district;
        this.city = city;
        this.street = street;
    }
    
    @Override
    public String toString() {
        return (street + ", " + district + ", " + city );
    }
}
