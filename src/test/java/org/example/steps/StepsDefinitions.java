package org.example.steps;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pagese.MainPagePO;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.webdriver;

public class StepsDefinitions {

    MainPagePO page = new MainPagePO();

    @BeforeAll
    public static void beforeAll(){
        Configuration.startMaximized = true;
    }

    @Given("main page is opened")
    public void maiPageIsOpened(){
        page.openPage();
    }

    @When("user move mouse to the {string} menu item")
    public void hoverOverMenuItem(String name){
        page.getHeadMenuElementsWithExpandByName(name).hover();
    }

    @Then("sub menu {string} is opened")
    public void subMenuIsPresent(String name){
       Assert.assertTrue(page.getDropdownSection(name).isDisplayed(), "Section "+name+" not found");
    }

    @And("section {string} is shown as child of {string} sub menu")
    public void sectionItemByNameIsShown(String itemName, String parentName){
        Assert.assertTrue(page.getSectionItemByName(parentName, itemName).isDisplayed(), "Section Item "+itemName+" not found");
    }

    @And("dependent sections of {string} menu are shown")
    public void subMenuCategoryColumnIsShown(String name){
        SoftAssert softAssert = new SoftAssert();
        page.getSectionNavColumns(name).forEach(element -> {
            softAssert.assertTrue(element.isDisplayed(), "Element "+element.getSearchCriteria()+" Not located");
            softAssert.assertNotNull(element.getText(), "Element "+element.getSearchCriteria()+" Text is missing");
        });

        softAssert.assertAll();
    }

    @AfterAll
    public static void afterAll(){
        webdriver().driver().getWebDriver().quit();
    }
}
