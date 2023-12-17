package org.example.catalog;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ex.ElementNotFound;
import org.example.OnlinerTvListener;
import org.example.pagese.CatalogSePO;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

@Listeners(OnlinerTvListener.class)
public class OnlinerCatalogTest {
    static {
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
    }

    private CatalogSePO page = new CatalogSePO();

    @Test(dataProvider = "elementsListProvider")
    public void allMenuElementPresent(List<String> elementNames) {
        page.openPage();
        SoftAssert softAssert = new SoftAssert();
        elementNames.forEach(element -> {
            try {
                softAssert.assertNotNull(page.getMenuElement(element));
            } catch (ElementNotFound e) {
                softAssert.fail("Element Not Fount by name " + element);
            }
        });

        softAssert.assertAll();
    }

    @Test(dataProvider = "categoryNameProvider")
    public void computersItemSideMenuPresent(String elementName, List<String> categoriesList) {
        SoftAssert softAssert = new SoftAssert();
        page.openPage();
        page.getMenuElement(elementName).click();

        categoriesList.forEach(element -> {
            softAssert.assertEquals(1, page.getElementMenuList(page.getMenuElement(elementName)).filterBy(Condition.text(element)).size(),
                    "Section - " + element + "is absent");
        });

        softAssert.assertAll();
    }

    @Test(dataProvider = "productCategoryNameProvider")
    public void computersProductListInfoPresent(String elementName, List<String> categoriesList) {
        SoftAssert softAssert = new SoftAssert();
        page.openPage();

        page.getMenuElement(elementName).click();
        categoriesList.forEach(element -> {
            page.getElementElementMenuItemByName(page.getMenuElement(elementName), element)
                    .click();
            page.getElementCategoryItems().forEach(element1 -> {
                System.out.println(element1.getText());
                softAssert.assertNotNull(page.getCategoryItemName(element1), "Title is missing for element: " + element1.getSearchCriteria());
                softAssert.assertNotNull(page.getCategoryItemDescription(element1), "Description is missing for element: " + element1.getSearchCriteria());
            });
        });
        softAssert.assertAll();
    }

    @DataProvider(name = "elementsListProvider")
    public Object[] elementsListProvider() {
        List<String> elementNamesList = new ArrayList<>();
        elementNamesList.add("Электроника");
        elementNamesList.add("Компьютеры и сети");
        elementNamesList.add("Бытовая техника");
        elementNamesList.add("Стройка и ремонт");
        elementNamesList.add("Дом и сад");
        elementNamesList.add("Авто и мото");
        elementNamesList.add("Красота и спорт");
        elementNamesList.add("Детям и мамам");
        elementNamesList.add("Работа и офис");
        elementNamesList.add("Еда");
        return new Object[]{
                elementNamesList
        };
    }
    @DataProvider(name = "categoryNameProvider")
    public Object[][] categoryNameProvider() {
        List<String> categoriesList = new ArrayList<>();
        categoriesList.add("Ноутбуки, компьютеры, мониторы");
        categoriesList.add("Комплектующие");
        categoriesList.add("Хранение данных");
        categoriesList.add("Сетевое оборудование");
        return new Object[][]{
                {"Компьютеры и сети", categoriesList}
        };
    }

    @DataProvider(name = "productCategoryNameProvider")
    public Object[][] productCategoryNameProvider() {
        List<String> categoriesList = new ArrayList<>();
        categoriesList.add("Комплектующие");
        categoriesList.add("Хранение данных");
        return new Object[][]{
                {"Компьютеры и сети", categoriesList}
        };
    }
}
