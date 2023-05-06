import com.codeborne.selenide.*;
import com.codeborne.selenide.testng.ScreenShooter;
import org.junit.jupiter.api.BeforeAll;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static org.testng.AssertJUnit.assertTrue;

public class SelenideTestsTwo {

    @BeforeAll
    public static void setup() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";

    }

    @Test
    public void books() {
        open("https://demoqa.com/books");

        ElementsCollection books = $$(".rt-tr-group").filterBy(text("O'Reilly Media")).filterBy(text("Javascript"));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(books.size(), 10);

        SelenideElement firstBook = books.first();
        softAssert.assertEquals(firstBook.$(".rt-td").getText(), "Learning JavaScript Design Patterns");


        // პირველი ვერსია
//        books.stream().forEach(el -> {
//            el.find("div.action-buttons").scrollTo().click();
//            back();
//        });

        // მეორე ვერსია
        // ზევითაც და აქაც stream().forEach() მიწერია და პირდაპირ ვიცი რო forEach() რო დავწერო უფრო ოპტიმალური იქნება
        // მაგრამ როცა მარტო მაგას ვწერ კოდს ყოველთვის არუშვებს და ზოგჯერ ერორდება ამიტო ასე გადავწყვიტე დამეტოვა ორივე
        books.stream().forEach(each -> {
            each.scrollIntoView(true);
            each.$(".action-buttons .mr-2 a").click();
            back();
        });

        File reportsFolder = new File("src/main/resources/Reports");
        if (!reportsFolder.exists()) {
            reportsFolder.mkdirs();
        }

        Configuration.reportsFolder ="src/main/resources/Reports";
        Configuration.screenshots = true;
        Configuration.savePageSource = false;


    }

    @Test
    public void booksSearch() {
        open("https://demoqa.com/books");

//        $$(byXpath("//h5[contains(text(), \"O'Reilly Media\")]/ancestor::div[@class=\"rt-tbody\"]//div[contains(text(),'Javascript')]"));
//        $$(byXpath("//h5[contains(text(), \"O'Reilly Media\")]/ancestor::div[@class=\"rt-tbody\"]//img")).stream().forEach(img ->
//                assertTrue(img.getAttribute("src").length() > 0));

        $(".rt-table").find(".rt-tbody").findAll(".rt-tr-group").filterBy(text("O'Reilly Media")).filterBy(text("JavaScript"));
        $(" div.books-wrapper").find("div.rt-table").findAll("img[alt='image']");

    }


}