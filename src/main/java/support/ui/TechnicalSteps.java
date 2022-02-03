package support.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.RemoteWebElement;
import settings.DriverConfigurator;

import java.time.Duration;

import static java.lang.String.format;

@Slf4j
public class TechnicalSteps {
    private static final int ANIMATION_TIME = 700;
    private static final int PRESS_TIME = 500;
    private static final int EDGE_BORDER = 10;

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    protected void getTargetElementOnScreen(MobileElement targetElement, Direction direction) {
        try {
            targetElement.isDisplayed();
        } catch (NoSuchElementException e) {
            swipeScreen(direction);
        }
    }

    protected void tapInCenterOfElement(MobileElement element) {
        AppiumDriver<RemoteWebElement> driver = DriverConfigurator.getDriver();
        new TouchAction(driver).tap(new PointOption().withCoordinates(element.getCenter()))
                .perform();
    }

    protected void dragAndDropToElement(MobileElement fromElement, MobileElement toElement) {
        AppiumDriver<RemoteWebElement> driver = DriverConfigurator.getDriver();
        TouchAction<?> action = new TouchAction(driver);

        Point fromElementLocation = fromElement.getLocation();
        Point toElementLocation = toElement.getLocation();
        action.press(PointOption.point(fromElementLocation)).moveTo(PointOption.point(toElementLocation)).perform().release();
    }

    protected void dragAndDropYOffset(MobileElement fromElement, int yOffset) {
        AppiumDriver<RemoteWebElement> driver = DriverConfigurator.getDriver();
        TouchAction<?> action = new TouchAction(driver);

        int x = fromElement.getLocation().getX();
        int y = fromElement.getLocation().getY();
        int target = y + yOffset;
        action.press(PointOption.point(x, y)).moveTo(PointOption.point(x, target)).perform().release();
    }

    protected void dragAndDropXOffset(MobileElement fromElement, int xOffset) {
        AppiumDriver<RemoteWebElement> driver = DriverConfigurator.getDriver();
        TouchAction action = new TouchAction(driver);

        int x = fromElement.getLocation().getX();
        int y = fromElement.getLocation().getY();
        int target = x + xOffset;
        action.press(PointOption.point(x, y)).moveTo(PointOption.point(target, y)).perform().release();
    }

    protected void swipeScreenFromHeightOfElement(Direction dir, MobileElement element, int attempt) {
        AppiumDriver<RemoteWebElement> driver = DriverConfigurator.getDriver();
        PointOption pointOptionStart;
        PointOption pointOptionEnd;
        int y = element.getLocation().getY();

        Dimension dims = driver.manage().window().getSize();
        pointOptionStart = PointOption.point(dims.width / 2, y);

        switch (dir) {
            case DOWN:
                pointOptionEnd = PointOption.point(dims.width / 2, y - EDGE_BORDER);
                break;
            case UP:
                pointOptionEnd = PointOption.point(dims.width / 2, EDGE_BORDER);
                break;
            case LEFT:
                pointOptionEnd = PointOption.point(EDGE_BORDER, y);
                break;
            case RIGHT:
                pointOptionEnd = PointOption.point(dims.width - EDGE_BORDER, y);
                break;
            default:
                throw new IllegalArgumentException(format("swipeScreenFromHeightOfElement(): dir: '%s' NOT supported", dir));
        }

        for (int i = 1; i <= attempt; i++) {
            try {
                new TouchAction(driver)
                        .press(pointOptionStart)
                        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                        .moveTo(pointOptionEnd)
                        .release().perform();
            } catch (Exception e) {
                log.error("swipeScreenFromHeightOfElement(): TouchAction FAILED\n" + e.getMessage());
                return;
            }

            try {
                Thread.sleep(ANIMATION_TIME);
            } catch (InterruptedException e) {
                // ignore
            }
        }
    }

    protected void swipeScreen(Direction dir) {
        AppiumDriver<RemoteWebElement> driver = DriverConfigurator.getDriver();
        PointOption pointOptionStart;
        PointOption pointOptionEnd;
        Dimension dims = driver.manage().window().getSize();
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);

        switch (dir) {
            case DOWN:
                pointOptionEnd = PointOption.point(dims.width / 2, dims.height - EDGE_BORDER);
                break;
            case UP:
                pointOptionEnd = PointOption.point(dims.width / 2, EDGE_BORDER);
                break;
            case LEFT:
                pointOptionEnd = PointOption.point(EDGE_BORDER, dims.height / 2);
                break;
            case RIGHT:
                pointOptionEnd = PointOption.point(dims.width - EDGE_BORDER, dims.height / 2);
                break;
            default:
                throw new IllegalArgumentException(format("swipeScreen(): dir: '%s' NOT supported", dir));
        }

        try {
            new TouchAction(driver)
                    .press(pointOptionStart)
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            log.error("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    protected void swipeUntilVisibleTo(MobileElement target, Direction dir, int maxAttempt) {
        int attempt = 0;
        do {
            swipeScreen(dir);
            attempt++;
        } while ((attempt <= maxAttempt) && !(ElementActions.isElementOnScreen(target)));
    }
}
