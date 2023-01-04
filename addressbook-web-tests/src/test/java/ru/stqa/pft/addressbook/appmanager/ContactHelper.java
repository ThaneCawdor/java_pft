package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactFrom(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getPhones());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            try {
                new Select(wd.findElement(By.name("new_group"))).selectByIndex(1);
            } catch (NoSuchElementException ex) {
                System.out.println("Нет групп для выбора");
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void fillContactFrom(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getPhones());
        type(By.name("email"), contactData.getEmail());

        if (isElementPresent(By.name("new_group"))) {
            try {
                new Select(wd.findElement(By.name("new_group"))).selectByIndex(1);
            } catch (NoSuchElementException ex) {
                System.out.println("Нет групп для выбора");
            }
        }
    }

    public void initContactCreation() {
        click(By.xpath("//li/a[@href='edit.php']"));
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void initContactModification() {
        click(By.xpath("//td/a/img[@title='Edit']"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void returnToContactPage() {
        if (isElementPresent(By.xpath("xpath=//input[@name='searchstring']"))) {
            return;
        }
        click(By.linkText("home"));
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactFrom(contact);
        submitContactCreation();
        returnToContactPage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {
            String firstName = element.findElement(By.xpath("td[3]")).getText();
            String lastName = element.findElement(By.xpath("td[2]")).getText();
            String address = element.findElement(By.xpath("td[4]")).getText();
            String phones = element.findElement(By.xpath("td[6]")).getText();
            String email = element.findElement(By.xpath("td[5]")).getText();
            int id = Integer.parseInt(element.findElement(By.xpath("td/input")).getAttribute("value"));
            ContactData contact = new ContactData(id, firstName, lastName, address, phones, email, null);
            contacts.add(contact);
        }
        return contacts;
    }
}
