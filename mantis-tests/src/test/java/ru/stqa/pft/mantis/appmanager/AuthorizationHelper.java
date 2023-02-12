package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

import java.io.IOException;

public class AuthorizationHelper extends HelperBase {


    public AuthorizationHelper(ApplicationManager app) throws IOException {
        super(app);
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("local.properties"));
    }

    public void authorizationAsAdmin() throws IOException {
        wd.get( app.getProperty("web.baseUrl1") + "/login_page.php");
        type(By.name("username"), "administrator");
        click(By.xpath("//input[@type='submit']"));
        type(By.name("password"), "root");
        click(By.xpath("//input[@type='submit']"));
    }

    public void initChangePassword(int id) {
        wd.get(app.getProperty("web.baseUrl1") +"/account_page.php");
        click(By.xpath("//a[@href='/" + app.getProperty("MantisVersion") + "/manage_overview_page.php']"));
        click(By.xpath("//a[@href='/" + app.getProperty("MantisVersion") + "/manage_user_page.php']"));
        click(By.xpath("//a[@href='manage_user_edit_page.php?user_id=" + id + "']"));
        click(By.xpath("//input[@value='Сбросить пароль']"));
    }
}
