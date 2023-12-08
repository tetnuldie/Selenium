package org.example.page;

import org.example.driver.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CatalogPO {
    private final String url = "https://catalog.onliner.by/";
    private final WebDriver driver = ChromeDriver.getInstance();
    private final WebDriverWait driverWait = ChromeDriver.getWaitInstance();
    @FindBy(xpath = "//li[@data-id=\"1\"]")
    private WebElement electronics;
    @FindBy(xpath = "//li[@data-id=\"2\"]")
    private WebElement computers;
    @FindBy(xpath = "//li[@data-id=\"3\"]")
    private WebElement agd;
    @FindBy(xpath = "//li[@data-id=\"4\"]")
    private WebElement building;
    @FindBy(xpath = "//li[@data-id=\"5\"]")
    private WebElement household;
    @FindBy(xpath = "//li[@data-id=\"6\"]")
    private WebElement auto;
    @FindBy(xpath = "//li[@data-id=\"7\"]")
    private WebElement beauty;
    @FindBy(xpath = "//li[@data-id=\"8\"]")
    private WebElement children;
    @FindBy(xpath = "//li[@data-id=\"9\"]")
    private WebElement everyday;
    @FindBy(xpath = "//li[@data-id=\"12\"]")
    private WebElement prime;

    public CatalogPO(){
        PageFactory.initElements(ChromeDriver.getInstance(), this);
    }

    public String getUrl() {
        return url;
    }

    public void openPage(){
        driver.get(url);
        driver.manage().window().maximize();
    }

    public WebElement getElectronics() {
        return electronics;
    }

    public WebElement getComputers() {
        return computers;
    }

    public WebElement getAgd() {
        return agd;
    }

    public WebElement getBuilding() {
        return building;
    }

    public WebElement getHousehold() {
        return household;
    }

    public WebElement getAuto() {
        return auto;
    }

    public WebElement getBeauty() {
        return beauty;
    }

    public WebElement getChildren() {
        return children;
    }

    public WebElement getEveryday() {
        return everyday;
    }

    public WebElement getPrime() {
        return prime;
    }

    public void passScenario(WebElement element){
        driverWait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();

        driver.findElements(By.xpath(String.format("//div[@data-id=\"%s\"]//div[@class=\"catalog-navigation-list__aside-item\"]", element.getAttribute("data-id")))
        ).stream().forEach(webElement -> {
            driverWait.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            driver.findElements(By.xpath("//div[@class=\"catalog-navigation-list__aside-item catalog-navigation-list__aside-item_active\"]//a/span/span[@class!=\"catalog-navigation-list__dropdown-preview\"]"))
                    .stream().forEach(webElement1 -> {
                        System.out.println(webElement1.getText());

                    });
        });
        element.click();
    }

}
