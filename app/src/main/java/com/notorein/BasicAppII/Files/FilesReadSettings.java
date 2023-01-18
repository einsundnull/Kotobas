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

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class FilesReadSettings {

    public static void scanStartUpSettingsFile(Context context, Activity activity) {
        try {
            InputStream ips = context.getApplicationContext().openFileInput(fileNameStartUpSettings);
            Scanner scn = new Scanner(ips, "UTF-8");
            while (scn.hasNext()) {
                try {
                    scn.next();
                    manualWasShown = scn.nextBoolean();
                    scn.next();
                    settingsWereUsed = scn.nextBoolean();
                    scn.next();
                    settingsWereUsedII = scn.nextBoolean();
                    scn.next();
                    showMenu = scn.nextBoolean();
                    scn.next();
                    orientationI = scn.nextInt();
                    scn.next();
                    orientationII = scn.nextInt();
                    scn.next();
                    alwaysShowManualAtStart = scn.nextBoolean();
                    scn.next();
                    nightMode = scn.nextBoolean();
                    scn.next();
                    saveBatteryMode = scn.nextBoolean();
                    scn.next();
                    playQuestionSound = scn.nextBoolean();
                    scn.next();
                    playAnswerSound = scn.nextBoolean();
                    scn.next();
                    isTrialVersion = scn.nextBoolean();
                    scn.next();
                    Trial.trialCount = scn.nextInt();
                    scn.next();
//                    buttonLayoutY = scn.nextFloat();
//                    scn.next();
//                    buttonLayoutYCounter = scn.nextInt();
//                    scn.next();
                    advancedRepeatLogicIndex = scn.nextInt();
                    scn.next();
                    deletedLesson = scn.nextBoolean();
                    scn.next();
                } catch (Exception e) {
                    break;
                }
                System.err.println("Lessons.lessonIndex " + Arrays.lessonIndex);
            }
            scn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void scanLessonSettingsFile(Activity activity) {
        try {
            InputStream ips = activity.getApplicationContext().openFileInput(fileNameLessonSettings);
            Scanner scn = new Scanner(ips, "UTF-8");
            while (scn.hasNext()) {
                try {
                    scn.next();
                    AnswerLogic.advancedRepeatLogicIsActive = scn.nextBoolean();
                    scn.next();
                    AnswerLogic.alwaysShowQuestionWithAnswer = scn.nextBoolean();
                    scn.next();
                    AnswerLogic.questionIsReversed = scn.nextBoolean();
                    scn.next();
                    AnswerLogic.introduceNewCards = scn.nextBoolean();
//                    scn.next();
//                    AnswerLogic.answerWasShownWhenDestroy = scn.nextBoolean();
                    scn.next();
                    AnswerLogic.switchBetweenLessons = scn.nextBoolean();
                    scn.next();
                    AnswerLogic.useTimeToRepeat = scn.nextBoolean();
                    scn.next();
                    Arrays.languageIndex = scn.nextInt();
                    scn.next();
                    Arrays.lessonIndex = scn.nextInt();
                    scn.next();
                    isTrialVersion = scn.nextBoolean();
                    scn.next();
                    Trial.trialCount = scn.nextInt();
                    scn.next();
                    playSoundWhenQuestionIsShown = scn.nextBoolean();
                    scn.next();
                    playSoundWhenAnswerIsShown = scn.nextBoolean();
                    scn.next();
//                    buttonLayoutY = scn.nextFloat();
//                    scn.next();
//                    buttonLayoutYCounter = scn.nextInt();
//                    scn.next();


                } catch (Exception e) {
                    break;
                }
            }
            scn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
