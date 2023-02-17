package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.appmanager.SoapHelper;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

import static org.openqa.selenium.remote.BrowserType.CHROME;

public class TestBase {
    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", CHROME));

    SoapHelper soapHelper = new SoapHelper(app);


    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException {
        app.init();
        //app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        //app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();
    }

    public boolean isIssueOpen(int issueId) throws RemoteException, MalformedURLException, ServiceException {
        MantisConnectPortType mc = soapHelper.getMantisConnect();
        IssueData issue = mc.mc_issue_get(app.getProperty("MantisUsername"), app.getProperty("MantisPassword"), BigInteger.valueOf(issueId));
        return !issue.getStatus().getName().equals("resolved");
    }

    public void skipIfNotFixed(int issueId) throws RemoteException, MalformedURLException, ServiceException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
