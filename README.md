# OLX Automation Framework

A Selenium + TestNG based test automation framework for [OLX India](https://www.olx.in/) following the **Page Object Model (POM)** design pattern.

---

## 📁 Project Structure

```
Hcl-final-project/
├── pom.xml                          # Maven dependencies & plugins
├── testng.xml                       # Full test suite configuration
├── allure.properties                # Allure report config
│
├── src/main/java/com/company/project/
│   ├── pages/
│   │   ├── HomePage.java            # OLX home page actions & locators
│   │   ├── SearchResultsPage.java   # Search results, filters, scroll
│   │   └── ProductDetailsPage.java  # Product title, price, seller, description
│   │
│   ├── drivers/
│   │   ├── DriverFactory.java       # Creates ChromeDriver instance
│   │   └── DriverManager.java       # ThreadLocal WebDriver management
│   │
│   ├── listeners/
│   │   ├── TestListener.java        # Logs pass/fail, screenshot on failure
│   │   ├── RetryAnalyzer.java       # Retries failed tests (max 2 times)
│   │   └── RetryListener.java       # Auto-applies RetryAnalyzer to all tests
│   │
│   └── utils/
│       ├── ConfigReader.java        # Reads config.properties
│       ├── WaitUtils.java           # Explicit wait helpers
│       ├── ScreenshotUtils.java     # Captures & saves screenshots
│       └── ExcelUtils.java          # Reads test data from Excel
│
├── src/test/java/com/company/project/tests/
│   ├── BaseTest.java                # @BeforeMethod / @AfterMethod setup
│   ├── HomePageTest.java            # TC01, TC13, TC14
│   ├── SearchProductTest.java       # TC02–TC07, TC11, TC12, TC15, TC16
│   └── ProductDetailsTest.java      # TC08–TC10, TC17, TC18, TC21, TC22
│
├── src/test/resources/
│   ├── config/config.properties     # URL, browser, timeout settings
│   ├── testdata/SearchData.xlsx     # Excel test data
│   └── suites/testng.xml            # Suite-level TestNG config
│
├── allure-results/                  # Raw Allure result files
├── allure-report/                   # Generated HTML Allure report
└── reports/screenshots/             # Auto-saved failure screenshots
```

---

## 🧪 Test Cases

| TC ID | Test Case | Class |
|-------|-----------|-------|
| TC01 | Verify OLX home page loads | `HomePageTest` |
| TC02 | Search with valid keyword | `SearchProductTest` |
| TC03 | Verify location filter | `SearchProductTest` |
| TC04 | Verify price range filter | `SearchProductTest` |
| TC05 | Verify brand filter | `SearchProductTest` |
| TC06 | Verify combined filters | `SearchProductTest` |
| TC07 | Verify result grid loads | `SearchProductTest` |
| TC08 | Verify product selection | `ProductDetailsTest` |
| TC09 | Verify product details displayed | `ProductDetailsTest` |
| TC10 | Verify screenshot capture | `ProductDetailsTest` |
| TC11 | Verify infinite scroll | `SearchProductTest` |
| TC12 | Verify images load properly | `SearchProductTest` |
| TC13 | Search with empty keyword | `HomePageTest` |
| TC14 | Search with invalid keyword | `HomePageTest` |
| TC15 | Invalid price range | `SearchProductTest` |
| TC16 | Apply filter with no results | `SearchProductTest` |
| TC17 | Broken image handling | `ProductDetailsTest` |
| TC18 | Product details missing | `ProductDetailsTest` |
| TC21 | Seller info unavailable | `ProductDetailsTest` |
| TC22 | Infinite scroll failure | `ProductDetailsTest` |

---

## ⚙️ Configuration

Edit `src/test/resources/config/config.properties`:

```properties
url=https://www.olx.in/
browser=chrome
implicit.wait=10
explicit.wait=15
```

---

## ▶️ How to Run

```bash
# Run full suite
mvn test

# Run by test class
mvn test -Dtest=HomePageTest
mvn test -Dtest=SearchProductTest
mvn test -Dtest=ProductDetailsTest
```

---

## 📊 Reports

- **Screenshots** – Auto-saved to `reports/screenshots/` on test failure
- **Surefire** – HTML report at `target/surefire-reports/`
- **Allure** – Generate with:
  ```bash
  mvn allure:report
  ```

---

## 🛠️ Tech Stack

| Tool | Version |
|------|---------|
| Java | 8+ |
| Selenium | 4.19.1 |
| TestNG | 7.9.0 |
| Maven | 3.x |
| Apache POI | 5.2.5 |
| Allure | 2.25.0 |
| Commons IO | 2.15.1 |
