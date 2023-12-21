package org.example.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChromeDriver {
    private static final WebDriver instance;
    private static final WebDriverWait waitInstance;

    static {
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
        instance = new org.openqa.selenium.chrome.ChromeDriver();
        waitInstance = new WebDriverWait(instance, 10);
    }

    public static WebDriver getInstance(){
        instance.manage().window().maximize();
        return instance;
    }

    public static WebDriverWait getWaitInstance(){
        return waitInstance;
    }


}
