package classes;

import java.io.Serializable;

public class Account implements Serializable {
    private String city;
    private String phoneNumber;
    private String first_name;
    private String last_name;

    public Account() {
    }

    public Account(String city, String phoneNumber, String first_name, String last_name) {
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public String toString() {
        return "Account{" +
                "city='" + city + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last name='" + last_name + '\'' +
                '}';
    }
}