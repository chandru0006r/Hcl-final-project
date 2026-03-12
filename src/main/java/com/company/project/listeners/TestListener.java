package com.company.project.listeners;

import com.company.project.drivers.DriverManager;
import com.company.project.utils.ScreenshotUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("▶ Starting Test: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✅ PASSED: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("❌ FAILED: " + result.getName());
        System.out.println("   Reason: " + result.getThrowable().getMessage());
        // Take screenshot on failure
        if (DriverManager.getDriver() != null) {
            ScreenshotUtils.captureScreenshot(DriverManager.getDriver(), result.getName() + "_FAILED");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⚠️ SKIPPED: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("========== Suite Started: " + context.getName() + " ==========");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("========== Suite Finished: " + context.getName() + " ==========");
        System.out.println("  Passed : " + context.getPassedTests().size());
        System.out.println("  Failed : " + context.getFailedTests().size());
        System.out.println("  Skipped: " + context.getSkippedTests().size());
    }
}
