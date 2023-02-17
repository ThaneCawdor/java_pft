package tests;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;

public class TestBase {


    public void readProperty() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("local.properties"));
    }

    protected Executor getExecutor() {
        return Executor.newInstance().auth("7172fcb5f1888f5fac3dced24caeaa6a", "");
    }

    public boolean isIssueOpen(int issueId) throws IOException {
        String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues/" + issueId + ".json"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement myIssue = parsed.getAsJsonObject().get("issues");
        return !(myIssue.toString().contains("Closed") || myIssue.toString().contains("Resolved"));
    }

    public void skipIfNotFixed(int issueId) throws IOException {

        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
