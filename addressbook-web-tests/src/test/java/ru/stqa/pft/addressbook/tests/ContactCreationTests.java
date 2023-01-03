package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        app.getNavigationHelper().goToContactPage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactFrom(new ContactData("Max", "Payne", "Brooklyn", "+17184848122", "www.valorservice.com", null), true);
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToContactPage();
        app.getNavigationHelper().goToContactPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),before.size() + 1);
    }
}
