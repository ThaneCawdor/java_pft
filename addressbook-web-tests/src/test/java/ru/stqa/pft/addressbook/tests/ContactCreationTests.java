package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        app.getContactHelper().goToContactPage();
        app.getContactHelper().createContact(new ContactData("Max", "Payne", "Brooklyn", "+17184848122", "www.valorservice.com", "test1"), true);
    }
}
