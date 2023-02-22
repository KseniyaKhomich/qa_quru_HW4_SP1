import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class gitHubTest {

    @BeforeAll
    public static void beforeAllMethod() {
        Configuration.browserSize = "1920x1080";
    }

    @Test
    public void checkSoftAssertionPage() {
        String expectedCodeExampleString = """
                                              @ExtendWith({SoftAssertsExtension.class})
                                              class Tests {
                                                @Test
                                                void test() {
                                                  Configuration.assertionMode = SOFT;
                                                  open("page.html");
                                              
                                                  $("#first").should(visible).click();
                                                  $("#second").should(visible).click();
                                                }
                                              }""";

        open("https://github.com/selenide/selenide");
        $("#wiki-tab").click();
        $("#wiki-pages-box").$("button").click();
        SelenideElement softAssertionLink = $("#wiki-pages-box").$(byText("SoftAssertions"));
        softAssertionLink.shouldHave(text("SoftAssertions"));
        softAssertionLink.click();
        String actualCodeExampleString = $(byId("user-content-3-using-junit5-extend-test-class"))
                .ancestor("h4")
                .sibling(0)
                .$("pre")
                .getText();

        Assertions.assertEquals(expectedCodeExampleString, actualCodeExampleString, "Code Example is incorrect");
    }
}
