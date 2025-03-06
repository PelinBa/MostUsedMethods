package pb;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class TestMostUsedMethods extends BaseTest {

    @Test
    public void ScrollDemoTest() throws MalformedURLException, URISyntaxException, InterruptedException {

        driver.findElement(AppiumBy.accessibilityId("Views")).click();

        //Scroll down to the WebView
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"))"));
        Thread.sleep(2000);
        //Scroll down to the Gallery
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Gallery\"))")).click();

        //Go to photos
        driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();

        //Swipe
        WebElement firstImage = driver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]"));
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) firstImage).getId(),
                "direction", "left",
                "percent", 0.75
        ));

        //Come back
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        //Go to drag and drop section
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();

        //Drag and drop action
        WebElement source = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) source).getId(),
                "endX", 655,
                "endY", 590
        ));
        Thread.sleep(2000);

        //Verify the Dropped! text
        String result = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_result_text")).getText();
        Assert.assertEquals(result, "Dropped!");
    }
}
