package org.example.catalog;

import com.codeborne.selenide.testng.SoftAsserts;
import org.testng.ITestResult;

public class OnlinerCatalogListener extends SoftAsserts {
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getName());
        System.out.println("Params:");
        Object[] params = result.getParameters();
        for (Object o : params) {
            System.out.println(o.toString());
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getName());
        System.out.println("Params:");
        Object[] params = result.getParameters();
        for (Object o : params) {
            System.out.println(o.toString());
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Starting: "+result.getName());

    }
}
