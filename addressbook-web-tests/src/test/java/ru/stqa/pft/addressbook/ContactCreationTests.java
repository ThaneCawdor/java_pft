package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        initContactCreation();
        fillContactFrom(new ContactData("Max", "Payne", "Brooklyn", "+17184848122", "www.valorservice.com"));
        submitContactCreation();
        returnToContactPage();
    }
}
