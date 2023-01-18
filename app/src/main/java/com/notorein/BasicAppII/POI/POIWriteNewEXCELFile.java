package com.notorein.BasicAppII.POI;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

import com.notorein.BasicAppII.Arrays;
import com.notorein.BasicAppII.Parameter.ParameterEXCEL;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;

public class POIWriteNewEXCELFile {


    public static File excelFileToWrite;
    private static int rows;
    private static int cells;
    private static float[] columnWidths;
    private static XSSFSheet sheet;
    private final int sheetIndex;
    private final int columnIndex;
    private final int cellIndex;
    private final Context context;

    public POIWriteNewEXCELFile(File file, int sheetIndex, int columnIndex, int cellIndex, Context context) {
        this.excelFileToWrite = file;
        this.sheetIndex = sheetIndex;
        this.columnIndex = columnIndex;
        this.cellIndex = cellIndex;
        this.context = context;
    }

    public void writeToExcelTable(File excelFileToWrite, ArrayList<ArrayList<String>> temp) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    if (!excelFileToWrite.exists()) {
                        copyAssets();
                    }
                    InputStream inputStream = new FileInputStream(excelFileToWrite);
                    int size = 0;
                    byte[] bucket = null;
                    try {
                        size = inputStream.available();
                        bucket = new byte[size];
                        inputStream.read(bucket);
                    } catch (IOException e2) {
                        e2.printStackTrace();

                    }
                    FileOutputStream outputStream = null;

                    try {
                        outputStream = new FileOutputStream(excelFileToWrite);
                    } catch (FileNotFoundException e2) {
                        e2.printStackTrace();
                    }
                    try {
                        outputStream.write(bucket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//
//
                    inputStream = new FileInputStream(excelFileToWrite);
                    XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
                    sheet = workbook.getSheetAt(ParameterEXCEL.sheetIndex);
                    rows = temp.size();

                    cells = Arrays.lessonsTemplateCardMetaInfoArrayMinLength;
                    Row row = null;
                    Cell cell = null;
                    for (int i = 0; i < rows; i++) {
                        row = sheet.getRow(i);
                        for (int n = 0; n < cells; n++) {

                            try {
                                cell = row.getCell(n);
                                cell.setCellValue(temp.get(i).get(n));
                            } catch (Exception e) {
                                row = sheet.createRow(i - 1);
                                cell = row.createCell(n - 1);
                                cell.setCellValue(temp.get(i).get(n));
                                break;
                            }

                        }
                    }
                    inputStream.close();
                    outputStream = new FileOutputStream(excelFileToWrite);
                    workbook.write(outputStream);
                    outputStream.close();
                    workbook.close();
                } catch (IOException e /* DocumentException e*/) {
                    e.printStackTrace();
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
    }


    private ArrayList<ArrayList<String>> data;
    private String fileName;


    public ArrayList<ArrayList<String>> readFromExcel() throws IOException {
        data = new ArrayList<>();

        // Check if external storage is available
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            // Get the external storage directory
            File directory = Environment.getExternalStorageDirectory();

            // Get the file in the "Notorein" folder
            File file = new File(directory, ParameterEXCEL.folderName + "/" + fileName);

            // Open the file using the Apache POI library
            Workbook workbook = new XSSFWorkbook(new FileInputStream(file));

            // Get the first sheet
            Sheet sheet = workbook.getSheetAt(0);

            // Iterate over the rows and cells
            for (Row row : sheet) {
                ArrayList<String> rowData = new ArrayList<>();
                for (Cell cell : row) {
                    // Get the cell value as a string
                    String cellValue = cell.getStringCellValue();
                    rowData.add(cellValue);
                }
                data.add(rowData);
            }

            // Close the workbook
            workbook.close();
        }

        return data;
    }


    public void writeToExcelTable() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    if (!excelFileToWrite.exists()) {
                        copyAssets();
                    }
                    InputStream inputStream = new FileInputStream(excelFileToWrite);
                    int size = 0;
                    byte[] bucket = null;
                    try {
                        size = inputStream.available();
                        bucket = new byte[size];
                        inputStream.read(bucket);
                    } catch (IOException e2) {
                        e2.printStackTrace();

                    }
                    FileOutputStream outputStream = null;

                    try {
                        outputStream = new FileOutputStream(excelFileToWrite);
                    } catch (FileNotFoundException e2) {
                        e2.printStackTrace();
                    }
                    try {
                        outputStream.write(bucket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//
//
                    inputStream = new FileInputStream(excelFileToWrite);
                    XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
                    sheet = workbook.getSheetAt(ParameterEXCEL.sheetIndex);
                    rows = Arrays.loadedCardsAsList.size();
                    LinkedList<LinkedList<String>> temp = Arrays.loadedCardsAsList;
                    cells = Arrays.lessonsTemplateCardMetaInfoArrayMinLength;
                    Row row = null;
                    Cell cell = null;
                    for (int i = 0; i < rows; i++) {
                        row = sheet.getRow(i);
                        for (int n = 0; n < cells; n++) {

                            try {
                                cell = row.getCell(n);
                                cell.setCellValue(Arrays.loadedCardsAsList.get(i).get(n));
                            } catch (Exception e) {
                                row = sheet.createRow(i - 1);
                                cell = row.createCell(n - 1);
                                cell.setCellValue(Arrays.loadedCardsAsList.get(i).get(n));
                                break;
                            }

                        }
                    }
                    inputStream.close();
                    outputStream = new FileOutputStream(excelFileToWrite);
                    workbook.write(outputStream);
                    outputStream.close();
                    workbook.close();
                } catch (IOException e /* DocumentException e*/) {
                    e.printStackTrace();
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
    }

    public void copyAssets() {
        AssetManager assetManager = context.getAssets();
        String[] files = null;
        try {
            files = assetManager.list("");
        } catch (IOException e) {
            Log.e("tag", "Failed to get asset file list.", e);
        }
        if (files != null) for (String filename : files) {
            if (filename.equals("sheet_template.xlsx")) {
                InputStream in = null;
                OutputStream out = null;
                try {
                    in = assetManager.open(filename);
                    File outFile = excelFileToWrite;
                    out = new FileOutputStream(outFile);
                    copyFile(in, out);
                } catch (IOException e) {
                    Log.e("tag", "Failed to copy asset file: " + filename, e);
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            // NOOP
                        }
                    }
                    if (out != null) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            // NOOP
                        }
                    }
                }
            }
        }
    }

    private static void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

    public static void copyAssets(Context context, File outFile) {
        AssetManager assetManager = context.getAssets();
        String[] files = null;
        try {
            files = assetManager.list("");
        } catch (IOException e) {
            Log.e("tag", "Failed to get asset file list.", e);
        }
        if (files != null) for (String filename : files) {
            if (filename.equals("sheet_template.xlsx")) {
                InputStream in = null;
                OutputStream out = null;
                try {
                    in = assetManager.open(filename);

                    out = new FileOutputStream(outFile);
                    copyFile(in, out);
                } catch (IOException e) {
                    Log.e("tag", "Failed to copy asset file: " + filename, e);
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            // NOOP
                        }
                    }
                    if (out != null) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            // NOOP
                        }
                    }
                }
            }
        }
    }

    public static boolean storeNewLesson(Activity activity, String lessonName, String[] array) {
        // First, check if external storage is available
        boolean stored = false;
        if (isExternalStorageWritable()) {
            // Get the directory for the app's private pictures directory
            File file = new File(Environment.getExternalStorageDirectory(), ParameterEXCEL.folderName);
            if (!file.mkdirs()) {
                Log.e(TAG, "Directory not created");
            }

            // Create a new Excel file with the given name
//            File excelFile = new File(file, lessonName + ".xlsx");


            // Get the file in the "Notorein" folder
            File excelFile = new File(Environment.getExternalStorageDirectory(), ParameterEXCEL.folderName + "/" + lessonName + ParameterEXCEL.fileExtension);

            try {
                // Create a new workbook and sheet
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("Sheet1");

                // Write the data to the sheet
                for (int i = 0; i < 10; i++) {
                    XSSFRow row = sheet.createRow(i);
                    for (int j = 0; j < array.length; j++) {
                        XSSFCell cell = row.createCell(j);
                        if (j == Arrays.sortIndexCard) {
                            cell.setCellValue(i);
                        } else {
                            cell.setCellValue(array[j]);
                        }
                    }
                }

                // Write the workbook to the file
                FileOutputStream outputStream = new FileOutputStream(excelFile);
                workbook.write(outputStream);
                workbook.close();
                stored = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stored;
    }

    /* Checks if external storage is available for read and write */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

}
