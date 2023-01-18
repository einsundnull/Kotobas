package com.notorein.BasicAppII.Parameter;

import android.text.TextPaint;

public class Parameter {

    public static boolean nightMode;
    public static boolean saveBatteryMode = true;
    public static boolean settingsWereUsed;
    public static boolean manualWasShown;
    public static boolean showMenu;

    public static boolean alwaysShowManualAtStart;
    public static boolean bigLetters = true;
    public static boolean isTrialVersion = false;
    public static boolean settingsWereUsedII;
    public static int mode;
    public static double displayWidth;
    public static float displayHeight;
    public static int advancedRepeatLogicIndex = 3;
    public static boolean playSoundWhenQuestionIsShown = true;
    public static boolean playSoundWhenAnswerIsShown = true;
    public static boolean playAnswerSound = true;
    public static boolean playQuestionSound = true;
    public static boolean moveButtons = false;
    public static boolean getFilesFromCustomFolder = true;
    public static int tempButtonLayoutY;


    public static double questionTextSize;
    public static double questionTextSizeII;
    public static double answerTextSize;
    public static int orientationII = 0;
    public static int orientationI = 0;
    public static int orientationTemporaryII = 0;


    public static int progress = 0;
    public static int rnd;
    public static float buttonLayoutY = 165;
    public static boolean buttonLayoutWasMovedUpwards;
    public static int buttonLayoutYCounter = 1;
    public static int defaultTextSize;
    public static String txt;
    public static int widthOfTextView;
    public static TextPaint textPaint;
    public static int widthTextFromTextView;

    public static float newTextSize;
    public static double weightedTextSize;
    public static double questionTextSizePercentage = 1;
    public static double questionHintTextSizePercentage = 0.6;
    public static double answerTextSizePercentage = 0.7;
    public static long newCardTransitionDuration = 800;
    public static long newCardTransitionDurationHint = 800;

    public static boolean firstStart = false;
    // This is the probability the cards learning direction is changed randomly.
    public static int reverseQuestionRandomlyRNDLimit = 5;
    public static int maxStringLengthLine;
    public static boolean fontSize;
    public static boolean doubleTextViewSize;
    public static int toastOffsetY;
    public static int toastOffsetX;
    public static boolean hideHint;
    public static boolean recreateActivity;
    public static boolean useFadeAnimationInQuestionLayout = true;
    public static boolean isEmptyCard = false;
    public static int emptyCardLimit = 1;
    public static boolean deletedLesson;
    public static boolean removeCard = false;
    public static boolean allowToStoreCard = true;
    public static boolean sortArrayBeforeEditingInAddMenu;
    public static boolean cardHasBeenEdited;
    public static boolean makeNewCard;
    public static boolean allowToReverseCard;
    public static boolean allowToAddNewCard;
    public static int editTextIndex;
    public static String text;
    public static boolean isRecording;

//    public static int lessonsTemplateCardMetaInfoArrayMinLength = 19;


}
