package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().contactPage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstName("Max").withLastName("Payne").withAddress("Brooklyn").withPhones("+17184848122").withEmail("www.valorservice.com"));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.contact().all();
        ContactData modifyContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifyContact.getId()).withFirstName("Max").withLastName("Payne").withAddress("Brooklyn").withPhones("+17184848122").withEmail("www.valorservice.com");
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withOut(modifyContact).withAdded(contact)));
    }
}
