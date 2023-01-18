package com.notorein.BasicAppII.Files;


import static com.notorein.BasicAppII.Arrays.lessonsTemplateCardMetaInfoArrayMinLength;

import android.content.Context;

import com.notorein.BasicAppII.Arrays;

import java.io.FileOutputStream;
import java.io.IOException;

public class FilesWriteLessons {

    public static void writeFileWords(Context c, String[][] loadedCardsAsArray) {
        FileOutputStream out;
//        Context con = activity.getApplicationContext();
        String file = Arrays.lessonNames[Arrays.lessonIndex];
        try {
            out = c.openFileOutput(file, Context.MODE_PRIVATE);
            for (int i = 0; i < loadedCardsAsArray.length; i++) {
                for (int n = 0; n < lessonsTemplateCardMetaInfoArrayMinLength; n++) {
                    String text = loadedCardsAsArray[i][n] + "\t";
                    text = text.replace(" ", "_");
                    out.write(text.getBytes());
                }
                String text = "\n";
                out.write(text.getBytes());
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFileWordsAfterDeleting(Context c) {
        FileOutputStream out;
//        Context con = activity.getApplicationContext();
        String file = Arrays.lessonNames[Arrays.lessonIndex];
        try {
            out = c.openFileOutput(file, Context.MODE_PRIVATE);
            for (int i = 0; i < Arrays.loadedCardsAsArray.length; i++) {
//                if (i != editedCardIndex) {
                    for (int n = 0; n < lessonsTemplateCardMetaInfoArrayMinLength; n++) {
                        String text = Arrays.loadedCardsAsArray[i][n] + "\t";
                        text = text.replace(" ", "_");
                        out.write(text.getBytes());
                    }
                    String text = "\n";
                    out.write(text.getBytes());
//                }
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

