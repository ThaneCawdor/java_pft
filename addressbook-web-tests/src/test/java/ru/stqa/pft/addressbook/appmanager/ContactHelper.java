package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {
    public ContactHelper(ChromeDriver wd) {
        super(wd);
    }

    public void fillContactFrom(ContactData contactData) {
        type(By.name("firstname"),contactData.getFirstName());
        type(By.name("lastname"),contactData.getLastName());
        type(By.name("address"),contactData.getAddress());
        type(By.name("mobile"),contactData.getPhones());
        type(By.name("email"),contactData.getEmail());
    }

    public void initContactCreation() {
        click(By.name("add new"));
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }
}
