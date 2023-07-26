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

public class AddContactInGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstName("Max").withLastName("Payne")
                    .withAddress("Brooklyn").withMobilePhone("+17184848122").withEmail1("www.valorservice.com"));
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
        Groups groups = app.db().groups();
        if (groupWithoutContact(groups) == null) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("testForAdd"));
        }
    }

    @Test(enabled = false)
    public void testAdd() {
        Contacts before = app.db().contacts();
        ContactData contactBefore = before.iterator().next();

        Groups groups = app.db().groups();
        GroupData group = groupWithoutContact(groups);

        app.goTo().contactPage();
        app.contact().selectContactById(contactBefore.getId());
        app.contact().selectGroupByName(group.getName());
        app.contact().addInGroup();
        app.goTo().contactPage();

        ContactData contactAfter = app.db().contacts().iterator().next().withId(contactBefore.getId());

        Assert.assertTrue(contactAfter.getGroups().contains(group));
        assertThat(contactAfter.getGroups().size(), equalTo(contactBefore.getGroups().size() + 1));
    }

    public GroupData groupWithoutContact(Groups group) {
        Groups groups = app.db().groups();
        List<GroupData> groupsF = groups.stream().filter(g -> g.getContacts().size() == 0).toList();
        if (groupsF.isEmpty()) {
            return null;
        }
        return groupsF.iterator().next();
    }
}
