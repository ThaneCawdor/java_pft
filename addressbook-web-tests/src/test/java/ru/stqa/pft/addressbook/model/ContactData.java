package ru.stqa.pft.addressbook.model;

public class ContactData {
    String firstName;
    String lastName;
    String address;
    String phones;
    String email;

    public ContactData(String firstName, String lastName, String address, String phones, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phones = phones;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhones() {
        return phones;
    }

    public String getEmail() {
        return email;
    }
}


