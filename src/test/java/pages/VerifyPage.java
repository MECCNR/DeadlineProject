package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerifyPage {
    private static SelenideElement codeField = $("[data-test-id=code] input");
    private static SelenideElement verifyButton = $("[data-test-id=action-verify]");

    public VerifyPage() {
        codeField.shouldBe(visible);
    }

    public static void validVerify(String verificationCode) {
        codeField.setValue(verificationCode);
        verifyButton.click();
    }
}
