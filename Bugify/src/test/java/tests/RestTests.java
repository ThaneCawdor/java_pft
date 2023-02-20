package tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import model.IssueData;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase {

    @Test
    public void testCreateIssue() throws IOException {
        skipIfNotFixed(105);
        Set<IssueData> oldIssueData = getIssues();
        IssueData newIssue = new IssueData().withSubject("Test issueV").withDescription("New test issueV");
        int issueId = createIssue(newIssue);
        Set<IssueData> newIssueData = getIssues();
        oldIssueData.add(newIssue.withId(issueId));
        assertEquals(newIssueData, oldIssueData);
    }

    private Set<IssueData> getIssues() throws IOException {
        String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json?limit=500"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<IssueData>>() {
        }.getType());
    }

    private int createIssue(IssueData newIssueData) throws IOException {
        String json = getExecutor().execute(Request.Post("https://bugify.stqa.ru/api/issues.json")
                        .bodyForm(new BasicNameValuePair("subject", newIssueData.getSubject()),
                                new BasicNameValuePair("description", newIssueData.getDescription())))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }
}
