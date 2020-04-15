package Pages;

import Configuration.ConfigProperties;
import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public abstract class BasePage {

    // This method opens page and waits for element to be existing
    public BasePage openPage(){
        open(ConfigProperties.BASE_URL);
        $(".w3-container").shouldBe(Condition.exist).shouldHave(Condition.text("SQL Statement:"));
        return this;
    }
}
