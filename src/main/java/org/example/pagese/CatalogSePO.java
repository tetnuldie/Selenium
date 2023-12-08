package org.example.pagese;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Dimension;

import static com.codeborne.selenide.Selenide.*;

public class CatalogSePO {
    public SelenideElement getElectronics() {
        return $x("//li[@data-id=\"1\"]");
    }

    public SelenideElement getComputers() {
        return $x("//li[@data-id=\"2\"]");
    }

    public SelenideElement getAgd() {
        return $x("//li[@data-id=\"3\"]");
    }

    public SelenideElement getBuilding() {
        return $x("//li[@data-id=\"4\"]");
    }

    public SelenideElement getHousehold() {
        return $x("//li[@data-id=\"5\"]");
    }

    public SelenideElement getAuto() {
        return $x("//li[@data-id=\"6\"]");
    }

    public SelenideElement getBeauty() {
        return $x("//li[@data-id=\"7\"]");
    }

    public SelenideElement getChildren() {
        return $x("//li[@data-id=\"8\"]");
    }

    public SelenideElement getEveryday() {
        return $x("//li[@data-id=\"9\"]");
    }

    public SelenideElement getPrime() {
        return $x("//li[@data-id=\"12\"]");
    }

    public void openPage(){
        open("https://catalog.onliner.by/");
 //       webdriver().driver().getWebDriver().manage().window().maximize();
        webdriver().driver().getWebDriver().manage().window().setSize(new Dimension(436, 913));

    }

    public ElementsCollection getElementMenuList(SelenideElement element){
        return $$x(String.format("//div[@data-id=\"%s\"]//div[@class=\"catalog-navigation-list__aside-item\"]", element.getAttribute("data-id")));
    }


}
