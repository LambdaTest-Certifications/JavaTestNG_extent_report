import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class Report2 {
    private static final String LT_HUB = "https://maheshrlambdatest:TJ1qSA1ghgSFzfcA8U5GZA0Gp9zlBIhpEgSOdLq4ATpb8Y080v@hub.lambdatest.com/wd/hub";
    private RemoteWebDriver driver = null;
    private ExtentReports extent;
    private ExtentTest logger;

    @BeforeTest
    public void startReport() throws MalformedURLException {
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/RKExtentReport.html");
        extent.attachReporter(spark);
        spark.config().setDocumentTitle("Extent Report");
        spark.config().setReportName("Functional Test Automation Report");
        spark.config().setTheme(Theme.STANDARD);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("safari");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 14 Plus");
        capabilities.setCapability("platformVersion", "16");

        driver = new RemoteWebDriver(new URL(LT_HUB), capabilities);
    }

    @Test
    public void verifyGoogleTitleAndLogo() {
        logger = extent.createTest("Verify Google Title and Logo");
        long startTime = System.currentTimeMillis();
        driver.get("http://www.google.com");
        System.out.println("Navigated to Google");

        try {
            String title = driver.getTitle();
            logger.log(Status.INFO, "Page title: " + title);
            System.out.println("Page title: " + title);
            Assert.assertEquals(title, "Google", "Page title is incorrect");

            boolean isLogoDisplayed = driver.findElement(By.id("hplogo")).isDisplayed();
            Assert.assertTrue(isLogoDisplayed, "Google logo is not displayed");
            logger.log(Status.PASS, "Google logo is displayed");
            captureScreenshot();
        } catch (NoSuchElementException e) {
            logger.log(Status.FAIL, "Element not found: " + e.getMessage());
            System.out.println("Element not found: " + e.getMessage());
            throw e;
        } finally {
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            logger.log(Status.INFO, "Test duration: " + duration + " milliseconds");
            System.out.println("Test duration: " + duration + " milliseconds");
        }
    }

    @Test
    public void verifyWikipediaTitleAndLogo() {
        logger = extent.createTest("Verify Wikipedia Title and Logo");
        long startTime = System.currentTimeMillis();
        driver.get("https://www.wikipedia.org");
        System.out.println("Navigated to Wikipedia");

        try {
            String title = driver.getTitle();
            logger.log(Status.INFO, "Page title: " + title);
            System.out.println("Page title: " + title);
            Assert.assertEquals(title, "Wikipedia", "Page title is incorrect");

            boolean isLogoDisplayed = driver.findElement(By.className("central-featured-logo")).isDisplayed();
            Assert.assertTrue(isLogoDisplayed, "Wikipedia logo is not displayed");
            logger.log(Status.PASS, "Wikipedia logo is displayed");
            captureScreenshot();
        } catch (NoSuchElementException e) {
            logger.log(Status.FAIL, "Element not found: " + e.getMessage());
            System.out.println("Element not found: " + e.getMessage());
            throw e;
        } finally {
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            logger.log(Status.INFO, "Test duration: " + duration + " milliseconds");
            System.out.println("Test duration: " + duration + " milliseconds");
        }
    }

    private void captureScreenshot() {
        try {
            // Take screenshot
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Generate screenshot file name
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotName = "Screenshot_" + timeStamp + ".png";

            // Create the directory if not exists
            Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/test-output/screenshots"));

            // Define the destination path
            String destPath = System.getProperty("user.dir") + "/test-output/screenshots/" + screenshotName;

            // Copy screenshot to the destination path
            Files.copy(srcFile.toPath(), new File(destPath).toPath());

            // Convert screenshot to base64 string
            String base64Screenshot = encodeFileToBase64Binary(new File(destPath));

            // Embed base64 screenshot into the log
            logger.fail("Failed", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());

        } catch (IOException e) {
            logger.fail("Failed to capture screenshot: " + e.getMessage());
            System.out.println("Failed to capture screenshot: " + e.getMessage());
            e.printStackTrace(); // Add this line to print the stack trace
        }
    }

    private String encodeFileToBase64Binary(File file) throws IOException {
        byte[] bytes = Files.readAllBytes(file.toPath());
        return Base64.getEncoder().encodeToString(bytes);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.log(Status.FAIL, "Test Case Failed: " + result.getName());
            logger.log(Status.FAIL, "Test Case Failed: " + result.getThrowable());
            System.out.println("Test Case Failed: " + result.getName());
            System.out.println("Test Case Failed: " + result.getThrowable());
        }
    }

    @AfterTest
    public void endReport() {
        extent.flush();
        if (driver != null) {
            driver.quit();
        }
    }
}
