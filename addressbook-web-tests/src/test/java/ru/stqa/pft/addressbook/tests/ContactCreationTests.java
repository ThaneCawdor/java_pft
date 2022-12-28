package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        app.getNavigationHelper().goToContactPage();
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactFrom(new ContactData("Max", "Payne", "Brooklyn", "+17184848122", "www.valorservice.com", null), true);
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToContactPage();
    }
}
