package org.example.pagese;

import com.codeborne.selenide.*;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

public class CatalogSePO {
    private Map<String, String> menuElementsMap;
    public CatalogSePO(){

        menuElementsMap = new HashMap<>();
        menuElementsMap.put("Электроника", "1");
        menuElementsMap.put("Компьютеры и сети", "2");
        menuElementsMap.put("Бытовая техника", "3");
        menuElementsMap.put("Стройка и ремонт", "4");
        menuElementsMap.put("Дом и сад", "5");
        menuElementsMap.put("Авто и мото", "6");
        menuElementsMap.put("Красота и спорт", "7");
        menuElementsMap.put("Детям и мамам", "8");
        menuElementsMap.put("Everyday", "9");
        menuElementsMap.put("Prime", "12");
    }

    public void openPage(){
        open("https://catalog.onliner.by/");
        webdriver().driver().getWebDriver().manage().window().maximize();
 //       webdriver().driver().getWebDriver().manage().window().setSize(new Dimension(436, 913));

    }

    public SelenideElement getMenuElement(String name) {
        return $x(String.format("//li[@data-id='%s']", menuElementsMap.get(name))).shouldBe(Condition.visible);
    }

    public ElementsCollection getElementMenuList(SelenideElement element){
        return $$x(String.format("//div[@data-id=\"%s\"]//div[@class=\"catalog-navigation-list__aside-item\"]", element.getAttribute("data-id")));
    }

    public SelenideElement getElementElementMenuItemByName(SelenideElement element, String name){
        return getElementMenuList(element)
                .filterBy(Condition.text(name))
                .get(0).shouldBe(Condition.visible);
    }

    public ElementsCollection getElementCategoryItems(){
        return $$x("//div[@class=\"catalog-navigation-list__aside-item catalog-navigation-list__aside-item_active\"]//a/span");
    }

    public SelenideElement getCategoryItemName(SelenideElement element){
        return element.$x("./span[@class=\"catalog-navigation-list__dropdown-title\"]").shouldBe(Condition.visible);
    }

    public SelenideElement getCategoryItemDescription(SelenideElement element){
        return element.$x("./span[@class=\"catalog-navigation-list__dropdown-description\"]").shouldBe(Condition.visible);
    }


}
