package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() {
        File photo = new File("src/test/resources/max.jpg");
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new ContactData().withFirstName("Max").withLastName("Payne").withAddress("Brooklyn")
                .withAllPhones("+17184848122").withAllEmail("www.valorservice.com").withPhoto(photo)});
        list.add(new Object[]{new ContactData().withFirstName("Max1").withLastName("Payne1").withAddress("Brooklyn1")
                .withAllPhones("+17184848122").withAllEmail("www.valorservice.com").withPhoto(photo)});
        list.add(new Object[]{new ContactData().withFirstName("Max2").withLastName("Payne2").withAddress("Brooklyn2")
                .withAllPhones("+17184848122").withAllEmail("www.valorservice.com").withPhoto(photo)});
        return list.iterator();
    }

    @Test(dataProvider = "validContacts")
    public void testContactCreation(ContactData contact) {
        app.goTo().contactPage();
        Contacts before = app.contact().all();
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt(c -> c.getId()).max().getAsInt()))));
    }

    @Test
    public void testBadContactCreation() {
        app.goTo().contactPage();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstName("Max'").withLastName("Payne").withAddress("Brooklyn").withAllPhones("+17184848122").withAllEmail("www.valorservice.com");
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }
}
