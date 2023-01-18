package com.notorein.BasicAppII.Files;

import static com.notorein.BasicAppII.Parameter.Parameter.advancedRepeatLogicIndex;
import static com.notorein.BasicAppII.Parameter.Parameter.alwaysShowManualAtStart;
import static com.notorein.BasicAppII.Parameter.Parameter.deletedLesson;
import static com.notorein.BasicAppII.Parameter.Parameter.isTrialVersion;
import static com.notorein.BasicAppII.Parameter.Parameter.manualWasShown;
import static com.notorein.BasicAppII.Parameter.Parameter.nightMode;
import static com.notorein.BasicAppII.Parameter.Parameter.orientationI;
import static com.notorein.BasicAppII.Parameter.Parameter.orientationII;
import static com.notorein.BasicAppII.Parameter.Parameter.playAnswerSound;
import static com.notorein.BasicAppII.Parameter.Parameter.playQuestionSound;
import static com.notorein.BasicAppII.Parameter.Parameter.playSoundWhenAnswerIsShown;
import static com.notorein.BasicAppII.Parameter.Parameter.playSoundWhenQuestionIsShown;
import static com.notorein.BasicAppII.Parameter.Parameter.saveBatteryMode;
import static com.notorein.BasicAppII.Parameter.Parameter.settingsWereUsed;
import static com.notorein.BasicAppII.Parameter.Parameter.settingsWereUsedII;
import static com.notorein.BasicAppII.Parameter.Parameter.showMenu;
import static com.notorein.BasicAppII.Parameter.ParameterSettingFiles.fileNameLessonSettings;
import static com.notorein.BasicAppII.Parameter.ParameterSettingFiles.fileNameStartUpSettings;

import android.app.Activity;
import android.content.Context;

import com.notorein.BasicAppII.AnswerLogic;
import com.notorein.BasicAppII.Arrays;
import com.notorein.BasicAppII.Trial;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FilesWriteSettings {
    public static void writeStartupSettings(Activity activity) {
        FileOutputStream out;
        String text;
        try {
            out = activity.getApplicationContext().openFileOutput(fileNameStartUpSettings, Context.MODE_PRIVATE);
            text = "manualWasShown" + "\t" + manualWasShown + "\n";
            out.write(text.getBytes());
            text = "settingsWereUsed" + "\t" + settingsWereUsed + "\n";
            out.write(text.getBytes());
            text = "settingsWereUsedII" + "\t" + settingsWereUsedII + "\n";
            out.write(text.getBytes());
            text = "showMenu" + "\t" + showMenu + "\n";
            out.write(text.getBytes());
            text = "orientationTemporaryI" + "\t" + orientationI + "\n";
            out.write(text.getBytes());
            text = "orientationTemporaryII" + "\t" + orientationII + "\n";
            out.write(text.getBytes());
            text = "alwaysShowManualAtStart" + "\t" + alwaysShowManualAtStart + "\n";
            out.write(text.getBytes());
            text = "nightMode" + "\t" + nightMode + "\n";
            out.write(text.getBytes());
            text = "saveBatteryMode" + "\t" + saveBatteryMode + "\n";
            out.write(text.getBytes());
            text = "playQuestionSound" + "\t" + playQuestionSound + "\n";
            out.write(text.getBytes());
            text = "playAnswerSound" + "\t" + playAnswerSound + "\n";
            out.write(text.getBytes());
            text = "isTrialVersion" + "\t" + isTrialVersion + "\n";
            out.write(text.getBytes());
            text = "trialCount" + "\t" + Trial.trialCount + "\n";
            out.write(text.getBytes());
////            if (moveButtons) {
//                text = "buttonLayoutY" + "\t" + buttonLayoutY + "\n";
//                out.write(text.getBytes());
//                text = "buttonLayoutYCounter" + "\t" + buttonLayoutYCounter + "\n";
//                out.write(text.getBytes());
////            } else {
//
////            }
            text = "advancedRepeatLogicIndex" + "\t" + advancedRepeatLogicIndex + "\n";
            out.write(text.getBytes());
            text = "deletedLesson" + "\t" + deletedLesson + "\n";
            out.write(text.getBytes());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeLessonSettings(Activity activity) {

        FileOutputStream out;
        String text;
        try {
            out = activity.getApplicationContext().openFileOutput(fileNameLessonSettings, Context.MODE_PRIVATE);
            text = "advancedRepeatLogicIsActive" + "\t" + AnswerLogic.advancedRepeatLogicIsActive + "\n";
            out.write(text.getBytes());
            text = "alwaysShowQuestionWithAnswer" + "\t" + AnswerLogic.alwaysShowQuestionWithAnswer + "\n";
            out.write(text.getBytes());
            text = "questionIsReversed" + "\t" + AnswerLogic.questionIsReversed + "\n";
            out.write(text.getBytes());
            text = "introduceNewCards" + "\t" + AnswerLogic.introduceNewCards + "\n";
            out.write(text.getBytes());
//            text = "answerWasShownWhenDestroy" + "\t" + AnswerLogic.answerWasShownWhenDestroy + "\n";
//            out.write(text.getBytes());
            text = "switchBetweenLessons" + "\t" + AnswerLogic.switchBetweenLessons + "\n";
            out.write(text.getBytes());
            text = "useTimeToRepeat" + "\t" + AnswerLogic.useTimeToRepeat + "\n";
            out.write(text.getBytes());
            text = "languageIndex" + "\t" + Arrays.languageIndex + "\n";
            out.write(text.getBytes());
            text = "lessonIndex" + "\t" + Arrays.lessonIndex + "\n";
            out.write(text.getBytes());
            text = "isTrialVersion" + "\t" + isTrialVersion + "\n";
            out.write(text.getBytes());
            text = "trialCount" + "\t" + Trial.trialCount + "\n";
            out.write(text.getBytes());
            text = "playQuestionSound" + "\t" + playSoundWhenQuestionIsShown + "\n";
            out.write(text.getBytes());
            text = "playAnswerSound" + "\t" + playSoundWhenAnswerIsShown + "\n";
            out.write(text.getBytes());
//            if (moveButtons) {
//                text = "buttonLayoutY" + "\t" + buttonLayoutY*-1 + "\n";
//                out.write(text.getBytes());
//                text = "buttonLayoutYCounter" + "\t" + buttonLayoutYCounter*-1 + "\n";
//                out.write(text.getBytes());
//            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createSettingsFiles(Context context, Activity activity) {
        try {
            FileInputStream in = context.getApplicationContext().openFileInput(fileNameStartUpSettings);
            if (in == null) {
                writeStartupSettings(activity);
                writeLessonSettings(activity);

            }
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
