package com.combktests.petstore;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.RestAssured;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class BaseTest {
    public static final String BASE_URL = "https://petstore.swagger.io/v2/";
    public static final String API_KEY = "special-key";
    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeSuite
    public void setup() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.basePath = "";
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.given().header("api_key", API_KEY);
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("target/petApiTests.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }


    @BeforeMethod
    public void beforeMethod(Method method) {
        test = extent.createTest(method.getName());
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test passed");
        } else {
            test.skip("Test skipped");
        }
    }

    @AfterSuite
    public void afterSuite() {
        extent.flush();
    }
}