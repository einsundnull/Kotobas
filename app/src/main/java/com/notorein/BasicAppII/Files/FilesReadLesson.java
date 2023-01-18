package com.notorein.BasicAppII.Files;


import android.app.Activity;

import com.notorein.BasicAppII.Arrays;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class FilesReadLesson {


    public static void readFromExistingWordFileInInternalStorage(Activity activity) {
        Scanner scn;
        try {
            FileInputStream in = activity.getApplicationContext().openFileInput(Arrays.lessonNames[Arrays.lessonIndex]);
            scn = new Scanner(in, "UTF-8");

            if (Arrays.loadedCardsAsList != null) {
                Arrays.loadedCardsAsList.clear();
            }
            scanLessonFile(scn);
            scn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void scanLessonFile(Scanner scn) {
        int index = 0;
        if (Arrays.loadedCardsAsList == null) {
            Arrays.loadedCardsAsList = new LinkedList<LinkedList<String>>();
        }
        while (scn.hasNext()) {
            try {
                Arrays.loadedCardsAsList.add(index, new LinkedList<>());
                for (int n = 0; n < Arrays.lessonsTemplateCardMetaInfoArrayMinLength; n++) {
                    String text = scn.next();
                    text = text.replace("_"," ");
                    if (!text.isEmpty()) Arrays.loadedCardsAsList.get(index).add(text);
                }
            } catch (Exception e) {
                break;
            }
        }
        Arrays.readListToArray();
    }

}
