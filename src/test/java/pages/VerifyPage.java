package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerifyPage {
    private SelenideElement codeField = $("[data-test-id=code] input");

    public VerifyPage() {
        codeField.shouldBe(visible);
    }
}
