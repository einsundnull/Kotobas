package com.notorein.Japanese.POI;

import static android.content.ContentValues.TAG;
import static com.notorein.BasicAppII.Arrays.cardlistLenght;
import static com.notorein.BasicAppII.Arrays.lessonIndex;
import static com.notorein.BasicAppII.Arrays.lessonNames;
import static com.notorein.BasicAppII.Arrays.loadedCardsAsArray;
import static com.notorein.BasicAppII.Arrays.loadedCardsAsList;
import static com.notorein.BasicAppII.Parameter.ParameterEXCEL.cellIndex;
import static com.notorein.BasicAppII.Parameter.ParameterEXCEL.columnIndex;
import static com.notorein.BasicAppII.Parameter.ParameterEXCEL.destinationDirectory;
import static com.notorein.BasicAppII.Parameter.ParameterEXCEL.folderName;
import static com.notorein.BasicAppII.Parameter.ParameterEXCEL.sheetIndex;

import android.os.Environment;
import android.util.Log;

import com.notorein.BasicAppII.Arrays;
import com.notorein.BasicAppII.Parameter.ParameterEXCEL;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class POIStoreExcelFile {

    private String[][] list;
    private String fileName;

    public POIStoreExcelFile(String[][] list, String fileName) {
        this.list = list;
        this.fileName = fileName;
        Log.i(TAG, "POIStoreExcelFile: " + fileName);

    }

    public static void updateFromExcelFile() {
        POIReadEXCELFile poi = new POIReadEXCELFile(new File(destinationDirectory, lessonNames[lessonIndex]), sheetIndex, columnIndex, cellIndex);
        loadedCardsAsList = poi.scanExcelByWhileLoopForWordListView();
        loadedCardsAsArray = poi.convertCardsArrayListToArray(loadedCardsAsList);
        Arrays.firstCardInRow = loadedCardsAsArray[0];
        Arrays.secondCardInRow = loadedCardsAsArray[1];
        cardlistLenght = loadedCardsAsList.size();
    }

    public static void readFromExcelFile() {
        POIReadEXCELFile poi = new POIReadEXCELFile(new File(destinationDirectory, lessonNames[lessonIndex]), sheetIndex, columnIndex, cellIndex);
        loadedCardsAsList = poi.scanExcelByWhileLoopForWordListView();
        loadedCardsAsArray = poi.convertCardsArrayListToArray(loadedCardsAsList);
        cardlistLenght = loadedCardsAsList.size();
    }

    public void writeToExcel() throws IOException {
        // Check if external storage is available
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {

                        // Get the external storage directory
                        File directory = Environment.getExternalStorageDirectory();
                        // Create a folder called "Notorein" in the external storage
                        File folder = new File(directory, folderName);
                        if (!folder.exists()) {
                            folder.mkdirs();
                        }

                        // Create a new workbook
                        Workbook workbook = new XSSFWorkbook();

                        // Create a new sheet
                        Sheet sheet = workbook.createSheet("Sheet1");

                        // Write the data to the sheet
                        for (int i = 0; i < list.length; i++) {
                            // Create a new row
                            Row row = sheet.createRow(i);

                            // Create a new cell
                            for (int n = 0; n < list[i].length; n++) {
                                Cell cell = row.createCell(n);
                                cell.setCellValue(list[i][n]);
                            }

                        }

                        // Write the workbook to a file
                        File file = new File(folder, fileName + ParameterEXCEL.fileExtension);
                        FileOutputStream fileOut = new FileOutputStream(file);
                        workbook.write(fileOut);
                        fileOut.close();

                        // Close the workbook

                        workbook.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.setDaemon(true);
            thread.start();
        }
    }
}
