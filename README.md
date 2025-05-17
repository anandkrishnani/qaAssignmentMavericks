# QA Automation Framework – Mavericks Assignment

## 📌 Framework Overview

This is a **hybrid automation framework** built using **Java**, **TestNG**, and **Playwright**. It is designed to support both **UI** and **API testing**, with **multi-browser** capabilities and scalability in mind.

## ✅ Design Principles & Patterns Followed

- **Page Object Model (POM)**
- **Single Responsibility Principle (SRP)**
- **Arrange, Act, and Assert (AAA) Pattern**

---

## 🧱 Key Components

### 1. **Core Classes**
- **`PlaywrightFactory`**  
  Initializes browser drivers and loads properties from configuration.

- **`BaseTest`**  
  Parent class for all tests. Handles setup and teardown.

### 2. **Directory Structure**
- **`model/`**  
  Contains POJO classes used across API/UI flows.

- **`pages/`**  
  Contains Page Object classes with elements and page-level actions.

- **`utilities/`**  
  Includes `ElementUtilities`, a wrapper for commonly used Playwright methods (e.g., `click`, `fill`, `getText`).

- **`exception/`**  
  Contains `BrowserException`, a custom exception handler for browser-related failures.

- **`tests/`**
    - `LoginTest` and `AddToCartTest` – for UI testing
    - `APITest` – for REST API testing

- **`resources/`**
    - `config.properties` – configuration file for base URL, credentials, headless mode, etc.
    - `testng_regression.xml` – defines the test suite and test classes.

---

## 🔧 Configuration Parameters

Each test can be customized using the following parameters:

| Parameter | Description            | Supported Values         |
|----------|------------------------|--------------------------|
| `testType` | Type of test           | `UI` (default), `API`    |
| `browser`  | Browser to execute on  | `chrome`, `chromium`, `safari`, `firefox` |

---

## 📊 Logging & Reporting

- **Logging** – Integrated using **Logback**
- **Reporting** – Integrated with **Allure Reports**

---

## ▶️ How to Run the Tests

Open a terminal and navigate to the root directory of the project.

```bash
# Clean and run tests
mvn clean test

# Generate Allure reports
allure generate

# Serve the Allure report locally
allure serve
