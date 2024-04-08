# Run Extent_Report With Java-TestNG-Selenium On LambdaTest

## Pre-requisites

Before you can start performing Java automation testing with Selenium, you would need to:

- Install the latest **Java development environment** i.e. **JDK 1.6** or higher. We recommend using the latest version.

- Download the latest **Selenium Client** and its **WebDriver bindings** from the [official website](https://www.selenium.dev/downloads/). Latest versions of Selenium Client and WebDriver are ideal for running your automation script on LambdaTest Selenium cloud grid.

- Install **Maven** which supports **JUnit** framework out of the box. **Maven** can be downloaded and installed following the steps from [the official website](https://maven.apache.org/). Maven can also be installed easily on **Linux/MacOS** using [Homebrew](https://brew.sh/) package manager.

### Cloning Repo And Installing Dependencies

**Step 1:** Clone the LambdaTest’s Java-TestNG-Selenium repository and navigate to the code directory as shown below:

```bash
git clone https://github.com/LambdaTest-Certifications/JavaTestNG_extent_report.git
```

You can also run the command below to check for outdated dependencies.

```bash
mvn versions:display-dependency-updates
```

### Setting Up Your Authentication

Make sure you have your LambdaTest credentials with you to run test automation scripts. You can get these credentials from the [LambdaTest Automation Dashboard](https://automation.lambdatest.com/build?utm_source=github&utm_medium=repo&utm_campaign=Java-TestNG-Selenium) or by your [LambdaTest Profile](https://accounts.lambdatest.com/login?utm_source=github&utm_medium=repo&utm_campaign=Java-TestNG-Selenium).

**Step 2:** Set LambdaTest **Username** and **Access Key** in environment variables.

  ```bash
  set LT_USERNAME="YOUR_USERNAME" 
  set LT_ACCESS_KEY="YOUR ACCESS KEY"
  ```

### Configuring Your Test Capabilities

**Step 3:** In the test script (Report.java), you need to update your test capabilities. In this code, we are passing browserName, platformName, platformVersion and deviceName. The capabilities object in the above code are defined as:

```java
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setBrowserName("safari");
    capabilities.setCapability("platformName", "iOS");
    capabilities.setCapability("deviceName", "iPhone 14 Plus");
    capabilities.setCapability("platformVersion", "16");
```

You can generate capabilities for your test requirements with the help of our inbuilt [Desired Capability Generator](https://www.lambdatest.com/capabilities-generator/?utm_source=github&utm_medium=repo&utm_campaign=Java-TestNG-Selenium).

### Run Your First Test

Run the **report.xml** file after setting up the project.

### Need more support?

* Got a query? we are available 24x7 to help. [Contact Us](mailto:support@lambdatest.com)
* For more info, visit - [LambdaTest](https://www.lambdatest.com?utm_source=github&utm_medium=repo&utm_campaign=Java-TestNG-Selenium)
