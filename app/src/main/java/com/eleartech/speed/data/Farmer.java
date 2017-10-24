package com.eleartech.speed.data;

/**
 * Created by junverarcayna on 22/10/2017.
 */

public class Farmer {

    public Profile profile;
    public Address address;
    public Land land;

    public Farmer() {}

    public Farmer(Profile profile, Address address, Land land) {
        this.profile = profile;
        this.address = address;
        this.land = land;
    }
}
