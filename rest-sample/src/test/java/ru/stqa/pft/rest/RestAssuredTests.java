package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static com.jayway.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class RestAssuredTests {

    @BeforeClass
    public void init() {
        RestAssured.authentication = RestAssured.basic("7172fcb5f1888f5fac3dced24caeaa6a", "");
    }

    @Test
    public void testCreateIssue() throws IOException {
        Set<IssueData> oldIssue = getIssues();
        IssueData newIssueData = new IssueData().withSubject("Test issueV").withDescription("New test issueV");
        int issueId = createIssue(newIssueData);
        Set<IssueData> newIssue = getIssues();
        //oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssue, oldIssue);
    }

    private Set<IssueData> getIssues() throws IOException {
        String json = RestAssured.get("https://bugify.stqa.ru/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<IssueData>>() {
        }.getType());
    }

    private int createIssue(IssueData newIssueData) throws IOException {
        String json = given()
                .parameter("subject", newIssueData.getSubject())
                .parameter("description", newIssueData.getDescription())
                .post("https://bugify.stqa.ru/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }
}
