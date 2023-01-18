package com.notorein.BasicAppII;


import static com.notorein.BasicAppII.AnswerLogic.allowReverseCurrentCard;
import static com.notorein.BasicAppII.AnswerLogic.allowReverseNextCard;
import static com.notorein.BasicAppII.AnswerLogic.answerIsRight;
import static com.notorein.BasicAppII.AnswerLogic.answerIsVisible;
import static com.notorein.BasicAppII.AnswerLogic.considerAnswer;
import static com.notorein.BasicAppII.AnswerLogic.countDownTillNewCards;
import static com.notorein.BasicAppII.AnswerLogic.introduceNewCards;
import static com.notorein.BasicAppII.AnswerLogic.introduceNewCardsAfterRightAnswerCount;
import static com.notorein.BasicAppII.AnswerLogic.questionIsReversed;
import static com.notorein.BasicAppII.AnswerLogic.questionIsReversedTemp;
import static com.notorein.BasicAppII.AnswerLogic.reversAnswerAtEnd;
import static com.notorein.BasicAppII.AnswerLogic.setCardToTheEnd;
import static com.notorein.BasicAppII.Parameter.ParameterEXCEL.cellIndex;
import static com.notorein.BasicAppII.Parameter.ParameterEXCEL.columnIndex;
import static com.notorein.BasicAppII.Parameter.ParameterEXCEL.destinationDirectory;
import static com.notorein.BasicAppII.Parameter.ParameterEXCEL.fileExtension;
import static com.notorein.BasicAppII.Parameter.ParameterEXCEL.folderName;
import static com.notorein.BasicAppII.Parameter.ParameterEXCEL.sheetIndex;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.notorein.BasicApp.R;
import com.notorein.BasicAppII.POI.POIWriteNewEXCELFile;
import com.notorein.BasicAppII.Parameter.Parameter;
import com.notorein.BasicAppII.Strings.StringsEXCEL;
import com.notorein.BasicAppII.Strings.StringsUI;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Arrays {


    //    public static ArrayList<String> fromFileProgress = new ArrayList<>();
    public static int lessonsTemplateCardMetaInfoArrayMinLength = 19;
    public final static int considerIndex = 25;
    private static final int stepLimit = 12;
    // The random value makes sure that the cards are not always placed in the same order after the answer
//    public static int randomNextCard = 0;
    private static final int considerIndexMultiplier = 2;
    private static final int nextCardRandomLimit = 7;
    public static String[][] loadedCardsAsArray = null;
    public static File[] lessonFilesInCustomFolder;
    public static String[] lessonNames;
    public static int lessonIndex = 0;
    public static LinkedList<LinkedList<String>> loadedCardsAsList;
    public static ArrayList<Boolean> startUpBooleans;
    public static ArrayList<Boolean> lessonsBooleans;
    public static int questionCardIndex = 0;
    public static int answerCardIndex = 1;
    public static int questionCardHintIndexMarker = 2;
    public static int answerCardHintIndexMarker = 3;
    public static int allowReverseCardIndexQuestionMarker = 4;
    public static int showHintIndexMarker = 5;
    public static int questionCardRepeatIndexMarker = 6;
    public static int answerCardRepeatIndexMarker = 7;
    public static int questionRepeatTimeIndexMarker = 8;
    public static int answerRepeatTimeIndexMarker = 9;
    public static int questionTimeStepIndexMarker = 10;
    public static int answerTimeStepIndexMarker = 11;
    public static int questionCountRightIndexMarker = 12;
    public static int questionCountFalseIndexMarker = 13;
    public static int answerCountFalseIndexMarker = 14;
    public static int answerCountRightIndexMarker = 15;
    public static int sortIndexCard = 16;
    public static int sortIndexCardProgress = 17;
    public static int fileInfoII = 18;
    public static String question = "";
    public static String answer = "";
    public static String questionHint = "";
    public static String answerHint = "";
    // The index of the card that will be shown as a question
    public static int indexShownCard = 0;
    // The index of the card that will be shown as the next question
    public static int indexNextCard = 1;
    public static int progressCountNonReversed;
    public static int progressCountReversed;

    public static int cardlistLenght = 0;
    public static LinkedList<LinkedList<String>> loadedCardsAsListFromExcelUpdate;
    public static String[][] firstCardInRowInAddMenu;
    public static String[] firstCardInRowTempEdit;
    public static String[][] tempArrayForDelete;
    private static int questRepeatIndex = 0;
    private static int answrRepeatIndex = 0;
    private static int questRepeatIndexNext = 0;
    private static int answrRepeatIndexNext = 0;
    private static String progressCountAsString = "";
    private static String[] allProgresses;
    //    public static int lessonIndex = 0;
    public static int languageIndex = 0;
    //    public static int[] lessonIDs;
    public static int lessonID = 0;
    public static String lessonName;
    private static int lengthReversed;
    public static String[] firstCardInRow;
    public static String[] secondCardInRow;
    public static int editedCardIndex = 0;
    public static int setIndex = 0;
    private static int movedCardsCounter = 0;

    //    public static boolean moveCardsInArrayAfterAnswer(Activity activity, ConstraintLayout layout, boolean firstStart) {
//        // AI
//        // These two integers determine where to place the cards after right/wrong answer.
//        setIndex = 0;
//        // The card will later been set to the setIndex in the array.
//        // This Random is used to make sure that cards are not repeated in the same order all the time.
//        int randomNextCard = new Random().nextInt(nextCardRandomLimit);
//        if (randomNextCard > loadedCardsAsArray.length - 2) {
//            randomNextCard = loadedCardsAsArray.length - 2;
//        }
//        if (randomNextCard < 1) {
//            randomNextCard = 1;
//        }
//        indexNextCard = 1;
//        indexShownCard = 0;
//        // These variables are the positions the cards have in the array after right of false answers
//        questRepeatIndex = Integer.parseInt(loadedCardsAsArray[0][questionCardRepeatIndexMarker]);
//        answrRepeatIndex = Integer.parseInt(loadedCardsAsArray[0][answerCardRepeatIndexMarker]);
//        allowReverseCurrentCard = Boolean.valueOf(loadedCardsAsArray[0][allowReverseCardIndexQuestionMarker]);
//        // Here is decided whether the card is allowed to reverse
////        int[] tempIndex = allowReverseNextCardLogic();
//        // These two integers determine where to place the cards after right/wrong answer.
////        int repeatIndex = tempIndex[0];
////        int repeatIndexNext = tempIndex[1];
//        // Here is determined whether the next card should be displayed as reversed. If the cardIndex is to big it will be reversed.
//        // After it was chosen to reverse the card or not text for the next question is set here.
//        getNextQuestion(activity, layout);
//        // Determines the index to which the card should go.
////        Log.i(TAG, "moveCardsInArrayAfterAnswer: " + randomNextCard);
//        if(allowReverseCurrentCard)
//        setIndex = setIndexToThatTheCardShouldGoInArray(setIndex, repeatIndex, randomNextCard);
//        // Sets the card to the index that has been chosen in the method above.
//        setCardToIndex(setIndex, firstCardInRow, secondCardInRow);
//        return true;
//    }
    public static boolean moveCardsInArrayAfterAnswer(Activity activity, ConstraintLayout layout, boolean firstStart) {
        // AI
        // These two integers determine where to place the cards after right/wrong answer.


        setIndex = 0;
        // The card will later been set to the setIndex in the array.
        // This Random is used to make sure that cards are not repeated in the same order all the time.
        int randomNextCard = new Random().nextInt(nextCardRandomLimit);
        if (randomNextCard > loadedCardsAsArray.length - 2) {
            randomNextCard = loadedCardsAsArray.length - 2;
        }
        if (randomNextCard < 1) {
            randomNextCard = 1;
        }
        firstCardInRow = loadedCardsAsArray[indexShownCard];
        secondCardInRow = loadedCardsAsArray[indexNextCard];
        indexNextCard = 1;
        indexShownCard = 0;
        // These variables are the positions the cards have in the array after right of false answers
        questRepeatIndex = Integer.parseInt(firstCardInRow[questionCardRepeatIndexMarker]);
        answrRepeatIndex = Integer.parseInt(firstCardInRow[answerCardRepeatIndexMarker]);
        questRepeatIndexNext = Integer.parseInt(secondCardInRow[questionCardRepeatIndexMarker]);
        answrRepeatIndexNext = Integer.parseInt(secondCardInRow[answerCardRepeatIndexMarker]);
        allowReverseCurrentCard = Boolean.valueOf(firstCardInRow[allowReverseCardIndexQuestionMarker]);
        allowReverseNextCard = Boolean.valueOf(secondCardInRow[allowReverseCardIndexQuestionMarker]);
        // Here is decided whether the card is allowed to reverse
        int[] tempIndex = allowReverseNextCardLogic();
        // These two integers determine where to place the cards after right/wrong answer.
        int repeatIndex = tempIndex[0];
        int repeatIndexNext = tempIndex[1];
        // Here is determined whether the next card should be displayed as reversed. If the cardIndex is to big it will be reversed.
        // After it was chosen to reverse the card or not text for the next question is set here.
        getNextQuestion(activity, layout, firstCardInRow, secondCardInRow);
        // Determines the index to which the card should go.
//        Log.i(TAG, "moveCardsInArrayAfterAnswer: " + randomNextCard);
        setIndex = setIndexToThatTheCardShouldGoInArray(setIndex, repeatIndex, randomNextCard);
        // Sets the card to the index that has been chosen in the method above.
        setCardToIndex(setIndex, firstCardInRow, secondCardInRow);
        return true;


    }


    public static void hideHintLogic(ConstraintLayout layout, boolean consider) {
        // AI
        TextView questionTxtVwHint = layout.findViewById(R.id.questionTxtVwII);
        String hintEmpty = StringsEXCEL.lessonTemplateEmpty[Arrays.questionCardHintIndexMarker];
        String hintFromText = questionTxtVwHint.getText().toString();
        String[] currentCard = consider ? firstCardInRow : secondCardInRow;
        boolean showHint = Boolean.valueOf(loadedCardsAsArray[0][showHintIndexMarker]);
        questionTxtVwHint.setAlpha(0);
        if (!questionIsReversed && !answerIsVisible && !hintFromText.equals(hintEmpty) && !hintFromText.equals("#")) {
            if (!Parameter.hideHint) {
                questionTxtVwHint.setAlpha(1);
                ActivityMain.fadeActionHint.run();
            } else {
                questionTxtVwHint.setVisibility(View.INVISIBLE);
            }
        } else {
            questionTxtVwHint.setVisibility(View.INVISIBLE);
        }
    }


    private static int[] allowReverseNextCardLogic() {
        // NOT TO GIVE TO THE AI IT MESSED IT UP
        // Here is decided whether the card is allowed to reverse
        if (!allowReverseCurrentCard) {
            questionIsReversedTemp = false;
        } else {
            // This boolean makes sure that only forwarded Cards being reversed.
            questionIsReversedTemp = questionIsReversed;
        }
        int repeatIndex = 0;
        int repeatIndexNext = 0;
        // Here is the card that is displayed as the question is chosen.
        if (questionIsReversedTemp) {
            repeatIndex = answrRepeatIndex;
        } else {
            repeatIndex = questRepeatIndex;
        }
        // Here is the card that is displayed as the NEXT question is chosen.
        if (allowReverseNextCard) {
            if (questionIsReversedTemp) {
                // If the question is reversed the answer of the next card will be presented as the question
                repeatIndexNext = answrRepeatIndexNext;
            }
        } else {
            repeatIndexNext = questRepeatIndexNext;
        }
        return new int[]{repeatIndex, repeatIndexNext};
    }

    private static int reversAnswerAtEndLogic(int cardIndexPrae, int cardIndex) {
        if (reversAnswerAtEnd) {
            if (allowReverseNextCard) {
                if (cardIndexPrae > cardlistLenght - 10) {
                    questionIsReversedTemp = !questionIsReversed;
                }
                if (questionIsReversedTemp) {
                    cardIndex = answrRepeatIndex;
                } else {
                    cardIndex = questRepeatIndex;
                }
            }
        }
        return cardIndex;
    }


    private static void getNextQuestion(Activity activity, ConstraintLayout layout, String[] firstCardInRow, String[] secondCardInRow) {
        // PARTIALLY AI
        TextView questionTxtVw = (TextView) layout.findViewById(R.id.questionTxtView);
        TextView questionTxtVwHint = (TextView) layout.findViewById(R.id.questionTxtVwII);
        TextView answerTxtVw = (TextView) layout.findViewById(R.id.answerTxtVw);
        TextView hintDirection = (TextView) layout.findViewById(R.id.hintDirection);
        String[][] temp = new String[][]{firstCardInRow, secondCardInRow};

        if (!allowReverseNextCard) {
            questionIsReversedTemp = false;
        } else {
            // This boolean makes sure that only cards that are allowed to reverse are reversed
            questionIsReversedTemp = questionIsReversed;
        }

        // Here the direction hint is adapted to the booleans
        if (!allowReverseNextCard && questionIsReversed) {
            // If the card is not allowed to reverse the direction hint gets a sign -> *
            hintDirection.setText(StringsUI.direction[2]);
        } else {
            if (questionIsReversedTemp) {
                hintDirection.setText(StringsUI.direction[1]);
            } else {
                hintDirection.setText(StringsUI.direction[0]);
            }
        }

        if (questionIsReversedTemp) {
            // If it was chosen and allowed to reverse the card the answer will be presented as the question.
            question = secondCardInRow[answerCardIndex];
            questionHint = secondCardInRow[questionCardHintIndexMarker];
            answer = secondCardInRow[questionCardIndex];
            // showHint = Boolean.valueOf(firstCardInRow[showHintIndexMarker]);
        } else {
            question = secondCardInRow[questionCardIndex];
            questionHint = secondCardInRow[questionCardHintIndexMarker];
            answer = secondCardInRow[answerCardIndex];
            // showHint = Boolean.valueOf(firstCardInRow[showHintIndexMarker]);
        }
        // Gets the next question and sets it to the field so user can reading them.
        question = question.replace("_", " ").trim();
        questionHint = questionHint.replace("_", " ").trim();
        answer = answer.replace("_", " ").trim();
        questionTxtVw.setText(question);
        questionTxtVwHint.setText(questionHint);
        answerTxtVw.setText(answer);
    }

    private static int setIndexToThatTheCardShouldGoInArray(int setIndex, int repeatIndex, int randomNextCard) {
        // AI
        if (considerAnswer) {
            return considerIndex * 2 + randomNextCard;
        } else {
            if (!setCardToTheEnd) {
                if (answerIsRight) {
                    setIndex = repeatIndex + (repeatIndex * 2) + randomNextCard;
                    if (setIndex >= cardlistLenght) {
                        setIndex = cardlistLenght - 1;
                    }
                    if (setIndex < 0) {
                        setIndex = 10;
                    }
                    if (questionIsReversedTemp) {
                        loadedCardsAsArray[indexShownCard][answerCardRepeatIndexMarker] = "" + setIndex;
                    } else {
                        loadedCardsAsArray[indexShownCard][questionCardRepeatIndexMarker] = "" + setIndex;
                    }
                } else {
                    setIndex = (int) (repeatIndex * 0.5);
                    if (setIndex < 10) {
                        setIndex = 10;
                    }
                    if (questionIsReversedTemp) {
                        loadedCardsAsArray[indexShownCard][answerCardRepeatIndexMarker] = "" + setIndex;
                    } else {
                        loadedCardsAsArray[indexShownCard][questionCardRepeatIndexMarker] = "" + setIndex;
                    }
                }
            } else {
                if ((answrRepeatIndex > cardlistLenght - 10 && questRepeatIndex > cardlistLenght - 10)) {
                    setIndex = cardlistLenght - 1;
                    if (questionIsReversedTemp) {
                        loadedCardsAsArray[indexShownCard][answerCardRepeatIndexMarker] = "" + (cardlistLenght - 1);
                    } else {
                        loadedCardsAsArray[indexShownCard][questionCardRepeatIndexMarker] = "" + (cardlistLenght - 1);
                    }
                } else {
                    setIndex = considerIndex * 2;
                    loadedCardsAsArray[indexShownCard][answerCardRepeatIndexMarker] = "" + (cardlistLenght - 1);
                    loadedCardsAsArray[indexShownCard][questionCardRepeatIndexMarker] = "" + (cardlistLenght - 1);
                }
            }
        }
        return setIndex;
    }

    private static void setCardToIndex(int setIndex, String[] firstCardInRow, String[] secondCardInRow) {
        // AI
        // These two if statements prevent IndexOutOfBoundExceptions
        setIndex = Math.max(setIndex, 0);
        setIndex = Math.min(setIndex, cardlistLenght - 1);

        try {
            System.arraycopy(loadedCardsAsArray, 1, loadedCardsAsArray, 0, setIndex);
        } catch (Exception e) {
            // catch block can be left empty
        }

        // Here the actual question is reintegrated to the Array
        loadedCardsAsArray[setIndex] = firstCardInRow;
        setCardToTheEnd = false;
        considerAnswer = false;
    }


    public static void introduceNewCards() {
        // AI
        if (!introduceNewCards) {
            return;
        }

        countDownTillNewCards++;
        if (countDownTillNewCards <= introduceNewCardsAfterRightAnswerCount) {
            return;
        }

        int newBlockSize = 5;
        int length = loadedCardsAsArray.length;
        int startEnd = (int) (length * Math.random());
        if ((startEnd + newBlockSize) >= length - 1) {
            return;
        }

        for (int i = 0; i < newBlockSize; i++) {
            try {
                String[] start = loadedCardsAsArray[i];
                String[] end = loadedCardsAsArray[startEnd + i];
                loadedCardsAsArray[i] = end;
                loadedCardsAsArray[startEnd + i] = start;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        countDownTillNewCards = 0;
    }


    public static String[][] copyCard(String[][] temp, int editedCardIndex) {
        boolean reachedLimit = false;
        String[][] newArray = new String[temp.length + 1][Arrays.lessonsTemplateCardMetaInfoArrayMinLength];
        for (int i = 0; i < temp.length; i++) {
            if (reachedLimit) {
                newArray[i] = temp[i];
            } else {

                if (i == editedCardIndex + 1) {
                    newArray[i] = temp[i];
                    reachedLimit = true;
                    newArray[i] = temp[i - 1];
                } else {
                    newArray[i] = temp[i - 1];
                }
            }
        }
        return temp;
    }

    public static String[][] sortLesson(String[][] temp) {
        for (int i = 1; i < temp.length; i++) {
            String[] currentCard = temp[i];
            int sortIndexCurrent = Integer.parseInt(currentCard[sortIndexCard]);
            int j = i - 1;
            while (j >= 0 && Integer.parseInt(temp[j][sortIndexCard]) > sortIndexCurrent) {
                temp[j + 1] = temp[j];
                j--;
            }
            temp[j + 1] = currentCard;
        }
        return temp;
    }

    public static String[][] sortLessonDelete(String[][] temp, int removeIndex) {
        for (int i = 0; i < temp.length; i++) {
            int value = Integer.parseInt(temp[i][Arrays.sortIndexCard]);
            if (value >= removeIndex) {
                value--;
                if (value < 0) {
                    value = 0;
                }
                temp[i][sortIndexCard] = Integer.toString(value);
            }
        }
        return temp;
    }

    public static String[][] sortLessonCopy(String[][] temp, int removeIndex) {
        for (int i = 0; i < temp.length; i++) {
            int value = Integer.parseInt(temp[i][Arrays.sortIndexCard]);
            if (value > removeIndex) {
                value++;
                if (value < 0) {
                    value = 0;
                }
                temp[i][sortIndexCard] = Integer.toString(value);
            }
        }
        return temp;
    }

    public static String[][] readListToArray(LinkedList<LinkedList<String>> arrayList) {
        // AI
        String[][] array = new String[arrayList.size()][];
        for (int i = arrayList.size() - 1; i >= 0; i--) {
            array[i] = arrayList.get(i).toArray(new String[arrayList.get(i).size()]);
        }
        cardlistLenght = array.length;
        return array;
    }

    public static void readListToArray() {
        // AI
        cardlistLenght = loadedCardsAsList.size();
        loadedCardsAsArray = new String[cardlistLenght][lessonsTemplateCardMetaInfoArrayMinLength];
        for (int i = 0; i < cardlistLenght; i++) {
            LinkedList<String> row = loadedCardsAsList.get((cardlistLenght - 1) - i);
            for (int n = 0; n < lessonsTemplateCardMetaInfoArrayMinLength; n++) {
                if (n < row.size()) {
                    loadedCardsAsArray[i][n] = row.get(n);
                } else {
                    break;
                }
            }
        }
    }

    public static LinkedList<LinkedList<String>> readArrayToList(String[][] array) {
        // AI
        LinkedList<LinkedList<String>> list = new LinkedList<>();
        for (int i = 0; i < array.length; i++) {
            LinkedList<String> temp = new LinkedList<>();
            for (int n = 0; n < lessonsTemplateCardMetaInfoArrayMinLength; n++) {
                temp.add(array[i][n]);
            }
            list.add(temp);
        }
        return list;
    }

    public static ArrayList<String> readArrayToList(String[] array) {
        // AI
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    public static ArrayList<File> readArrayToList(File[] array) {

        ArrayList<File> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }


    public static void readArrayToList() {
        cardlistLenght = loadedCardsAsList.size();
        LinkedList<String> temp = new LinkedList<>();
        for (int i = 0; i < cardlistLenght; i++) {
            for (int n = 0; n < lessonsTemplateCardMetaInfoArrayMinLength; n++) {
                try {
                    String value = loadedCardsAsArray[i][n];
                    temp.add(value);
                } catch (Exception e) {
                    break;
                }
            }
            loadedCardsAsList.add(temp);
        }
    }

//    public static void mixCards() {
//        for (int i = 0; i < loadedCardsAsArray.length; i++) {
//            int randomI = new Random().nextInt(loadedCardsAsArray.length - 1);
//            int randomII = new Random().nextInt(loadedCardsAsArray.length - 1);
//            try {
//                String[] start = loadedCardsAsArray[randomI];
//                String[] end = loadedCardsAsArray[randomII];
//                loadedCardsAsArray[randomI] = end;
//                loadedCardsAsArray[randomII] = start;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public static void mixCards() {
        // AI
        Random random = new Random();
        for (int i = 0; i < loadedCardsAsArray.length; i++) {
            int randomI = random.nextInt(loadedCardsAsArray.length);
            int randomII = random.nextInt(loadedCardsAsArray.length);
            String[] temp = loadedCardsAsArray[randomI];
            loadedCardsAsArray[randomI] = loadedCardsAsArray[randomII];
            loadedCardsAsArray[randomII] = temp;
        }
    }


    public static LinkedList<LinkedList<String>> removeEmptyCards(LinkedList<LinkedList<String>> loadedCardsAsList) {
        for (int c = loadedCardsAsList.size() - 1; c > Parameter.emptyCardLimit; c--) {
            String question = loadedCardsAsList.get(c).get(Arrays.questionCardIndex);
            String answer = loadedCardsAsList.get(c).get(Arrays.answerCardIndex);
            if (isEmptyCard(question, answer)) {
                loadedCardsAsList.remove(c);
            }
        }
        return loadedCardsAsList;
    }

    public static boolean isEmptyCard(String question, String answer) {
        if ((question.equals(StringsEXCEL.QUESTION) && answer.equals(StringsEXCEL.ANSWER)) || (question.equals(StringsEXCEL.ANSWER) && answer.equals(StringsEXCEL.QUESTION))) {
            Parameter.isEmptyCard = true;
            return true;
        }
        Parameter.isEmptyCard = false;
        return false;
    }

    public static String[][] removeEmptyCardsToArray(LinkedList<LinkedList<String>> loadedCardsAsList) {
        String[][] temp = null;
        for (int c = loadedCardsAsList.size() - 1; c >= 10; c--) {
            String question = loadedCardsAsList.get(c).get(Arrays.questionCardIndex);
            String answer = loadedCardsAsList.get(c).get(Arrays.answerCardIndex);
            if (isEmptyCard(question, answer)) {
                loadedCardsAsList.remove(c);
            }
        }
        for (int c = loadedCardsAsList.size() - 1; c >= 10; c--) {
            for (int n = 0; n < loadedCardsAsList.get(c).size(); n++) {
                temp[c][n] = loadedCardsAsList.get(c).get(n);
            }
        }
        return temp;
    }


    public static void getAllLessonsInCustomFolder(Context context) {

//        folderName = folderName.trim();
        File file = new File(Environment.getExternalStorageDirectory(), folderName);
        if (file.exists() && file.isDirectory()) {
            lessonFilesInCustomFolder = file.listFiles(
                    new FileFilter() {
                        @Override
                        public boolean accept(File file) {
                            return (file.getPath().endsWith(".xlsx"));
                        }
                    });
            Arrays.lessonNames = new String[lessonFilesInCustomFolder.length];
            for (int i = 0; i < lessonFilesInCustomFolder.length; i++) {
                Arrays.lessonNames[i] = lessonFilesInCustomFolder[i].getName().toString().replace(".xlsx", "");
            }
//            Toast.makeText(context, " Arrays.lessonFilesInCustomFolder.length   " + Arrays.lessonFilesInCustomFolder.length, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "No Lessons found!\nCreated Empty Lesson Instead! ", Toast.LENGTH_SHORT).show();
        }
        if (lessonNames == null) {
            POIWriteNewEXCELFile writeEXCELFile = new POIWriteNewEXCELFile(new File(destinationDirectory, "test" + fileExtension), sheetIndex, columnIndex, cellIndex, context);
            writeEXCELFile.copyAssets();
            getAllLessonsInCustomFolder(context);
        }
    }

    public static void progressCounter(String[][] questioning, ConstraintLayout layout) {
        // AI
        progressCountNonReversed = 0;
        progressCountReversed = 0;
        int indexReversed = Arrays.answerCardRepeatIndexMarker;
        int indexNonReversed = Arrays.questionCardRepeatIndexMarker;
        lengthReversed = 0;
        int i = 0;
        for (String[] card : questioning) {
            Arrays.loadedCardsAsArray[i][Arrays.fileInfoII] = "" + i;
            if (Integer.parseInt(card[indexNonReversed]) >= questioning.length - 5 ||
                    Integer.parseInt(card[indexReversed]) >= questioning.length - 5) {
                progressCountNonReversed++;
                progressCountReversed++;
            }
            if (Boolean.parseBoolean(card[allowReverseCardIndexQuestionMarker])) {
                lengthReversed++;
            }
            i++;
        }
        getProgressAsString(questioning, layout);
    }


//    public static void progressCounter(String[][] questioning, ConstraintLayout layout) {
//        progressCountNonReversed = 0;
//        progressCountReversed = 0;
//        int indexReversed = Arrays.answerCardRepeatIndexMarker;
//        int indexNonReversed = Arrays.questionCardRepeatIndexMarker;
//        lengthReversed = questioning.length;
//        for (int i = 0; i < questioning.length; i++) {
//            Arrays.loadedCardsAsArray[i][Arrays.fileInfoII] = "" + i;
//            if (Integer.parseInt(questioning[i][indexNonReversed]) >= questioning.length - 5) {
//                progressCountNonReversed++;
//            }
//            if (Integer.parseInt(questioning[i][indexReversed]) >= questioning.length - 5) {
//                progressCountReversed++;
//
//            }
//            if (!Boolean.parseBoolean(questioning[i][allowReverseCardIndexQuestionMarker])) {
//                lengthReversed--;
//            }
//
////            Log.i(TAG, questioning[i][indexReversed] + " " + progressCountNonReversed + "  " + questioning[i][indexNonReversed] + " " + progressCountReversed);
//        }
//        getProgressAsString(questioning, layout);
//    }


    public static void getProgressAsString(String[][] questioning, ConstraintLayout layout) {
        TextView hintMode = (TextView) layout.findViewById(R.id.hintMode);
        try {
            progressCountAsString = questioning.length + "/" + progressCountNonReversed + " ?\n" +
                    lengthReversed + "/" + progressCountReversed + " !\n" +
                    StringsUI.mode[Parameter.advancedRepeatLogicIndex];
            hintMode.setText(progressCountAsString);
        } catch (Exception e) {
            Parameter.advancedRepeatLogicIndex = 0;
            progressCountAsString = questioning.length + "/" + progressCountNonReversed + " ?\n" +
                    lengthReversed + "/" + progressCountReversed + " !\n"
                    +
                    StringsUI.mode[Parameter.advancedRepeatLogicIndex];
            hintMode.setText(progressCountAsString);
        }
    }

    public static String[][] addElementToCardArray(String[][] array, String[] element) {
        // Check if the array is empty
//        if (array.length == 0) {
//            // Create a new 2D array with a single element
//            return new String[][]{{element}};
//        }

        // Create a new 2D array with an additional row
        String[][] newArray = new String[array.length + 1][];

        // Add the new element to the first row of the new array
        newArray[0] = element;

        // Copy the elements from the old array to the new array
        for (int i = 0; i < array.length; i++) {
            newArray[i + 1] = java.util.Arrays.copyOf(array[i], array[i].length);
        }

        return newArray;
    }

    public static boolean checkForEmptyCardsInEdit(String[][] arrayOne, String[] arrayTwo) {
        String[] empty = null;
        String[] first = arrayOne[0];
        for (int i = 0; i < arrayOne.length; i++) {
            if (arrayOne[i][0].equals(arrayTwo[0]) && arrayOne[i][1].equals(arrayTwo[1])) {
                editedCardIndex = i;
                return false;
            } else {
                editedCardIndex = 0;
            }
        }
        return true;
    }

    public static String[][] removeVariable(String[][] array, int i) {
        for (int j = i; j < array.length - 1; j++) {
            array[j] = array[j + 1];
        }
        array[array.length - 1] = null;
        return array;
    }


//    public static void compareLists(ArrayList<ArrayList<String>> loadedCardsAsListFromExcelUpdate, ArrayList<ArrayList<String>> loadedCardsAsList) {
//        boolean foundEqualCard = false;
//
//        String question = null;
//        String questionHint = null;
//        String answer = null;
//
//        String questionII = null;
//        String questionHintII = null;
//        String answerII = null;
//
//        for (int j = 0; j < loadedCardsAsListFromExcelUpdate.size(); j++) {
//            question = loadedCardsAsListFromExcelUpdate.get(j).get(Arrays.questionCardIndex);
//            questionHint = loadedCardsAsListFromExcelUpdate.get(j).get(Arrays.questionCardHintIndexMarker);
//            answer = loadedCardsAsListFromExcelUpdate.get(j).get(Arrays.answerCardIndex);
//            for (int i = 0; i < loadedCardsAsList.size(); i++) {
//                questionII = loadedCardsAsList.get(i).get(Arrays.questionCardIndex);
//                questionHintII = loadedCardsAsList.get(i).get(Arrays.questionCardHintIndexMarker);
//                answerII = loadedCardsAsList.get(i).get(Arrays.answerCardIndex);
//                if (question.equals(questionII) && questionHint.equals(questionHintII) && answer.equals(answerII)) {
//                    foundEqualCard = true;
//                }
//            }
//        }
//    }

//    public static void compareLists(String[][] loadedCardsAsListFromExcelUpdate, String[][] loadedCardsAsList) {
//
//        for (int n = 0; n < loadedCardsAsListFromExcelUpdate.size(); n++) {
//            boolean found = false;
//            for (int m = 0; m < loadedCardsAsList.size(); m++) {
//                if (loadedCardsAsListFromExcelUpdate.get(n)[0].equals(loadedCardsAsList.get(m)[0]) &&
//                        loadedCardsAsListFromExcelUpdate.get(n)[1].equals(loadedCardsAsList.get(m)[1]) &&
//                        loadedCardsAsListFromExcelUpdate.get(n)[2].equals(loadedCardsAsList.get(m)[2])) {
//                    found = true;
//                    break;
//                }
//            }
//            if (!found) {
//                listTwo.add(listOne.get(n));
//            }
//        }
//    }

    public static LinkedList<LinkedList<String>> compareLists(LinkedList<LinkedList<String>> loadedCardsAsListFromExcelUpdate, LinkedList<LinkedList<String>> loadedCardsAsList) {
        for (int n = 0; n < loadedCardsAsListFromExcelUpdate.size(); n++) {
            boolean found = false;
            for (int m = 0; m < loadedCardsAsList.size(); m++) {
                if (loadedCardsAsListFromExcelUpdate.get(n).get(0).equals(loadedCardsAsList.get(m).get(0)) &&
                        loadedCardsAsListFromExcelUpdate.get(n).get(1).equals(loadedCardsAsList.get(m).get(1))) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                loadedCardsAsList.add(loadedCardsAsListFromExcelUpdate.get(n));
            }
        }
        return loadedCardsAsList;
    }

}


