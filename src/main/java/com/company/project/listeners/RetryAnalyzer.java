package com.company.project.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int MAX_RETRY = 1;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < MAX_RETRY) {
            System.out.println("Retrying test: " + result.getName() + " (attempt " + (retryCount + 1) + ")");
            retryCount++;
            return true;
        }
        return false;
    }
}
