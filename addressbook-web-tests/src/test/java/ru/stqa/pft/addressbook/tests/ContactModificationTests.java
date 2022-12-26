package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getContactHelper().goToContactPage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Max", "Payne", "Brooklyn", "+17184848122", "www.valorservice.com", "test1"), false);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactFrom(new ContactData("Max", "Payne", "Brooklyn", "+17184848122", "www.valorservice.com", null), false);
        app.getContactHelper().submitContactModification();
    }
}
