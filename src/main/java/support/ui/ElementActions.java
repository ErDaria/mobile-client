package support.ui;

import io.appium.java_client.MobileElement;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static settings.ParameterAccessor.getLaunchPropertyProvider;

@Slf4j
@UtilityClass
public class ElementActions {

    public static final String ELEMENT_WAS_FOUND = "The element {} was found";
    public static final String ELEMENT_WAS_NOT_FOUND = "The element {} was not found";

    public MobileElement getElement(By by, long timeout) {
        try {
            MobileElement element = (MobileElement) Waiting.until(ExpectedConditions.visibilityOfElementLocated(by), timeout);
            log.info(ELEMENT_WAS_FOUND, by);
            return element;
        } catch (TimeoutException ignored) {
            log.info(ELEMENT_WAS_NOT_FOUND, by);
            return null;
        }
    }

    public MobileElement getElement(By by) { return getElement(by, getLaunchPropertyProvider().getExplicitWaitDuration());}

    public boolean isElementVisible(By by, long timeout) { return getElement(by, timeout) != null; }

    public boolean isElementVisible(By by) { return isElementVisible(by, getLaunchPropertyProvider().getExplicitWaitDuration()); }

    public boolean isElementOnScreen(MobileElement mobileElement) {
        try {
            Waiting.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(mobileElement)));
            log.info(ELEMENT_WAS_FOUND, mobileElement.toString());
            return true;
        } catch (TimeoutException e) {
            log.info(ELEMENT_WAS_NOT_FOUND, mobileElement.toString());
            return false;
        }
    }

    public boolean isElementSelected(MobileElement mobileElement) {
        try {
            Waiting.until(ExpectedConditions.elementToBeSelected(mobileElement));
            log.info(ELEMENT_WAS_FOUND, mobileElement.toString());
            return true;
        } catch (TimeoutException e) {
            log.info(ELEMENT_WAS_NOT_FOUND, mobileElement.toString());
            return false;
        }
    }

    public void clickIfElementVisible(By by) {
        MobileElement element = getElement(by);
        if (element != null) {
            element.click();
        }
    }

    public void clickIfElementVisible(MobileElement element) {
        if (isElementOnScreen(element)) {
            element.click();
        }
    }

    public void clickEnabledElement(MobileElement element) {
        Waiting.until(ExpectedConditions.elementToBeClickable(element))
                .click();
    }

    public boolean isElementContainsText(MobileElement element, String text, long timeout) {
        try {
            Waiting.until(ExpectedConditions.textToBePresentInElement(element, text), timeout);
            log.info("The element contains the text '{}'", element);
            return true;
        } catch (TimeoutException ignored) {
            log.info("The element don't contain the text '{}'", element);
            return false;
        }
    }

}
