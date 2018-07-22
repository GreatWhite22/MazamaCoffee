package com.mazamacoffee.www.mazamacoffeeco;

/**
 * Created by conno on 12/31/2017.
 * TEST!!! DELETE THIS WHEN DONE
 */

//package com.amazonaws.demo.lambdainvoker;

/**
 * A simple POJO
 */
public class NameInfo {
    private String firstName;
    private String lastName;

    public NameInfo() {}

    public NameInfo(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}