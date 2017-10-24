package com.eleartech.speed.data;

/**
 * Created by junverarcayna on 21/10/2017.
 */

public class Address {

    public String street;
    public String barangay;
    public String city;
    public String province;

    public Address() {}

    public Address(String street, String barangay, String city, String province) {
        this.street = street;
        this.barangay = barangay;
        this.city = city;
        this.province = province;
    }
}
