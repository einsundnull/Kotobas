package com.notorein.BasicAppII.Parameter;

import android.os.Environment;

import java.io.File;

public class ParameterEXCEL {


    public static String folderName = "Kotobas";

    public static String destinationDirectory = Environment.getExternalStorageDirectory() + "/" + folderName + "/";

//    public static String destinationDirectory = Environment.getExternalStorageDirectory() + "/" + folderName + "/";

    // Nineteen elements are in one column for all information of a card.
//    public final static int lessonsTemplateCardMetaInfoArrayMinLength = 19 - 1;

    public static int positionItemListViewLesson = 0;
    public static int positionItemListViewCards = 0;
    //     DOWNLOAD FILE
    // This is the link that will be opened in order to download the file
    public static String getURL = "https://docs.google.com/spreadsheets/d/18tedKnpURsb_-QYajsqzD-N_Q8JpgBe-/edit?usp=sharing&ouid=100102232968973464110&rtpof=true&sd=true";

    //     I don't know what that is for.
    public static String nameOfDownloadedFile = "lectionDownloadLinkList";
    //     This String is used to check whether the website mentioned below is available.
    public static String webSiteToCheckIfAvailable = "google.com";
    // This String defines in which directory Android puts the downloaded file
    // In this case it will be
    public static String DIRECTORY_DOWNLOADS = "Download";

    // Put the name of the directory you want to download into in this String. Replace "MyFolder"
//    public static  String folderName = "Kotobas";

    // Put the name of the sub directory you want to download into in this String. Replace "MyFolder"
//    public static String destinationDirectory = Environment.getExternalStorageDirectory() + "/" + folderName + "/";

    // This is the name the file that is downloaded will be stored.
//    public static String fileName = "questioningDatas";

    // This is the name the file format in which the downloaded file will be stored.
    public static String fileExtension = ".xlsx";
    public static File downloadedFile;
    public static int sheetIndex = 0;
    public static int columnIndex = 0;
    public static int cellIndex = 0;
    public static String newExelFileName = "test";


    // READ EXCEL SHEET
//    public static File excelFileToRead;

//    public static String excelFilePath;
//    public static String excelFileName;
//    public static int sheetIndex = 0;
//    public static int columnIndex = 1;
//    public static int cellIndex = 1;

//    public static int cellIndexStart = 1;
//    public static int cellIndexEnd = 1;

//    public static int sheetIndexstart;
//    public static int sheetIndexEnd;

//    public static int columnIndexstart;
//    public static int columnIndexEnd;
    // This ist the position of the card in the array
//    public static int cardIndex = 16;
//
//    public static int cardSideToRead = 0;
//
//    public static int allowToReverseCardIndex = 5;
//    public static int showQuestionHintIndex = 6;
//    public static boolean allowToReverseCard = true;
//    public static boolean showQuestionHint = true;
//    public static int questionIndex = 0;
//    public static int answerIndex = 1;
//    public static int questionHintIndex = 3;
//    public static int answerHintIndex = 4;


//    public static int cardListLength = 0;
//    public static File[] files;
//    public static String[] fileNames;
//    public static File[] fileListArray;
//    public static ArrayList<ArrayList<String>> loadedCardsAsList;
//    public static String[][] loadedLessonAsArray;
//    public static String[] listCardsQuestion;
//    public static String[] listCardsQuestionHint;
//    public static String[] listCardsAnswer;
//    public static String[] listCardsAnswerHint;
//    public static boolean[] allowToReverseThisCard;
//    public static boolean[] allowToShowHintOfThisCard;
//    public static int questionTextLength = 0;
//    public static int questionHintTextLength = 0;
//    public static int answerTextLength = 0;
//    public static int answerHintTextLength = 0;

//    public final static int numberOfElements = 19;
//    public final static int considerIndex = 25;
//    private static final int stepLimit = 12;
//    // The random value makes sure that the cards are not always placed in the same order after the answer
////    public static int randomNextCard = 0;
//    private static final int considerIndexMultiplier = 2;
//    private static final int nextCardRandomLimit = 7;
//
//    public static ArrayList<Boolean> startUpBooleans = new ArrayList<>();
//    public static ArrayList<Boolean> lessonsBooleans = new ArrayList<>();
//    public static int questionCardIndex = 0;
//    public static int answerCardIndex = 1;
//    public static int questionCardHintIndexMarker = 2;
//    public static int answerCardHintIndexMarker = 3;
//    public static int questionCardRepeatIndexMarker = 4;
//    public static int answerCardRepeatIndexMarker = 5;
//    public static int questionRepeatTimeIndexMarker = 6;
//    public static int answerRepeatTimeIndexMarker = 7;
//    public static int questionTimeStepIndexMarker = 8;
//    public static int answerTimeStepIndexMarker = 9;
//    public static int questionCountRightIndexMarker = 10;
//    public static int questionCountFalseIndexMarker = 11;
//    public static int answerCountFalseIndexMarker = 12;
//    public static int answerCountRightIndexMarker = 13;
//    public static int allowReverseCardIndexQuestionMarker = 14;
//    public static int showHintIndexMarker = 15;
//    public static int sortIndexCard = 16;
//    public static int fileInfoI = 17;
//    public static int fileInfoII = 18;
//    public static String question = "";
//    public static String answer = "";
//    public static String questionHint = "";
//    public static String answerHint = "";
//    // The index of the card that will be shown as a question
//    public static int indexShownCard = 0;
//    // The index of the card that will be shown as the next question
//    public static int indexNextCard = 1;
//    public static int progressCount;
}
