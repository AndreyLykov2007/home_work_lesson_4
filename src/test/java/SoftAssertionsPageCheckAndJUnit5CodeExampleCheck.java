import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SoftAssertionsPageCheckAndJUnit5CodeExampleCheck {
    @BeforeAll
    static void beforeAll(){
    Configuration.browserSize = "1920x1080";
    Configuration.baseUrl = "https://github.com/selenide/selenide";
    }
    @Test
void softAssertionsPageCheck() {
    open("/wiki");
    $(".markdown-body").$$("a").findBy(text("Soft assertions")).shouldHave(exist);
    }
    @Test
void jUnit5CodeExampleCheck() {
    open("/wiki/SoftAssertions");
    $("#wiki-wrapper")
            .shouldHave(text("@ExtendWith({SoftAssertsExtension.class})\n" +
            "class Tests {\n" +
            "  @Test\n" +
            "  void test() {\n" +
            "    Configuration.assertionMode = SOFT;\n" +
            "    open(\"page.html\");\n" +
            "\n" +
            "    $(\"#first\").should(visible).click();\n" +
            "    $(\"#second\").should(visible).click();\n" +
            "  }\n" +
            "}"))
            .shouldHave(text("class Tests {\n" +
                    "  @RegisterExtension \n" +
                    "  static SoftAssertsExtension softAsserts = new SoftAssertsExtension();\n" +
                    "\n" +
                    "  @Test\n" +
                    "  void test() {\n" +
                    "    Configuration.assertionMode = SOFT;\n" +
                    "    open(\"page.html\");\n" +
                    "\n" +
                    "    $(\"#first\").should(visible).click();\n" +
                    "    $(\"#second\").should(visible).click();\n" +
                    "  }\n" +
                    "}"));
 }
}
