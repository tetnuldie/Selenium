package org.example;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/features",
        glue = "org.example.steps",
//        tags = "@yourTag",
        plugin = {"pretty"}
)
public class CucumberRunner extends AbstractTestNGCucumberTests {

}
