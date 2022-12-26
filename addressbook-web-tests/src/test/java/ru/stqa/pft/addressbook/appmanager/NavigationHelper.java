package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {
    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void closeAlert() {
        wd.switchTo().alert().accept();
    }
}
