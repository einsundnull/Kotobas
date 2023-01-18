package com.notorein.Japanese.POI;

// In Build.gradle :
//          https://mvnrepository.com/artifact/org.apache.poi/poi
//          implementation group: 'org.apache.poi', name: 'poi', version: '3.9'
//          implementation group: 'org.apache.poi', name: 'poi-ooxml', version: '3.16-beta1'
//          Thanks for using https://jar-download.com

import static android.content.ContentValues.TAG;
import static com.notorein.BasicAppII.Arrays.lessonsTemplateCardMetaInfoArrayMinLength;
import static com.notorein.BasicAppII.Arrays.loadedCardsAsList;
import static com.notorein.BasicAppII.Arrays.removeEmptyCards;
import static com.notorein.BasicAppII.Arrays.sortIndexCard;
import static com.notorein.BasicAppII.Strings.StringsEXCEL.lessonTemplateEmpty;
import static org.apache.poi.ss.usermodel.CellType.NUMERIC;

import android.util.Log;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class POIReadEXCELFile {

    private File excelFileToRead;

    private String excelFilePath;
    private String excelFileName;
    private int sheetIndex = 1;
    private int columnIndex = 1;
    private int cellIndex = 1;


    public POIReadEXCELFile(File file, int sheetIndex, int columnIndex, int cellIndex) {
        this.excelFileToRead = file;
        this.sheetIndex = sheetIndex;
        this.columnIndex = columnIndex;
        this.cellIndex = cellIndex;
    }


    public ArrayList<ArrayList<String>> scanExcelByWhileLoopForWordListView() {
        if (excelFileToRead.exists()) {
//            Log.i(TAG, "file: " + excelFileToRead);
            FileInputStream inputStream = null;
            Workbook workbook = null;
            Sheet firstSheet = null;
            Iterator<Row> iterator = null;
            String value = "";
            int sheetCounter = 0;
            int rowCounter = 0;
            int cellCounter = 0;
            loadedCardsAsList = new ArrayList<ArrayList<String>>();
            try {
                inputStream = new FileInputStream(excelFileToRead);
                workbook = new XSSFWorkbook(inputStream);
                firstSheet = (Sheet) workbook.getSheetAt(sheetIndex);
                iterator = firstSheet.iterator();
//                Log.i(TAG, "\n\n\n\nscanExcelByWhileLoopForWordListView: Check Whether the correction funcion adds an unecesarry card at the end");
                while (iterator.hasNext()) {
                    Row nextRow = iterator.next();
                    Iterator<Cell> cellIterator = nextRow.cellIterator();
                    loadedCardsAsList.add(new ArrayList<String>());
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        if (cell.getCellTypeEnum() != NUMERIC) {
                            value = cell.getStringCellValue().replace("\n", "").replace("\t", "");
                        } else {
                            value = "" + (int) cell.getNumericCellValue();
                        }
                        loadedCardsAsList.get(rowCounter).add(cellCounter, value);
//                        Log.i(TAG, "" + value);
                        cellCounter++;
                    }
//                    Log.i(TAG, "\n\n");
                    // If there are values missing they will be added automatically.
                    ArrayList list = fillMissingCellValueLogic(rowCounter, lessonsTemplateCardMetaInfoArrayMinLength, loadedCardsAsList.get(loadedCardsAsList.size() - 1));
                    loadedCardsAsList.remove(rowCounter);
                    loadedCardsAsList.add(list);
                    rowCounter++;
                    cellCounter = 0;
                }
                workbook.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
//            Log.i(TAG, "re/adExcelFile: " + "FILE DOES NOT EXSISTS");
        }
        try {
            loadedCardsAsList =  removeEmptyCards(loadedCardsAsList);
        } catch (Exception e) {
            Log.i(TAG, "scanExcelByWhileLoopForWordListView: " + loadedCardsAsList);
        }
        return loadedCardsAsList;
    }



    private ArrayList<String> fillMissingCellValueLogic(int rowCounter, int minLength, ArrayList<String> list) {
        String temp = "";
        for (int i = 0; i < minLength; i++) {
            try {
                temp = list.get(i);
                if (temp.isEmpty()) {
                    list.remove(i);
                    if (i != sortIndexCard) {
                        list.add(i, lessonTemplateEmpty[i]);
                    } else {
                        list.add(i, "" + rowCounter);
                    }
                }
            } catch (Exception e) {
                if (i != sortIndexCard) {
                    list.add(i, lessonTemplateEmpty[i]);
                } else {
                    list.add(i, "" + rowCounter);
                }
            }
        }
        return list;
    }

    public static  String[] fillMissingCellValueLogic(int rowCounter, int minLength, String[] list) {
        String temp = "";
        for (int i = 0; i < minLength; i++) {
            try {
                temp = list[i];
                if (temp.isEmpty()) {
                    if (i != sortIndexCard) {
                        list[i] =lessonTemplateEmpty[i];
                    } else {
                        list[i] = "" + rowCounter;
                    }
                }
            } catch (Exception e) {
                if (i != sortIndexCard) {
                    list[i] = lessonTemplateEmpty[i];
                } else {
                    list[i] = "" + rowCounter;
                }
            }
        }
        return list;
    }

    public static String[][] convertCardsArrayListToArray(ArrayList<ArrayList<String>> loadedLessonAsList) {
        // AI
        int sizeOne = loadedLessonAsList.size();
        int sizeTwo = lessonsTemplateCardMetaInfoArrayMinLength;
        String[][] temp = new String[sizeOne][sizeTwo];
        for (int i = 0; i < sizeOne; i++) {
            ArrayList<String> row = loadedLessonAsList.get(i);
            for (int n = 0; n < sizeTwo; n++) {
                String value = row.get(n);
                temp[i][n] = value != null ? value : lessonTemplateEmpty[n];
            }
        }
        return temp;
    }

//    public static String[][] convertCardsArrayListToArray(ArrayList<ArrayList<String>> loadedLessonAsList) {
//        int sizeOne = loadedLessonAsList.size();
//        int sizeTwo = lessonsTemplateCardMetaInfoArrayMinLength;
//        String[][] temp = new String[sizeOne][sizeTwo];
//        for (int i = 0; i < sizeOne; i++) {
//            for (int n = 0; n < sizeTwo; n++) {
//                String value = loadedLessonAsList.get(i).get(n);
//                if (value != null) {
//                    temp[i][n] = loadedLessonAsList.get(i).get(n);
//                } else {
//                    temp[i][n] = lessonTemplateEmpty[n];
//                }
//
////                Log.i(TAG, "\n\nconvertCardsArrayListToArray: " + loadedLessonAsList.get(i).get(n));
//            }
//        }
//        return temp;
//    }

//    public static String[][] convertCardsArrayListToArray(ArrayList<ArrayList<String>> loadedLessonAsList) {
//        int sizeOne = loadedLessonAsList.size();
//        int sizeTwo = lessonsTemplateCardMetaInfoArrayMinLength;
//        int setIndex = 0;
//        String[][] temp = new String[sizeOne][sizeTwo];
//        for (int i = 0; i < sizeOne-1; i++) {
//            setIndex = Integer.parseInt(loadedLessonAsList.get(i).get(sortIndexCard));
//            for (int n = 0; n < sizeTwo; n++) {
//                temp[setIndex][n] = loadedLessonAsList.get(setIndex).get(n);
//            }
//        }
//
//        return temp;
//    }


}
