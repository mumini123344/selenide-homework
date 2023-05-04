import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class SelenideTests {

    @BeforeAll
    public static void setup() {
        Configuration.browser = "chrome";
        // მაინც არ ადიდებს
        Configuration.browserSize = "1980x1000";


    }


    @Test
    public void checkboxes() {
        open("http://the-internet.herokuapp.com/checkboxes");
        $("#checkboxes input:first-child").click();
        $("#checkboxes input:first-child").shouldBe(checked);
        $("#checkboxes input").shouldHave(attribute("type", "checkbox"));


    }

    @Test
    public void dropdown() {
        open("http://the-internet.herokuapp.com/dropdown");

        $("#dropdown").shouldHave(value(""));
        $("#dropdown").selectOption("Option 2");
        $("#dropdown").shouldHave(value("2"));

    }

    @Test
    public void textBoxes() {

        open("https://demoqa.com/text-box");
        $("#userName").sendKeys("Rati Shatirishvili");
        $("#userEmail").sendKeys("rati.shatirishvili.1@btu.edu.ge");
        $(byId("currentAddress")).sendKeys("Didi Digomi");
        // მერე პრაივატ კომენტარში როდამიწეროთ თუ შეძლებთ რომლის გამოყენება ჯობია sendKeys-ის თუ setValue-ს.
        // sendKeys-ის ვიცი რო ჯავას ვებელემენტის მეთოდია და წერს ელემენტში გადაცემულ ტექსტს, მაგრამ ორივე ერთი და იგივეს შვრება
        // და აქვს მნიშვნელობა რომელს გამოვიყენებთ?
        $(byXpath("//textarea[@id='permanentAddress']")).setValue("jer niznaiu");
        $("#submit").scrollTo();
        $(byXpath("//button[@id='submit']")).click();

        $$("#output").shouldHave(exactTexts("Name:Rati Shatirishvili\n" +
                "Email:rati.shatirishvili.1@btu.edu.ge\n" +
                "Current Address :Didi Digomi\n" +
                "Permananet Address :jer niznaiu\n"));


    }


}