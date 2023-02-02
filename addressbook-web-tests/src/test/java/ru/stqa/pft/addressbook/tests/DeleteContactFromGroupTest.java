package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromGroupTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstName("Max").withLastName("Payne")
                    .withAddress("Brooklyn").withMobilePhone("+17184848122").withEmail1("www.valorservice.com"));
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test 1"));
        }
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        if (contactWithGroup(contacts) == null) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstName("Max")
                    .withLastName("Payne").inGroup(groups.iterator().next()));
        }
    }

    @Test
    public void testDel() {
        Groups before = app.db().groups();
        GroupData groupBefore = groupWithContact(before);

        Contacts contacts = app.db().contacts();
        ContactData contact = contactWithGroup(contacts);

        app.goTo().contactPage();
        app.contact().selectListGroup(groupBefore.getName());
        app.contact().selectContactById(contact.getId());
        app.contact().removeContact();
        app.goTo().contactPage();

        Groups after = app.db().groups();
        GroupData groupAfter = after.iterator().next().withName(groupBefore.getName());

        Assert.assertFalse(groupAfter.getContacts().contains(contact));
        assertThat(groupAfter.getContacts().size(), equalTo(groupBefore.getContacts().size() - 1));
    }

    public GroupData groupWithContact(Groups group) {
        Groups groups = app.db().groups();
        List<GroupData> groupsF = groups.stream().filter(g -> g.getContacts().size() != 0).toList();
        if (groupsF.isEmpty()) {
            return null;
        }
        return groupsF.iterator().next();
    }

    public ContactData contactWithGroup(Contacts contact) {
        Contacts contacts = app.db().contacts();
        List<ContactData> contactsF = contacts.stream().filter(c -> c.getGroups().size() != 0).toList();
        if (contactsF.isEmpty()) {
            return null;
        }
        return contactsF.iterator().next();
    }
}
