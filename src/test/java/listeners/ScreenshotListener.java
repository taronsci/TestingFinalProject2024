package listeners;

import Tests.BaseTest;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ScreenshotListener extends TestListenerAdapter implements ITestListener{

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getName());
        WebDriver driver = ((BaseTest) result.getInstance()).getDriver();
        if (driver instanceof TakesScreenshot) {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String directory = "src/test/java/listeners";

            File directoryPath = new File(directory);
            if (!directoryPath.exists()) {
                directoryPath.mkdirs();
            }

            String filePath = directory + result.getName() + ".png";

            try {
                Files.copy(screenshot.toPath(), new File(filePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Screenshot captured: " + filePath);
            } catch (IOException e) {
                System.out.println("Failed to capture screenshot: " + e.getMessage());
            }
        } else {
            System.out.println("Driver does not support taking screenshots");
        }
    }

}
