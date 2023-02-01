package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> validContactsFromXml() throws IOException {
        //File photo = new File("src/test/resources/max.jpg");
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.xml"))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xStream = new XStream();
            xStream.processAnnotations(ContactData.class);
            xStream.allowTypes(new Class[]{ContactData.class});
            List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
            return contacts.stream().map(c -> new Object[]{c}).toList().iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.json"))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType());
            return contacts.stream().map(c -> new Object[]{c}).toList().iterator();
        }
    }

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test 1"));
        }
    }

    @Test(dataProvider = "validContactsFromJson")
    public void testContactCreation(ContactData contact) {
        Groups groups = app.db().groups();
        app.goTo().contactPage();
        Contacts before = app.db().contacts();
        app.contact().create(contact.inGroup(groups.iterator().next()));
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt(c -> c.getId()).max().getAsInt()))));
        verifyContactListInUi();
    }

    @Test
    public void testBadContactCreation() {
        Groups groups = app.db().groups();
        app.goTo().contactPage();
        Contacts before = app.db().contacts();
        ContactData contact = new ContactData().withFirstName("Max'").withLastName("Payne").withAddress("Brooklyn").withMobilePhone("+17184848122").withEmail1("www.valorservice.com");
        app.contact().create(contact.inGroup(groups.iterator().next()));
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before));
    }
}
