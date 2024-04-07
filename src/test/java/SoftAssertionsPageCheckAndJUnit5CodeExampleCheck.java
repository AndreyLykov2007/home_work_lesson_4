import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.selector.ByTagAndText;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.*;
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
    $(".markdown-body").$(new ByTagAndText("a", "Soft assertions")).shouldBe(exist);
    }
    @Test
void jUnit5CodeExampleCheck() {
    open("/wiki/SoftAssertions");
    $("#wiki-wrapper")
            .shouldHave(text("""
            @ExtendWith({SoftAssertsExtension.class})
            class Tests {
            @Test
              void test() {
                Configuration.assertionMode = SOFT;
                open("page.html");
                $("#first").should(visible).click();
                $("#second").should(visible).click();
              }
            }
            """))
            .shouldHave(text("""
                    class Tests {
                    @RegisterExtension
                    static SoftAssertsExtension softAsserts = new SoftAssertsExtension();
                    
                    @Test
                    void test() {
                    Configuration.assertionMode = SOFT;
                    open("page.html");
                    
                        $("#first").should(visible).click();
                        $("#second").should(visible).click();
                      }
                    }
                    """));
 }
}
