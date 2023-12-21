package org.example.pagese;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class MainPagePO {

    static {
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
    }

    public SelenideElement getHeadMenuElementsWithExpandByName(String name) {
        return $$x("//li[@class='b-main-navigation__item b-main-navigation__item_arrow']")
                .filterBy(Condition.text(name))
                .first()
                .shouldBe(Condition.visible);
    }

    public SelenideElement getDropdownSection(String name){
        return $$x("//div[contains(@class, 'b-main-navigation__dropdown-column_')]")
                .filterBy(Condition.text(name))
                .first()
                .shouldBe(Condition.visible);
    }

    public ElementsCollection getSectionNavColumns(String sectionName){
        return getDropdownSection(sectionName)
                .$$x(".//div[@class='b-main-navigation__dropdown-column']");
    }


    public SelenideElement getSectionItemByName(String sectionName, String name){
        return getDropdownSection(sectionName)
                .$$x(".//span")
                .filterBy(Condition.text(name))
                .first()
                .shouldBe(Condition.visible);
    }

    public void openPage(){
        open("https://catalog.onliner.by/");
    }
}
