package qaguru.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class SelenideTest {

    private static final String BASE_URL = "https://github.com";
    private static final String REPOSITORY = "allure";
    private static final int ISSUE_NUMBER = 1303;

    @Test
    public void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open(BASE_URL);
        $(".header-search-input").click();
        $(".header-search-input").setValue(REPOSITORY).submit();
        $(By.linkText("allure-framework/allure2")).click();
        $(withText("Issues")).click();
        $(withText("#" + ISSUE_NUMBER)).should(Condition.exist);
    }
     //тест с намеренной ошибкой
    @Test
    public void testSelenideNoIssue() {
        //создание лога
        SelenideLogger.addListener("allure", new AllureSelenide().screenshots(false));

        open(BASE_URL);
        $(".header-search-input").click;
        $(By.linkText("allure-frameworkallure2")).click();
        $(withText("Issues")).click();
        $(withText("#" + ISSUE_NUMBER)).should(Condition.visible);

    }

}
