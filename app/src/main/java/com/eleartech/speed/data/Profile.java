package com.eleartech.speed.data;

/**
 * Created by junverarcayna on 21/10/2017.
 */

public class Profile {

    public String firstName;
    public String middleName;
    public String lastName;
    public String suffixName;
    public String gender;
    public String birthday;
    public String contactNumber;

    public Profile() {}

    public Profile(String firstName, String middleName, String lastName, String suffixName, String gender, String birthday, String contactNumber) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.suffixName = suffixName;
        this.gender = gender;
        this.birthday = birthday;
        this.contactNumber = contactNumber;
    }

}
