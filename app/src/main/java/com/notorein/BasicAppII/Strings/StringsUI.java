package com.notorein.BasicAppII.Strings;

import com.notorein.BasicAppII.AnswerLogic;
import com.notorein.BasicAppII.Trial;

public class StringsUI {

    public static String[] hintText = {"?", "!"};
    public static String[] direction = {"?→!", "!→?", "?→!*"};
    public static String[] mode = {"rnd", "end", AnswerLogic.rightAnswerCounter + "/" + AnswerLogic.rightAnswerLimit, "="};
    public static String[] btnLeftText = {"Consider", "Wrong"};
    public static String[] btnRightText = {"Show Answer", "Right"};
    public static String[] btnSetToEnd = {"→"};
    public static String[] hintClickText = {"Answers will be shown as questions!", "Questions will be shown as questions!"};
    public static String openingText = "Tap on the QUESTION MARK to reverse the learning direction.\n" +
            "The ?→! sign will change to !→?.\n\n" +
            "Tap the CONSIDER button [ → ] if you have a word on the tip of your tongue.\n\n" +
            "Tap the MIDDLE button to set the card to the end of the lesson.\n\n" +
            "Tap the SHOW ANSWER button to show the answer.\n\n" +
            "Tap on the question to hear the question again.\n\n" +
            "Tap on the answer to hear the answer again.\n\n" +
            "THIS APP IS NO REPLACEMENT FOR PRAXIS!";
    public static String ok = "OK";
    public static String no = "No";
    public static String yes = "Yes";
    public static String resetLesson = "Reset Lesson?";
    public static String mixCards = "Mix Cards?";
    public static String toastTextMix = "Cards have been mixed!";
    public static String toastTextDeleteCard = "Card has been deleted!";
    public static String toastTextReset = "Lesson has been reset!";
    public static String toastTextDeleteLesson = "Lesson has been deleted!";
    public static String manual = "Manual";
    public static String dialogDeleteCard = "Delete Card?";
    public static String dialogDeleteLesson = "Delete Lesson?";
    public static String alwaysShowManual = "Always Show Manual";
    public static String mp3 = ".mp3";
    public static String wav = ".wav";
    public static String menuDialog = "What you want to do?";
    public static String menuDialogEdit = "EDIT CARD";
    public static String menuDialogAd = "CREATE A NEW CARD";

    public static String[] menuDialogs = {
            "Show All Lessons", "Show All Cards of this Lessons", "Open Settings", "Adjust Buttons", "Choose Lesson", "Show Manual", "About"
    };
    public static String tempQuestionText = "";

    private static final int numberOfWords = 300;
    public static String aboutText =
            "Version 1.0" +
                    "\nType: Vocabulary" +
                    "\nSound Files Not Included" +
                    "\nRelease Date: 03.05.2022" +
                    "\nIncluded Words Approximately " + numberOfWords +
                    "\n\n" +
                    "This is version 1.0 of the app. Version 1.1 will include sound files. " +
                    "If you purchased V.1.0 you can send an email to: notorein@gmail.com. " +
                    "You will receive the updated version 1.1 with sound files after it is released for free." +
                    "\nA grammar version of this app is in progress to be made." +
                    "\nAll information is provided without warranty!" +
                    "\n\n" +
                    "Contact: notorein@gmail.com\n\n\n" +
                    Trial.countCardsLeft(Trial.trialCount);
    public static String[] adjustText = {"Up", "Down", "Tap here continue learning"};
    public static String goToDayMode = "Turn off dark mode?";
    public static String storedNewCardText = "New Card Saved";
    public static String storedEditedCardText = "Editing Saved";
    public static String updateExcelFileText = "Update Excel File";
    public static String updateFromExcelFileText = "Update Lesson From Excel File";
    public static String menuIconSymbol = "≡";
    public static String hideHintText = "Hints will be hidden from now on!";
    public static String showHintText = "Hints will be shown from now on!";
    public static String noNameForNewLessonSet = "Set a name for the lesson first";
    public static String newLessonName = "Name";
    public static String newLessonStoredTo = "File created under: ";
    public static String storeNewCardNotAllowedText = "Empty Fields are not allowed";
    public static String btn_right_text = "→";
    public static String btn_left_text = "←";
    public static String lastCard = "This is the last card.\nThere must be at least one card in the lesson!";
    public static String copiedCard = "Card Copied";
    public static String toastTextUpdatedLesson = "Lesson has been updated";
    public static CharSequence clipboardLabelText = "Card Text";
    public static String leaveWithoutSaving = "Do you want to leave without saving the card?";
    public static String emptyCard = "You have to store the card before you can delete it or just close the menu";


    public static void refreshAboutText() {
        aboutText =
                "Version 1.0" +
                        "\nType: Vocabulary" +
                        "\nSound Files Not Included" +
                        "\nRelease Date: 03.05.2022" +
                        "\nIncluded Words Approximately 1000" +
                        "\n\n" +
                        "This is version 1.0 of the app. Version 1.1 will include sound files. " +
                        "If you purchased V.1.0 you can send an email to: notorein@gmail.com. " +
                        "You will receive the updated version 1.1 with sound files after it is released for free." +
                        "\nA grammar version of this app is in progress to be made." +
                        "\nAll information is provided without warranty!" +
                        "\n\n" +
                        "Contact: notorein@gmail.com\n\n\n" +
                        Trial.countCardsLeft(Trial.trialCount);
    }
}