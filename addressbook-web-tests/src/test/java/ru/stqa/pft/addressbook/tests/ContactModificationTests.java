package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToContactPage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Max", "Payne", "Brooklyn", "+17184848122", "www.valorservice.com", null));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(0);
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactFrom(new ContactData("Max", "Payne", "Brooklyn", "+17184848122", "www.valorservice.com", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToContactPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
    }
}
