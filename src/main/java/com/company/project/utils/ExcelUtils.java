package com.company.project.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {

    private static final String EXCEL_PATH = "src/test/resources/testdata/SearchData.xlsx";

    public static String getTestData(String sheetName, int row, int col) {
        try (FileInputStream fis = new FileInputStream(EXCEL_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) throw new RuntimeException("Sheet '" + sheetName + "' not found.");
            Row dataRow = sheet.getRow(row);
            if (dataRow == null) throw new RuntimeException("Row " + row + " not found.");
            Cell cell = dataRow.getCell(col);
            if (cell == null) return "";

            DataFormatter formatter = new DataFormatter();
            return formatter.formatCellValue(cell);

        } catch (IOException e) {
            throw new RuntimeException("Failed to read Excel: " + e.getMessage(), e);
        }
    }
}
