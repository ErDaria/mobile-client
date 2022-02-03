package support.ui;

import lombok.experimental.UtilityClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import settings.DriverConfigurator;

import static settings.ParameterAccessor.getLaunchPropertyProvider;

@UtilityClass
public class Waiting {

    public <T> T until(ExpectedCondition<T> isTrue, long timeoutInSeconds) {
        return new WebDriverWait(DriverConfigurator.getDriver(), timeoutInSeconds)
                .until(isTrue);
    }

    public <T> T until(ExpectedCondition<T> isTrue) {
        return until(isTrue, getLaunchPropertyProvider().getExplicitWaitDuration());
    }

    public WebElement waitForElementPresent(String id){
        WebDriverWait wait = new WebDriverWait(DriverConfigurator.getDriver(), 10);
        By by = By.id(id);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
