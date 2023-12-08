package org.example.catalog;

import com.codeborne.selenide.Condition;
import org.example.OnlinerTvListener;
import org.example.pagese.CatalogSePO;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.$$x;

@Listeners(OnlinerTvListener.class)
public class OnlinerCatalogTest {
    static {
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
    }

    private CatalogSePO page = new CatalogSePO();

    @Test
    public void allMenuElementPresent() {
        page.openPage();
        page.getElectronics().shouldBe(Condition.visible);
        page.getAuto().shouldBe(Condition.visible);
        page.getAgd().shouldBe(Condition.visible);
        page.getBeauty().shouldBe(Condition.visible);
        page.getBuilding().shouldBe(Condition.visible);
        page.getChildren().shouldBe(Condition.visible);
        page.getComputers().shouldBe(Condition.visible);
        page.getHousehold().shouldBe(Condition.visible);
        page.getEveryday().shouldBe(Condition.visible);
        page.getPrime().shouldBe(Condition.visible);
    }

    @Test(dataProvider = "categoryNameProvider")
    public void computersItemSideMenuPresent(String categoryName) {
        page.openPage();
        page.getComputers().shouldBe(Condition.visible).click();
        Assert.assertEquals(1, page.getElementMenuList(page.getComputers()).filterBy(Condition.text(categoryName)).size(),
                "Section - "+ categoryName + "is absent");
    }

    @Test(dataProvider = "productCategoryNameProvider")
    public void computersProductListInfoPresent(String categoryName) {
        SoftAssert softAssert = new SoftAssert();
        page.openPage();
        page.getComputers().shouldBe(Condition.visible).click();
        var menuOption = page.getElementMenuList(page.getComputers())
                .filterBy(Condition.text(categoryName))
                .get(0).shouldBe(Condition.visible);

        menuOption.click();

        $$x("//div[@class=\"catalog-navigation-list__aside-item catalog-navigation-list__aside-item_active\"]//a/span")
                .stream().forEach(element -> {
                    softAssert.assertNotNull(
                            element
                                    .$x("./span[@class=\"catalog-navigation-list__dropdown-title\"]")
                                    .getText(),"Title is missing for element: " + element.getSearchCriteria()
                    );
                    softAssert.assertNotNull(
                            element
                                    .$x("./span[@class=\"catalog-navigation-list__dropdown-description\"]")
                                    .getText(), "Description is missing for element: "+ element.getSearchCriteria()
                    );
                });

        softAssert.assertAll();
    }

    @DataProvider(name = "categoryNameProvider")
    public Object[] categoryNameProvider() {
        return new Object[]{
                "Ноутбуки, компьютеры, мониторы",
                "Комплектующие",
                "Хранение данных",
                "Сетевое оборудование"
        };
    }

    @DataProvider(name = "productCategoryNameProvider")
    public Object[] productCategoryNameProvider(){
        return new Object[]{
                "Комплектующие",
                "Хранение данных"
        };
    }
}
